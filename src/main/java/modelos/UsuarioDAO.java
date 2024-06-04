// Source code is decompiled from a .class file using FernFlower decompiler.
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
	private final String jdbcURL = "jdbc:mysql://localhost:3306/AlkeWalletJSP?serverTimezone=UTC";
	private final String jdbcUsername = "root";
	private final String jdbcPassword = "5W@rdf15";
	private Connection jdbcConnection;

	public UsuarioDAO() {
	}

	protected Connection connect() throws SQLException {
		try {
			if (this.jdbcConnection == null || this.jdbcConnection.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				this.jdbcConnection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/AlkeWalletJSP?serverTimezone=UTC", "PONER-TU-USUARIO",
						"poner-tu-pass");
				System.out.println("Conexi\u00f3n a la base de datos establecida correctamente.");
			}
		} catch (SQLException | ClassNotFoundException var2) {
			var2.printStackTrace();
			throw new SQLException("Error al conectar a la base de datos: " + var2.getMessage());
		}

		return this.jdbcConnection;
	}

	protected void disconnect() throws SQLException {
		if (this.jdbcConnection != null && !this.jdbcConnection.isClosed()) {
			this.jdbcConnection.close();
		}

	}

	public Usuario findUser(String usuario, String password) throws SQLException {
		String sql = "SELECT * FROM usuarios WHERE usuario=? AND password=SHA2(?, 256)";
		this.connect();
		Usuario user = null;

		try {
			PreparedStatement statement = this.jdbcConnection.prepareStatement(sql);

			try {
				statement.setString(1, usuario);
				statement.setString(2, password);
				ResultSet resultSet = statement.executeQuery();

				try {
					if (resultSet.next()) {
						int id = resultSet.getInt("id");
						double saldo = resultSet.getDouble("saldo");
						user = new Usuario(id, usuario, password, saldo);
					}
				} catch (Throwable var18) {
					if (resultSet != null) {
						try {
							resultSet.close();
						} catch (Throwable var17) {
							var18.addSuppressed(var17);
						}
					}

					throw var18;
				}

				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Throwable var19) {
				if (statement != null) {
					try {
						statement.close();
					} catch (Throwable var16) {
						var19.addSuppressed(var16);
					}
				}

				throw var19;
			}

			if (statement != null) {
				statement.close();
			}
		} finally {
			this.disconnect();
		}

		return user;
	}

	public void retirarFondos(Usuario usuario, double monto) throws SQLException {
	}

	private void sha2(String password) {
	}

	public void actualizarSaldoUsuario(Usuario usuario) {
		String sql = "UPDATE usuarios SET saldo=? WHERE id=?";

		try {
			this.connect();

			try {
				PreparedStatement statement = this.jdbcConnection.prepareStatement(sql);

				try {
					statement.setDouble(1, usuario.getSaldo());
					statement.setInt(2, usuario.getId());
					int rowsUpdated = statement.executeUpdate();
					System.out.println("Filas actualizadas: " + rowsUpdated);
				} catch (Throwable var12) {
					if (statement != null) {
						try {
							statement.close();
						} catch (Throwable var11) {
							var12.addSuppressed(var11);
						}
					}

					throw var12;
				}

				if (statement != null) {
					statement.close();
				}
			} finally {
				this.disconnect();
			}
		} catch (SQLException var14) {
			var14.printStackTrace();
		}

	}

	public double getSaldo(Usuario usuario) throws SQLException {
		double saldo = 0.0;
		String sql = "SELECT saldo FROM usuarios WHERE usuario = ?";

		try {
			Connection con = this.connect();

			try {
				PreparedStatement ps = con.prepareStatement(sql);

				try {
					ps.setString(1, usuario.getNombreUsuario());
					ResultSet rs = ps.executeQuery();

					try {
						if (rs.next()) {
							saldo = rs.getDouble("saldo");
						}
					} catch (Throwable var13) {
						if (rs != null) {
							try {
								rs.close();
							} catch (Throwable var12) {
								var13.addSuppressed(var12);
							}
						}

						throw var13;
					}

					if (rs != null) {
						rs.close();
					}
				} catch (Throwable var14) {
					if (ps != null) {
						try {
							ps.close();
						} catch (Throwable var11) {
							var14.addSuppressed(var11);
						}
					}

					throw var14;
				}

				if (ps != null) {
					ps.close();
				}
			} catch (Throwable var15) {
				if (con != null) {
					try {
						con.close();
					} catch (Throwable var10) {
						var15.addSuppressed(var10);
					}
				}

				throw var15;
			}

			if (con != null) {
				con.close();
			}

			return saldo;
		} catch (SQLException var16) {
			var16.printStackTrace();
			throw var16;
		}
	}
}