package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Lugar;

@Repository
public class LugarDaoImpl implements LugarDao {
	@Autowired
	LugarJpaSpring lugarDao;

	@Override
	public void addLugar(Lugar lugar) {
		// TODO: Implementar el método para agregar un Lugar.
		lugarDao.save(lugar);
	}

	@Override
	public void removeLugar(Lugar lugar) {
		// TODO: Implementar el método para eliminar un Lugar.
		lugarDao.delete(lugar);
	}

	@Override
	public List<Lugar> getLugar() {
		// TODO: Implementar el método para obtener todos los Lugares.
		return lugarDao.findAll();
	}

	@Override
	public void removeLugar(int idLugar) {
		// TODO: Implementar el método para eliminar un Lugar por su ID.
		lugarDao.deleteById(idLugar);
	}

	@Override
	public Lugar retrieveLugar(int idLugar) {
		// TODO: Implementar el método para obtener un Lugar por su ID.
		return lugarDao.findById(idLugar).orElse(null);
	}

	@Override
	public void updateLugar(Lugar lugar) {
		// TODO: Implementar el método para actualizar un Lugar.
		lugarDao.save(lugar);
	}

	@Override
	public List<Lugar> getLugaresPorTipo(int idTipoLugar) {
		// TODO: Implementar el método para obtener los Lugares por su tipo.
		return lugarDao.findLugaresByTipoLugar(idTipoLugar);
	}

	@Override
	public List<Lugar> findLugaresByWord(String valor) {
		// TODO: Implementar el método para buscar Lugares por una palabra clave.
		return lugarDao.findLugaresByWord(valor);
	}
}
