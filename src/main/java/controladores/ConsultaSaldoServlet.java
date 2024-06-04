// Source code is decompiled from a .class file using FernFlower decompiler.
package controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Usuario;
import modelos.UsuarioDAO;

@WebServlet({ "/consultaSaldo" })
public class ConsultaSaldoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConsultaSaldoServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session == null || session.getAttribute("usuario") == null) {
				response.sendRedirect("login.jsp");
				return;
			}

			Usuario usuario = (Usuario) session.getAttribute("usuario");
			String nombreUsuario = usuario.getNombreUsuario();
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			double saldo = usuarioDAO.getSaldo(usuario);
			request.setAttribute("saldo", saldo);
			request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
		} catch (SQLException var9) {
			Logger.getLogger(ConsultaSaldoServlet.class.getName()).log(Level.SEVERE, (String) null, var9);
		}

	}
}
