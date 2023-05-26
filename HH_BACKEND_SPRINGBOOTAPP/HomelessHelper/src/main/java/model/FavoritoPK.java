package model;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The primary key class for the favoritos database table.
 * 
 */
@Embeddable
public class FavoritoPK implements Serializable {

	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable = false, updatable = false)
	private String email;

	@Column(name = "id_lugar", insertable = false, updatable = false)
	private int idLugar;

	public FavoritoPK() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdLugar() {
		return this.idLugar;
	}

	public void setIdLugar(int idLugar) {
		this.idLugar = idLugar;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FavoritoPK)) {
			return false;
		}
		FavoritoPK castOther = (FavoritoPK) other;
		return this.email.equals(castOther.email) && (this.idLugar == castOther.idLugar);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.email.hashCode();
		hash = hash * prime + this.idLugar;

		return hash;
	}
	
	@Override
	public String toString() {
		return "FavoritoPK [email=" + email + ", idLugar=" + idLugar + "]";
	}
}