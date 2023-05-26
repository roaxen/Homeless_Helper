package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Lugarsugerido;

@Service
public interface LugarSugeridoJpaSpring extends JpaRepository<Lugarsugerido, Integer> {
	@Transactional
	@Modifying
	@Query("delete from Lugarsugerido l Where l.idLugarsugerido=?1")
	void removeById(int idLugar);

	@Query("SELECT l FROM Lugarsugerido l WHERE l.idTipolugar = :idTipolugar")
	List<Lugarsugerido> findLugaresByTipoLugar(@Param("idTipolugar") int idTipolugar);

	@Query("SELECT l FROM Lugarsugerido l WHERE l.emailResponsable = :email")
	List<Lugarsugerido> findLugaresByEmail(@Param("email") String email);

	@Query("SELECT l FROM Lugarsugerido l WHERE l.nombre LIKE CONCAT('%', :valor, '%') OR l.direccion LIKE CONCAT('%', :valor, '%') OR l.ubicacion LIKE CONCAT('%', :valor, '%') OR l.descripcion LIKE CONCAT('%', :valor, '%') OR l.email LIKE CONCAT('%', :valor, '%') OR l.emailResponsable LIKE CONCAT('%', :valor, '%')")
	List<Lugarsugerido> findLugaresByWord(@Param("valor") String valor);
}
