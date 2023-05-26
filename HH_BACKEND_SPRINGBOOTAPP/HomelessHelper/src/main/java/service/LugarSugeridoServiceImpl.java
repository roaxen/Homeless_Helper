package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.LugarSugeridoDao;
import model.Lugarsugerido;

@Service
public class LugarSugeridoServiceImpl implements LugarSugeridoService {

	@Autowired
	LugarSugeridoDao lugarDao;

	/**
	 * Agrega un nuevo registro de lugar sugerido.
	 *
	 * @param lugar El objeto Lugarsugerido a agregar.
	 * @return true si se agregó correctamente, false de lo contrario.
	 */
	@Override
	public boolean addLugarsugerido(Lugarsugerido lugar) {
		if (lugarDao.retrieveLugarsugerido(lugar.getIdLugarsugerido()) == null) {
			lugarDao.addLugarsugerido(lugar);
			return true;
		}
		return false;
	}

	/**
	 * Obtiene todos los registros de lugares sugeridos.
	 *
	 * @return Una lista de objetos Lugarsugerido.
	 */
	@Override
	public List<Lugarsugerido> retrieveLugarsugerido() {
		return lugarDao.getLugarsugerido();
	}

	/**
	 * Actualiza un registro de lugar sugerido.
	 *
	 * @param lugar El objeto Lugarsugerido a actualizar.
	 * @return true si se actualizó correctamente, false de lo contrario.
	 */
	@Override
	public boolean updateLugarsugerido(Lugarsugerido lugar) {
		if (lugarDao.retrieveLugarsugerido(lugar.getIdLugarsugerido()) != null) {
			lugarDao.addLugarsugerido(lugar);
			return true;
		}
		return false;
	}

	/**
	 * Elimina un registro de lugar sugerido.
	 *
	 * @param idLugarsugerido El ID del lugar sugerido a eliminar.
	 * @return true si se eliminó correctamente, false de lo contrario.
	 */
	@Override
	public boolean deleteLugarsugerido(int idLugarsugerido) {
		if (lugarDao.retrieveLugarsugerido(idLugarsugerido) != null) {
			lugarDao.removeLugarsugerido(idLugarsugerido);
			return true;
		}
		return false;
	}

	/**
	 * Obtiene un registro de lugar sugerido por su ID.
	 *
	 * @param idLugarsugerido El ID del lugar sugerido a obtener.
	 * @return El objeto Lugarsugerido correspondiente al ID especificado.
	 */
	@Override
	public Lugarsugerido retrieveLugarsugerido(int idLugarsugerido) {
		return lugarDao.retrieveLugarsugerido(idLugarsugerido);
	}

	/**
	 * Obtiene una lista de lugares sugeridos por tipo de lugar.
	 *
	 * @param idTipoLugarsugerido El ID del tipo de lugar sugerido.
	 * @return Una lista de objetos Lugarsugerido que corresponden al tipo de lugar
	 *         sugerido especificado.
	 */
	@Override
	public List<Lugarsugerido> getLugaresPorTipo(int idTipoLugarsugerido) {
		return lugarDao.getLugaresPorTipo(idTipoLugarsugerido);
	}

	/**
	 * Busca lugares sugeridos por palabra clave.
	 *
	 * @param valor La palabra clave a buscar.
	 * @return Una lista de objetos Lugarsugerido que coinciden con la palabra
	 *         clave.
	 */
	@Override
	public List<Lugarsugerido> findLugaresByWord(String valor) {
		return lugarDao.findLugaresByWord(valor);
	}

}