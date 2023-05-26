package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Empleo;

@Repository
public class EmpleoDaoImpl implements EmpleoDao {
	@Autowired
	EmpleoJpaSpring empleoDao;

	/**
	 * Guarda un objeto Empleo en la base de datos.
	 *
	 * @param empleo El objeto Empleo a guardar.
	 */
	@Override
	public void addEmpleo(Empleo empleo) {
		empleoDao.save(empleo);
	}

	/**
	 * Elimina un objeto Empleo de la base de datos.
	 *
	 * @param empleo El objeto Empleo a eliminar.
	 */
	@Override
	public void removeEmpleo(Empleo empleo) {
		empleoDao.delete(empleo);
	}

	/**
	 * Obtiene una lista de todos los objetos Empleo en la base de datos.
	 *
	 * @return Lista de objetos Empleo.
	 */
	@Override
	public List<Empleo> getEmpleo() {
		return empleoDao.findAll();
	}

	/**
	 * Elimina un objeto Empleo de la base de datos mediante su ID.
	 *
	 * @param idEmpleo El ID del objeto Empleo a eliminar.
	 */
	@Override
	public void removeEmpleo(int idEmpleo) {
		empleoDao.deleteById(idEmpleo);
	}

	/**
	 * Recupera un objeto Empleo de la base de datos mediante su ID.
	 *
	 * @param idEmpleo El ID del objeto Empleo a recuperar.
	 * @return El objeto Empleo correspondiente al ID, o null si no se encuentra.
	 */
	@Override
	public Empleo retrieveEmpleo(int idEmpleo) {
		return empleoDao.findById(idEmpleo).orElse(null);
	}

	/**
	 * Actualiza un objeto Empleo en la base de datos.
	 *
	 * @param empleo El objeto Empleo a actualizar.
	 */
	@Override
	public void updateEmpleo(Empleo empleo) {
		empleoDao.save(empleo);
	}

	/**
	 * Obtiene una lista de objetos Empleo por ID de tipo de empleo.
	 *
	 * @param idTipoEmpleo El ID del tipo de empleo.
	 * @return Lista de objetos Empleo filtrados por ID de tipo de empleo.
	 */
	@Override
	public List<Empleo> getEmpleosPorTipo(int idTipoEmpleo) {
		return empleoDao.findEmpleosByTipoEmpleo(idTipoEmpleo);
	}

	/**
	 * Busca objetos Empleo por palabra clave.
	 *
	 * @param valor La palabra clave a buscar.
	 * @return Lista de objetos Empleo que coinciden con la palabra clave.
	 */
	@Override
	public List<Empleo> findEmpleosByWord(String valor) {
		return empleoDao.findEmpleosByWord(valor);
	}

	/**
	 * Obtiene una lista de objetos Empleo por dirección de correo electrónico.
	 *
	 * @param email La dirección de correo electrónico.
	 * @return Lista de objetos Empleo filtrados por dirección de correo
	 *         electrónico.
	 */
	@Override
	public List<Empleo> findEmpleosByEmail(String email) {
		return empleoDao.findEmpleosByEmail(email);
	}

	/**
	 * Obtiene una lista de objetos Empleo por dirección de correo electrónico.
	 *
	 * @param email La dirección de correo electrónico.
	 * @return Lista de objetos Empleo filtrados por dirección de correo
	 *         electrónico.
	 */
	@Override
	public List<Empleo> getEmpleos(String email) {
		return empleoDao.getEmpleos(email);
	}

}
