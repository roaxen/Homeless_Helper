package service;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Favorito;

@Service
public interface FavoritoService {

	List<Favorito> retrieveFavoritos();

	List<Favorito> retrieveFavorito(String email);

	Boolean addFavorito(Favorito favorito);

	Boolean deleteFavorito(Favorito favorito);

	boolean checkFavExists(Favorito fav);
}