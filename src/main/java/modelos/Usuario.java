// Source code is decompiled from a .class file using FernFlower decompiler.
package modelos;

public class Usuario {
	private int id;
	private String usuario;
	private String password;
	private double saldo;

	public Usuario(int id, String usuario, String password, double saldo) {
		this.id = id;
		this.usuario = usuario;
		this.password = password;
		this.saldo = saldo;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getNombreUsuario() {
		return this.usuario;
	}
}
