package dao;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Favorito;

@Service
public interface FavoritoDao {

	List<Favorito> getFavoritos();

	List<Favorito> getFavorito(String email);

	Boolean addFavorito(Favorito favorito);

	Boolean deleteFavorito(Favorito favorito);

	boolean checkFavExists(Favorito fav);

}