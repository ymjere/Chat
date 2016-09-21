package com.test;

import org.junit.Test;

import com.chat.DAO.MessageDAO;

public class MessageDAOTest {
	
	@Test
	public void testAddMessage(){
		MessageDAO mes = new MessageDAO();
		mes.addMessage("Pikachu", "Tonnerre");
	}
	
	@Test
	public void testGetMessage(){
		
	}
}
