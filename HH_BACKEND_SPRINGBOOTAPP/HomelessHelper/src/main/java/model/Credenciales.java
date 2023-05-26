package model;

public class Credenciales {

	String clave;
	String email;
	String new_clave;
	String valor;

	public Credenciales(String clave, String email, String new_clave, String valor) {
		super();
		this.clave = clave;
		this.email = email;
		this.new_clave = new_clave;
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Credenciales() {
		super();
	}

	public String getNew_clave() {
		return new_clave;
	}

	public void setNew_clave(String new_clave) {
		this.new_clave = new_clave;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Credenciales [clave=" + clave + ", email=" + email + ", new_clave=" + new_clave + ", valor=" + valor
				+ "]";
	}
}
