package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TipoempleoDao;
import model.Tipoempleo;

@Service
public class TipoempleoServiceImpl implements TipoempleoService {

	@Autowired
	TipoempleoDao tipoempleoDao;

	/**
	 * Obtiene todos los registros de tipo de empleo.
	 *
	 * @return Una lista de objetos Tipoempleo.
	 */
	@Override
	public List<Tipoempleo> retrieveTipoempleo() {
		return tipoempleoDao.retrieveTipoempleo();
	}

	/**
	 * Obtiene un registro de tipo de empleo por su ID.
	 *
	 * @param idTipoempleo El ID del tipo de empleo a obtener.
	 * @return El objeto Tipoempleo correspondiente al ID especificado.
	 */
	@Override
	public Tipoempleo getTipoEmpleo(int idTipoempleo) {
		return tipoempleoDao.getTipoempleo(idTipoempleo);
	}
}
