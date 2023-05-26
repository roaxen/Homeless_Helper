package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TipolugarDao;
import model.Tipolugar;

@Service
public class TipolugarServiceImpl implements TipolugarService {

	@Autowired
	TipolugarDao tipolugardao;

	/**
	 * Obtiene un registro de tipo de lugar por su ID.
	 *
	 * @param idTipoLugar El ID del tipo de lugar a obtener.
	 * @return El objeto Tipolugar correspondiente al ID especificado.
	 */
	@Override
	public Tipolugar getTipoLugar(int idTipoLugar) {
		return tipolugardao.getTipoLugar(idTipoLugar);
	}

	/**
	 * Obtiene todos los registros de tipo de lugar.
	 *
	 * @return Una lista de objetos Tipolugar.
	 */
	@Override
	public List<Tipolugar> retrieveTipolugar() {
		return tipolugardao.retrieveTipolugar();
	}

}
