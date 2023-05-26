package model;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the lugar database table.
 * 
 */
@Entity
@NamedQuery(name = "Lugar.findAll", query = "SELECT l FROM Lugar l")
public class Lugar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_lugar")
	private int idLugar;

	@Lob
	private String descripcion;

	private String direccion;

	private String email;
	
	@Lob
	private byte[] imagen;

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	@Column(name = "email_responsable")
	private String emailResponsable;

	@Column(name = "id_tipolugar")
	private int idTipolugar;

	private String nombre;

	private int telefono;

	private String ubicacion;

	private int valoracion;

	public Lugar() {
	}

	public int getIdLugar() {
		return this.idLugar;
	}

	public void setIdLugar(int idLugar) {
		this.idLugar = idLugar;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailResponsable() {
		return this.emailResponsable;
	}

	public void setEmailResponsable(String emailResponsable) {
		this.emailResponsable = emailResponsable;
	}

	public int getIdTipolugar() {
		return this.idTipolugar;
	}

	public void setIdTipolugar(int idTipolugar) {
		this.idTipolugar = idTipolugar;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getValoracion() {
		return this.valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	@Override
	public String toString() {
		return "Lugar [idLugar=" + idLugar + ", descripcion=" + descripcion + ", direccion=" + direccion + ", email="
				+ email + ", emailResponsable=" + emailResponsable + ", idTipolugar=" + idTipolugar + ", nombre="
				+ nombre + ", telefono=" + telefono + ", ubicacion=" + ubicacion + ", valoracion=" + valoracion + "]";
	}
}