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

@WebServlet({ "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		if (usuario != null && !usuario.isEmpty() && password != null && !password.isEmpty()) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();

			try {
				Usuario usu = usuarioDAO.findUser(usuario, password);
				if (usu != null) {
					HttpSession session = request.getSession();
					session.setAttribute("usuario", usu);
					response.sendRedirect("dashboard.jsp");
				} else {
					response.sendRedirect("login.jsp?error=Usuario+o+contrase\u00f1a+incorrectos");
				}
			} catch (Exception var8) {
				var8.printStackTrace();
				response.sendRedirect("login.jsp?error=Error+al+procesar+la+solicitud");
			}

		} else {
			response.sendRedirect("login.jsp?error=Credenciales+inv\u00e1lidas");
		}
	}
}
