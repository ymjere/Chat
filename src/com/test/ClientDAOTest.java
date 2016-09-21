package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.chat.DAO.ClientDAO;
import com.chat.model.User;

public class ClientDAOTest {

	@Test
	public void testLoginClientDone() {
		ClientDAO client = new ClientDAO();
		// client.addClient("Toto", "toto");
		// client.getClient();
		assertEquals(true, client.loginClient("Toto", "toto"));
	}

	@Test
	public void testLoginClientFail() {
		ClientDAO client = new ClientDAO();
		assertEquals(false, client.loginClient("lkjsfh", "fl"));
	}

	@Test
	public void testGetClientDone() {
		ClientDAO client = new ClientDAO();
		// client.addClient("Toto", "toto");
		// client.getClient();
		if (!(client.getClient("Toto") instanceof User)) {
			assertEquals(false, true);
		}
	}

	@Test
	public void testGetClientFail() {
		ClientDAO client = new ClientDAO();
		assertEquals(null, client.getClient("lkjsfh"));
	}
	
	@Test
	public void testChangePseudo(){
		ClientDAO client = new ClientDAO();
		client.getClient();
		client.changePseudo("Jeremy", "Jerem");
		System.out.println("\n\n");
		client.getClient();
	}
}
