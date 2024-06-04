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

@WebServlet({ "/deposit" })
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DepositServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("usuario") != null) {
			Usuario usuario = (Usuario) session.getAttribute("usuario");

			try {
				double monto = Double.parseDouble(request.getParameter("monto"));
				if (monto <= 0.0) {
					response.sendRedirect("dashboard.jsp?error=El+monto+debe+ser+positivo");
					return;
				}

				usuario.setSaldo(usuario.getSaldo() + monto);
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				usuarioDAO.actualizarSaldoUsuario(usuario);
				session.setAttribute("usuario", usuario);
				response.sendRedirect(request.getContextPath() + "/dashboard?exito=1");
			} catch (NumberFormatException var8) {
				response.sendRedirect("dashboard.jsp?error=El+monto+debe+ser+un+numero+valido");
			} catch (Exception var9) {
				var9.printStackTrace();
				response.sendRedirect("dashboard.jsp?error=Error+al+procesar+el+deposito");
			}

		} else {
			response.sendRedirect("login.jsp");
		}
	}
}