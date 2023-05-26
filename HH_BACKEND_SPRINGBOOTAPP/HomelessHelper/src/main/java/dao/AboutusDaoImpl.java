package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Aboutus;

@Repository

public class AboutusDaoImpl implements AboutusDao {
	@Autowired
	AboutusJpaSpring aboutusDao;

	/**
	 * Guarda un objeto Aboutus en la base de datos.
	 * 
	 * @param aboutus El objeto Aboutus a guardar.
	 */
	@Override
	public void addAboutus(Aboutus aboutus) {
		aboutusDao.save(aboutus);
	}

	/**
	 * Elimina un objeto Aboutus de la base de datos.
	 * 
	 * @param aboutus El objeto Aboutus a eliminar.
	 */
	@Override
	public void removeAboutus(Aboutus aboutus) {
		aboutusDao.delete(aboutus);
	}

	/**
	 * Obtiene una lista de todos los objetos Aboutus en la base de datos.
	 * 
	 * @return Lista de objetos Aboutus.
	 */
	@Override
	public List<Aboutus> getAboutus() {
		return aboutusDao.findAll();
	}

	/**
	 * Elimina un objeto Aboutus de la base de datos mediante su ID.
	 * 
	 * @param id El ID del objeto Aboutus a eliminar.
	 */
	@Override
	public void removeAboutus(int id) {
		aboutusDao.deleteById(id);
	}

	/**
	 * Recupera un objeto Aboutus de la base de datos mediante su ID.
	 * 
	 * @param id El ID del objeto Aboutus a recuperar.
	 * @return El objeto Aboutus correspondiente al ID, o null si no se encuentra.
	 */
	
	@Override
	public Aboutus retrieveAboutus(int id) {
		return aboutusDao.findById(id).orElse(null);
	}

	/**
	 * Actualiza un objeto Aboutus en la base de datos.
	 * 
	 * @param aboutus El objeto Aboutus a actualizar.
	 */
	
	@Override
	public void updateAboutus(Aboutus aboutus) {
		aboutusDao.save(aboutus);
	}
}
