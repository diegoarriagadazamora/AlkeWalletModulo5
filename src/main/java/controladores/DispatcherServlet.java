package controladores;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Usuario;
import modelos.UsuarioDAO;

@WebServlet("/dispatcher")
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
                        } else {
                            response.sendRedirect(request.getContextPath() + "/dispatcher?action=login"); // Redireccionar al login si no hay sesión de usuario
                        }
                        break;
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
            // Si no hay acción proporcionada, redirigir al dashboard
            response.sendRedirect(request.getContextPath() + "/dispatcher?action=dashboard");
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

            UsuarioDAO usuarioDAO = null;
            try {
                usuarioDAO = new UsuarioDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                Usuario usu = usuarioDAO.findUser(usuario, password);
                if (usu != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", usu);
                    response.sendRedirect(request.getContextPath() + "/dispatcher?action=dashboard"); // Redireccionar al dashboard después del inicio de sesión exitoso
                } else {
                    request.setAttribute("error", "Usuario o contraseña incorrectos");
                    request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }
            } catch (Exception var9) {
                var9.printStackTrace();
                request.setAttribute("error", "Error al procesar la solicitud");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        } else {
            response.sendError(400, "La accion no es compatible con el metodo POST");
        }

    }
}
