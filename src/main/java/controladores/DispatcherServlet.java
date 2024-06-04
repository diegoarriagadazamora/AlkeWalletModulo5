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

@WebServlet({ "/dispatcher" })
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DispatcherServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && !action.isEmpty()) {
			try {
				switch (action) {
				case "login":
					request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
					break;
				case "dashboard":
					HttpSession session = request.getSession(false);
					if (session != null && session.getAttribute("usuario") != null) {
						request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
						break;
					}

					response.sendRedirect("login.jsp");
					return;
				case "consultarSaldo":
					request.getRequestDispatcher("/ConsultarSaldoServlet").forward(request, response);
					break;
				default:
					response.sendError(404, "Accion no encontrada");
				}
			} catch (Exception var7) {
				response.sendError(500, "Error al procesar la solicitud");
			}

		} else {
			response.sendError(400, "La accion no puede estar vacia");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("login".equals(action)) {
			String usuario = request.getParameter("usuario");
			String password = request.getParameter("password");
			if (usuario == null || usuario.isEmpty() || password == null || password.isEmpty()) {
				request.setAttribute("error", "Credenciales inv\u00e1lidas");
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
				return;
			}

			UsuarioDAO usuarioDAO = new UsuarioDAO();

			try {
				Usuario usu = usuarioDAO.findUser(usuario, password);
				if (usu != null) {
					HttpSession session = request.getSession();
					session.setAttribute("usuario", usu);
					response.sendRedirect("dispatcher?action=dashboard");
				} else {
					request.setAttribute("error", "Usuario o contrase\u00f1a incorrectos");
					request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
				}
			} catch (Exception var9) {
				var9.printStackTrace();
				request.setAttribute("error", "Error al procesar la solicitud");
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			}
		} else {
			response.sendError(400, "La acci\u00f3n no es compatible con el m\u00e9todo POST");
		}

	}
}
