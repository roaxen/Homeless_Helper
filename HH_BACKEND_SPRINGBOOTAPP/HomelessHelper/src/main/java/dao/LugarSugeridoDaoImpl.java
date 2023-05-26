package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Lugarsugerido;

@Repository
public class LugarSugeridoDaoImpl implements LugarSugeridoDao {
	@Autowired
	LugarSugeridoJpaSpring lugarDao;

	/**
	 * Agrega un nuevo lugar sugerido.
	 * 
	 * @param lugar El lugar sugerido a agregar.
	 */
	@Override
	public void addLugarsugerido(Lugarsugerido lugar) {
		lugarDao.save(lugar);
	}

	/**
	 * Elimina un lugar sugerido.
	 * 
	 * @param lugar El lugar sugerido a eliminar.
	 */
	@Override
	public void removeLugarsugerido(Lugarsugerido lugar) {
		lugarDao.delete(lugar);
	}

	/**
	 * Obtiene todos los lugares sugeridos.
	 * 
	 * @return Una lista de lugares sugeridos.
	 */
	@Override
	public List<Lugarsugerido> getLugarsugerido() {
		return lugarDao.findAll();
	}

	/**
	 * Elimina un lugar sugerido por su ID.
	 * 
	 * @param idLugar El ID del lugar sugerido a eliminar.
	 */
	@Override
	public void removeLugarsugerido(int idLugar) {
		lugarDao.deleteById(idLugar);
	}

	/**
	 * Obtiene un lugar sugerido por su ID.
	 * 
	 * @param idLugar El ID del lugar sugerido a obtener.
	 * @return El lugar sugerido encontrado, o null si no existe.
	 */
	@Override
	public Lugarsugerido retrieveLugarsugerido(int idLugar) {
		return lugarDao.findById(idLugar).orElse(null);
	}

	/**
	 * Actualiza un lugar sugerido.
	 * 
	 * @param lugar El lugar sugerido a actualizar.
	 */
	@Override
	public void updateLugarsugerido(Lugarsugerido lugar) {
		lugarDao.save(lugar);
	}

	/**
	 * Obtiene una lista de lugares sugeridos por su tipo de lugar.
	 * 
	 * @param idTipoLugar El ID del tipo de lugar.
	 * @return Una lista de lugares sugeridos del tipo especificado.
	 */
	@Override
	public List<Lugarsugerido> getLugaresPorTipo(int idTipoLugar) {
		return lugarDao.findLugaresByTipoLugar(idTipoLugar);
	}

	/**
	 * Busca lugares sugeridos que coincidan con una palabra clave.
	 * 
	 * @param valor La palabra clave a buscar.
	 * @return Una lista de lugares sugeridos que coinciden con la palabra clave.
	 */
	@Override
	public List<Lugarsugerido> findLugaresByWord(String valor) {
		return lugarDao.findLugaresByWord(valor);
	}
}
