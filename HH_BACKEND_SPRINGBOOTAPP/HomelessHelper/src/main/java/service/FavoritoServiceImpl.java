package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.FavoritoDao;
import model.Favorito;

@Service
public class FavoritoServiceImpl implements FavoritoService {

	@Autowired
	FavoritoDao favoritoDao;

	/**
	 * Obtiene todos los registros de favoritos.
	 *
	 * @return Una lista de objetos Favorito.
	 */
	@Override
	public List<Favorito> retrieveFavoritos() {
		return favoritoDao.getFavoritos();
	}

	/**
	 * Obtiene los registros de favoritos asociados a un correo electrónico.
	 *
	 * @param email El correo electrónico asociado a los favoritos.
	 * @return Una lista de objetos Favorito asociados al correo electrónico
	 *         especificado.
	 */
	@Override
	public List<Favorito> retrieveFavorito(String email) {
		return favoritoDao.getFavorito(email);
	}

	/**
	 * Agrega un nuevo registro de favorito.
	 *
	 * @param favorito El objeto Favorito a agregar.
	 * @return true si se agregó correctamente, false de lo contrario.
	 */
	@Override
	public Boolean addFavorito(Favorito favorito) {
		if (favoritoDao.addFavorito(favorito)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Elimina un registro de favorito.
	 *
	 * @param favorito El objeto Favorito a eliminar.
	 * @return true si se eliminó correctamente, false de lo contrario.
	 */
	@Override
	public Boolean deleteFavorito(Favorito favorito) {
		return favoritoDao.deleteFavorito(favorito);
	}

	/**
	 * Verifica si un favorito existe.
	 *
	 * @param fav El objeto Favorito a verificar.
	 * @return true si el favorito existe, false de lo contrario.
	 */
	@Override
	public boolean checkFavExists(Favorito fav) {
		return favoritoDao.checkFavExists(fav);
	}

}
