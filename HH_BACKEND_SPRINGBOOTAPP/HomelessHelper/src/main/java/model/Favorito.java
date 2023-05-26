package model;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the favoritos database table.
 * 
 */
@Entity
@Table(name = "favoritos")
@NamedQuery(name = "Favorito.findAll", query = "SELECT f FROM Favorito f")
public class Favorito implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FavoritoPK id;

	public Favorito() {
	}

	public FavoritoPK getId() {
		return this.id;
	}

	public void setId(FavoritoPK id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Favorito [id=" + id + "]";
	}
}