// Source code is decompiled from a .class file using FernFlower decompiler.
package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Usuario;
import modelos.UsuarioDAO;

@WebServlet({ "/dashboard" })
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DashboardServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("usuario") != null) {
			Usuario usuario = (Usuario) session.getAttribute("usuario");

			try {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				Double saldo = usuarioDAO.getSaldo(usuario);
				request.setAttribute("saldo", saldo);
				request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
			} catch (Exception var7) {
				var7.printStackTrace();
				response.sendError(500, "Error al recuperar el saldo del usuario.");
			}

		} else {
			response.sendRedirect(request.getContextPath() + "/dispatcher?action=login");


		}
	}
}
