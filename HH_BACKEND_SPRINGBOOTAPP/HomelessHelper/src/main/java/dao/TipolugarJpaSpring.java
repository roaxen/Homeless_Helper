package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Tipolugar;

public interface TipolugarJpaSpring extends JpaRepository<Tipolugar, Integer> {

	@Query("SELECT t FROM Tipolugar t WHERE t.idTipolugar = ?1")
	Tipolugar findByIdTipoLugar(int idTipoLugar);
}
