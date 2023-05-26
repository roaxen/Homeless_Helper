package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Tipolugar;

@Repository
public class TipolugarDaoImpl implements TipolugarDao {

	@Autowired
	TipolugarJpaSpring tipolugarJpaSpring;

	/**
	 * Obtiene un tipo de lugar por su ID.
	 * 
	 * @param idTipoLugar El ID del tipo de lugar a obtener.
	 * @return El tipo de lugar encontrado, o null si no existe.
	 */
	@Override
	public Tipolugar getTipoLugar(int idTipoLugar) {
		return tipolugarJpaSpring.findById(idTipoLugar).orElse(null);
	}

	/**
	 * Obtiene todos los tipos de lugar.
	 * 
	 * @return Una lista de tipos de lugar.
	 */
	@Override
	public List<Tipolugar> retrieveTipolugar() {
		return tipolugarJpaSpring.findAll();
	}
}
