package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Tipoempleo;

@Repository
public class TipoempleoDaoImpl implements TipoempleoDao {

	@Autowired
	TipoempleoJpaSpring tipoempleoDao;

	/**
	 * Obtiene todos los tipos de empleo.
	 * 
	 * @return Una lista de tipos de empleo.
	 */
	@Override
	public List<Tipoempleo> retrieveTipoempleo() {
		return tipoempleoDao.findAll();
	}

	/**
	 * Obtiene un tipo de empleo por su ID.
	 * 
	 * @param idTipoempleo El ID del tipo de empleo a obtener.
	 * @return El tipo de empleo encontrado, o null si no existe.
	 */
	@Override
	public Tipoempleo getTipoempleo(int idTipoempleo) {
		return tipoempleoDao.findById(idTipoempleo).orElse(null);
	}
}
