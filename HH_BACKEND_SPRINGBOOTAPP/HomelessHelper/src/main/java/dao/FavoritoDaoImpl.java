package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Favorito;

@Repository
public class FavoritoDaoImpl implements FavoritoDao {
	@Autowired
	FavoritoJpaSpring favoritoDao;

	@Override
	public List<Favorito> getFavoritos() {
		// TODO: Implementar el método para obtener todos los Favoritos.
		return favoritoDao.findAll();
	}

	@Override
	public List<Favorito> getFavorito(String email) {
		// TODO: Implementar el método para obtener los Favoritos de un usuario por su
		// dirección de correo electrónico.
		List<Favorito> favoritos = favoritoDao.findByEmail(email);
		return favoritos;
	}

	@Override
	public Boolean addFavorito(Favorito favorito) {
		// TODO: Implementar el método para agregar un Favorito.
		if (favoritoDao.save(favorito) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean deleteFavorito(Favorito favorito) {
		// TODO: Implementar el método para eliminar un Favorito.
		if (favoritoDao.findByEmail(favorito.getId().getEmail()) != null) {
			favoritoDao.delete(favorito);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkFavExists(Favorito fav) {
		// TODO: Implementar el método para verificar si un Favorito existe.
		if (favoritoDao.checkFavExists(fav.getId().getIdLugar(), fav.getId().getEmail()) != null) {
			return true;
		} else {
			return false;
		}
	}
}