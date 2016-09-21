package com.chat.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chat.DAO.ClientDAO;
import com.chat.model.User;

/**
 * Servlet implementation class DeconnectionServlet
 */
@WebServlet("/DeconnectionServlet")
public class DeconnectionServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ClientDAO client = new ClientDAO();
		try {
			HttpSession session = req.getSession(true);
			session.setAttribute("Client", null);
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
