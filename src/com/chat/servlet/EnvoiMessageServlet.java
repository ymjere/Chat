package com.chat.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chat.DAO.ClientDAO;
import com.chat.model.Message;
import com.chat.model.User;

/**
 * Servlet implementation class EnvoiMessageServlet
 */
@WebServlet("/EnvoiMessageServlet")
public class EnvoiMessageServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ClientDAO client = new ClientDAO();
		try {
			HttpSession session = req.getSession(true);
			if (session.getAttribute("Client") != null) {
				User user = (User) session.getAttribute("Client");
				Message message = new Message(user, req.getParameter("message"), Date.);
				this.getServletContext().getRequestDispatcher("/pagePrincipal.jsp").forward(req, res);
			} else
				this.getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
