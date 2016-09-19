package com.chat.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Service centralise d'acc�s � une connection JDBC. Point d'entree : la methode getInstance
 * 
 * 
 * 
 * Le constructeur de ConnectionManager est prive. Pour recuperer une 
 * instance de ConnectionManager, il faut passer par la methode statique getInstance() 
 * (implementation classique du Design Pattern 'Singleton' decrivant des objets qui ne peuvent
 * avoir qu'une seule instance)
 * 
 * @author Sysdeo (c) 11/2004
 */

public class ConnectionManager {

	private static String DATASOURCE = null;
	private static String LOGIN = null;
	private static String PASSWORD = null;

	private static String DRIVER = null;
	private static String URL = null;

	private static ConnectionManager fConnectionManager = null;
	private DataSource fDataSource = null;

	/**
	 * Constructeur de ConnectionManager.
	 * Appelle le chargement des param�tres de connection � partir du fichier bdconf.properties .
	 * Appelle l'initialisation de la connection en base de donnees, en mode "classique" ou "datasource".
	 */
	private ConnectionManager() {
		loadProperties("bdconf");
		if (DATASOURCE != null) {
			this.initDataSource();
		} else {
			this.initConnectionClassique();
		}

	}

	private void loadProperties(String bundleFile) {
		ResourceBundle rb = null;
		try {
			rb = PropertyResourceBundle.getBundle(bundleFile);

			DATASOURCE = rb.getString("datasource");
		} catch (MissingResourceException e) {
			try {
				LOGIN = rb.getString("login");
				PASSWORD = rb.getString("password");
				DRIVER = rb.getString("driver");
				URL = rb.getString("url");
			} catch (MissingResourceException e2) {
				System.out.println("Parametres manquants dans bdconf.properties : " + e);
				return;
			}
		}

	}

	/**
	 * 
	 * Demande une connection � la DataSource est la renvoie � l'appelant.
	 * @return java.sql.Connection
	 */
	public Connection getConnection() throws SQLException {
		Connection ma_con = null;

		if (DATASOURCE != null) {
			ma_con = fDataSource.getConnection();
		} else {
			try {
				if (LOGIN == null || (LOGIN != null && LOGIN.trim().length() < 1)) {
					ma_con = DriverManager.getConnection(URL);
				} else {
					ma_con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
				}
			} catch (SQLException e) {
				System.out.println("Impossible de recuperer une connection par le DriverManager : " + e);
			}
		}
		return ma_con;
	}
	/**
	 * Retourne une instance de ConnectionManager. <BR>
	 * Cree cette instance si elle n'existe pas (mod�le de conception "singleton").
	 *
	 * @return com.bankonet.model.tech.ConnectionManager
	 */
	public static ConnectionManager getInstance() {
		if (fConnectionManager == null) {
			synchronized (ConnectionManager.class) {
				if (fConnectionManager == null) {
					fConnectionManager = new ConnectionManager();
				}
			}
		}
		return fConnectionManager;
	}
	/**
	 * Recup�re la DataSource
	 */
	private void initDataSource() {
		try {

			Context ctx = new InitialContext();
			fDataSource = (DataSource) ctx.lookup(DATASOURCE);
			System.out.println("Connection par datasource");
		} catch (NamingException e) {
			System.out.println("Erreur JNDI : " + e);
			return;
		}

	}

	/**
	 * Recup�re la DataSource
	 */
	private void initConnectionClassique() {

		try {
			Class.forName(DRIVER);

			if (LOGIN == null || (LOGIN != null && LOGIN.trim().length() < 1)) {
				DriverManager.getConnection(URL);
			} else {
				DriverManager.getConnection(URL, LOGIN, PASSWORD);
			}
			System.out.println("Connection classique");
		} catch (ClassNotFoundException e) {
			System.out.println("Impossible de charger le driver : " + e);
			return;
		} catch (SQLException e) {
			System.out.println("Impossible de recuperer une connection par le DriverManager : " + e);
			return;
		}
	}
}
