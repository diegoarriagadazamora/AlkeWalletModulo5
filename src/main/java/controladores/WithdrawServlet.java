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

@WebServlet({ "/withdraw" })
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WithdrawServlet() {
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

				if (usuario.getSaldo() >= monto) {
					usuario.setSaldo(usuario.getSaldo() - monto);
					UsuarioDAO usuarioDAO = new UsuarioDAO();
					usuarioDAO.actualizarSaldoUsuario(usuario);
					session.setAttribute("usuario", usuario);
					response.sendRedirect("dashboard?exito=2");
				} else {
					response.sendRedirect("dashboard.jsp?error=Saldo+insuficiente+para+realizar+el+retiro");
				}
			} catch (NumberFormatException var8) {
				response.sendRedirect("dashboard.jsp?error=El+monto+debe+ser+un+n\u00famero+v\u00e1lido");
			} catch (Exception var9) {
				var9.printStackTrace();
				response.sendRedirect("dashboard.jsp?error=Error+al+procesar+el+retiro+de+fondos");
			}

		} else {
			response.sendRedirect("login.jsp");
		}
	}
}
