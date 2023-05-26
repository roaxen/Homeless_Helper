package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import model.Usuario;

public interface UsuarioJpaSpring extends JpaRepository<Usuario, String> {

	@Query("SELECT u FROM Usuario u WHERE u.email = ?1 and u.clave =?2")
	Usuario checkUserExists(String email, String clave);

	@Modifying
	@Transactional
	@Query("DELETE FROM Lugar l WHERE l.emailResponsable = ?1")
	void removeLugares(String email);

	@Modifying
	@Transactional
	@Query("DELETE FROM Favorito f WHERE f.id.email = ?1")
	void removeFavoritos(String email);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Empleo e WHERE e.email = ?1")
	void removeEmpleos(String email);

	@Modifying
	@Transactional
	@Query("DELETE FROM Usuario u WHERE u.email = ?1")
	void removeByEmail(String email);

}
