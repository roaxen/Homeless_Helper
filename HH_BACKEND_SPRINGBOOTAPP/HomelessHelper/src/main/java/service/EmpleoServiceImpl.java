package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.EmpleoDao;
import model.Empleo;

@Service
public class EmpleoServiceImpl implements EmpleoService {

	@Autowired
	EmpleoDao empleoDao;

	/**
	 * Agrega un nuevo registro de empleo.
	 *
	 * @param empleo El objeto Empleo a agregar.
	 * @return true si se agregó correctamente, false si ya existe un registro con
	 *         el mismo ID.
	 */
	@Override
	public boolean addEmpleo(Empleo empleo) {
		if (empleoDao.retrieveEmpleo(empleo.getId()) == null) {
			empleoDao.addEmpleo(empleo);
			return true;
		}
		return false;
	}

	/**
	 * Obtiene todos los registros de empleo.
	 *
	 * @return Una lista de objetos Empleo.
	 */
	@Override
	public List<Empleo> retrieveEmpleo() {
		return empleoDao.getEmpleo();
	}

	/**
	 * Actualiza un registro de empleo existente.
	 *
	 * @param empleo El objeto Empleo actualizado.
	 * @return true si se actualizó correctamente, false si no se encontró el
	 *         registro.
	 */
	@Override
	public boolean updateEmpleo(Empleo empleo) {
		if (empleoDao.retrieveEmpleo(empleo.getId()) != null) {
			empleoDao.addEmpleo(empleo);
			return true;
		}
		return false;
	}

	/**
	 * Elimina un registro de empleo por su ID.
	 *
	 * @param idEmpleo El ID del registro a eliminar.
	 * @return true si se eliminó correctamente, false si no se encontró el
	 *         registro.
	 */
	@Override
	public boolean deleteEmpleo(int idEmpleo) {
		if (empleoDao.retrieveEmpleo(idEmpleo) != null) {
			empleoDao.removeEmpleo(idEmpleo);
			return true;
		}
		return false;
	}

	/**
	 * Obtiene un registro de empleo por su ID.
	 *
	 * @param idEmpleo El ID del registro a obtener.
	 * @return El objeto Empleo encontrado, o null si no se encontró ningún registro
	 *         con ese ID.
	 */
	@Override
	public Empleo retrieveEmpleo(int idEmpleo) {
		return empleoDao.retrieveEmpleo(idEmpleo);
	}

	/**
	 * Obtiene una lista de empleos filtrados por el tipo de empleo.
	 *
	 * @param idTipoEmpleo El ID del tipo de empleo.
	 * @return Una lista de objetos Empleo que pertenecen al tipo de empleo
	 *         especificado.
	 */
	@Override
	public List<Empleo> getEmpleosPorTipo(int idTipoEmpleo) {
		return empleoDao.getEmpleosPorTipo(idTipoEmpleo);
	}

	/**
	 * Busca empleos que coincidan con un valor específico.
	 *
	 * @param valor El valor a buscar.
	 * @return Una lista de objetos Empleo que coinciden con el valor especificado.
	 */
	@Override
	public List<Empleo> findEmpleosByWord(String valor) {
		return empleoDao.findEmpleosByWord(valor);
	}

	/**
	 * Obtiene una lista de empleos asociados a un correo electrónico.
	 *
	 * @param email El correo electrónico asociado a los empleos.
	 * @return Una lista de objetos Empleo asociados al correo electrónico
	 *         especificado.
	 */
	@Override
	public List<Empleo> findEmpleosByEmail(String email) {
		return empleoDao.findEmpleosByEmail(email);
	}

	/**
	 * Obtiene una lista de empleos asociados a un correo electrónico.
	 *
	 * @param email El correo electrónico asociado a los empleos.
	 * @return Una lista de objetos Empleo asociados al correo electrónico
	 *         especificado.
	 */

	@Override
	public List<Empleo> retrieveEmpleos(String email) {
		return empleoDao.getEmpleos(email);
	}
}
