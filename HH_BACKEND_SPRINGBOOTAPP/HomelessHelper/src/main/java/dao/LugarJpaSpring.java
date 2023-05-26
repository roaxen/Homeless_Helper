package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Lugar;

@Service
public interface LugarJpaSpring extends JpaRepository<Lugar, Integer> {
	@Transactional
	@Modifying
	@Query("delete from Lugar l Where l.idLugar=?1")
	void removeById(int idLugar);

	@Query("SELECT l FROM Lugar l WHERE l.idTipolugar = :idTipolugar")
	List<Lugar> findLugaresByTipoLugar(@Param("idTipolugar") int idTipolugar);

	@Query("SELECT l FROM Lugar l WHERE l.email = :email")
	List<Lugar> findLugaresByEmail(@Param("email") String email);

	@Query("SELECT l FROM Lugar l WHERE l.nombre LIKE CONCAT('%', :valor, '%') OR l.direccion LIKE CONCAT('%', :valor, '%') OR l.ubicacion LIKE CONCAT('%', :valor, '%') OR l.descripcion LIKE CONCAT('%', :valor, '%') OR l.email LIKE CONCAT('%', :valor, '%') OR l.emailResponsable LIKE CONCAT('%', :valor, '%')")
	List<Lugar> findLugaresByWord(@Param("valor") String valor);
}
