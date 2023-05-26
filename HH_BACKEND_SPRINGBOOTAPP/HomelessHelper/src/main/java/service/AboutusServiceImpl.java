package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AboutusDao;
import model.Aboutus;

@Service
public class AboutusServiceImpl implements AboutusService {

	@Autowired
	AboutusDao aboutusDao;

	/**
	 * Agrega un nuevo registro "About Us".
	 *
	 * @param aboutus El objeto Aboutus a agregar.
	 * @return true si se agregó correctamente, false si ya existe un registro con
	 *         el mismo ID.
	 */
	@Override
	public boolean addAboutus(Aboutus aboutus) {
		if (aboutusDao.retrieveAboutus(aboutus.getId()) == null) {
			aboutusDao.addAboutus(aboutus);
			return true;
		}
		return false;
	}

	/**
	 * Obtiene todos los registros "About Us".
	 *
	 * @return Una lista de objetos Aboutus.
	 */
	@Override
	public List<Aboutus> retrieveAboutus() {
		return aboutusDao.getAboutus();
	}

	/**
	 * Actualiza un registro "About Us" existente.
	 *
	 * @param aboutus El objeto Aboutus actualizado.
	 * @return true si se actualizó correctamente, false si no se encontró el
	 *         registro.
	 */
	@Override
	public boolean updateAboutus(Aboutus aboutus) {
		if (aboutusDao.retrieveAboutus(aboutus.getId()) != null) {
			aboutusDao.updateAboutus(aboutus);
			return true;
		}
		return false;
	}

	/**
	 * Elimina un registro "About Us" por su ID.
	 *
	 * @param id El ID del registro a eliminar.
	 * @return true si se eliminó correctamente, false si no se encontró el
	 *         registro.
	 */
	@Override
	public boolean deleteAboutus(int id) {
		if (aboutusDao.retrieveAboutus(id) != null) {
			aboutusDao.removeAboutus(id);
			return true;
		}
		return false;
	}

	/**
	 * Obtiene un registro "About Us" por su ID.
	 *
	 * @param id El ID del registro a obtener.
	 * @return El objeto Aboutus encontrado, o null si no se encontró ningún
	 *         registro con ese ID.
	 */
	@Override
	public Aboutus retrieveAboutus(int id) {
		return aboutusDao.retrieveAboutus(id);
	}

}
