package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Favorito;
import model.FavoritoPK;

public interface FavoritoJpaSpring extends JpaRepository<Favorito, FavoritoPK> {

	@Query("SELECT f FROM Favorito f WHERE f.id.email = ?1")
	List<Favorito> findByEmail(String email);

	@Query("SELECT f FROM Favorito f WHERE f.id.idLugar = :id and f.id.email = :email")
	Favorito checkFavExists(@Param("id")int id,@Param("email") String email);
}	