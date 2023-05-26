package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.LugarDao;
import model.Lugar;

@Service
public class LugarServiceImpl implements LugarService {

	@Autowired
	LugarDao lugarDao;

	/**
	 * Agrega un nuevo registro de lugar.
	 *
	 * @param lugar El objeto Lugar a agregar.
	 * @return true si se agregó correctamente, false de lo contrario.
	 */
	@Override
	public boolean addLugar(Lugar lugar) {
		if (lugarDao.retrieveLugar(lugar.getIdLugar()) == null) {
			lugarDao.addLugar(lugar);
			return true;
		}
		return false;
	}

	/**
	 * Obtiene todos los registros de lugares.
	 *
	 * @return Una lista de objetos Lugar.
	 */
	@Override
	public List<Lugar> retrieveLugar() {
		return lugarDao.getLugar();
	}

	/**
	 * Actualiza un registro de lugar.
	 *
	 * @param lugar El objeto Lugar a actualizar.
	 * @return true si se actualizó correctamente, false de lo contrario.
	 */
	@Override
	public boolean updateLugar(Lugar lugar) {
		if (lugarDao.retrieveLugar(lugar.getIdLugar()) != null) {
			lugarDao.addLugar(lugar);
			return true;
		}
		return false;
	}

	/**
	 * Elimina un registro de lugar.
	 *
	 * @param idLugar El ID del lugar a eliminar.
	 * @return true si se eliminó correctamente, false de lo contrario.
	 */
	@Override
	public boolean deleteLugar(int idLugar) {
		if (lugarDao.retrieveLugar(idLugar) != null) {
			lugarDao.removeLugar(idLugar);
			return true;
		}
		return false;
	}

	/**
	 * Obtiene un registro de lugar por su ID.
	 *
	 * @param idLugar El ID del lugar a obtener.
	 * @return El objeto Lugar correspondiente al ID especificado.
	 */
	@Override
	public Lugar retrieveLugar(int idLugar) {
		return lugarDao.retrieveLugar(idLugar);
	}

	/**
	 * Obtiene una lista de lugares por tipo de lugar.
	 *
	 * @param idTipoLugar El ID del tipo de lugar.
	 * @return Una lista de objetos Lugar que corresponden al tipo de lugar
	 *         especificado.
	 */
	@Override
	public List<Lugar> getLugaresPorTipo(int idTipoLugar) {
		return lugarDao.getLugaresPorTipo(idTipoLugar);
	}

	/**
	 * Busca lugares por palabra clave.
	 *
	 * @param valor La palabra clave a buscar.
	 * @return Una lista de objetos Lugar que coinciden con la palabra clave.
	 */
	@Override
	public List<Lugar> findLugaresByWord(String valor) {
		return lugarDao.findLugaresByWord(valor);
	}

}