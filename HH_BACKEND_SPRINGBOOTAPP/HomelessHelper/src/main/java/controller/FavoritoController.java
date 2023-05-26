/*
 * 
 * Controlador de los EndPoints del modelo favorito,
 * recibe la información y la transmite a la capa "service"
 * 
 * @Author: HomelessHelper
 * @Version 1.0.0
 * @Since 30-05-2023
 * 
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import service.FavoritoService;
import service.LugarService;
import model.Favorito;
import model.FavoritoPK;
import model.Lugar;

@RestController
public class FavoritoController {

	@Autowired
	FavoritoService favoritoservice;

	@Autowired
	LugarService lugarservice;

	/**
	 * Método que recupera una lista de objetos Favorito en formato JSON.
	 * 
	 * @return Lista de objetos Favorito.
	 */
	@GetMapping(value = "favoritos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Favorito> retrieveFavoritos() {
		return favoritoservice.retrieveFavoritos();
	}

	/**
	 * Método que verifica si un objeto Favorito existe en formato JSON.
	 * 
	 * @param fav Objeto Favorito a verificar.
	 * @return Booleano que indica si el Favorito existe o no.
	 */
	@PostMapping(value = "favorito", produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean getFavorito(@RequestBody Favorito fav) {
		return favoritoservice.checkFavExists(fav);
	}

	/**
	 * Método que recupera una lista de lugares favoritos para un correo electrónico
	 * en formato JSON. Los lugares favoritos se obtienen utilizando el correo
	 * electrónico del FavoritoPK.
	 * 
	 * @param email Objeto FavoritoPK que contiene el correo electrónico para buscar
	 *              los lugares favoritos.
	 * @return Lista de objetos Lugar que son favoritos del usuario.
	 */
	@PostMapping(value = "lugaresFavoritos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lugar> getLugaresFavoritos(@RequestBody FavoritoPK email) {
		List<Lugar> lugaresFavoritos = new ArrayList<Lugar>();
		email.setIdLugar(0);
		List<Favorito> favoritosEmail = favoritoservice.retrieveFavorito(email.getEmail());
		for (Favorito f : favoritosEmail) {
			Lugar lugarActual = lugarservice.retrieveLugar(f.getId().getIdLugar());
			lugaresFavoritos.add(lugarActual);
		}
		return lugaresFavoritos;
	}

	/**
	 * Método que guarda un nuevo objeto Favorito.
	 * 
	 * @param favorito Objeto Favorito a guardar.
	 * @return Objeto Favorito guardado en formato JSON.
	 */
	@PostMapping(value = "newFavorito", produces = MediaType.APPLICATION_JSON_VALUE)
	public Favorito saveFavorito(@RequestBody Favorito favorito) {
		if (favoritoservice.addFavorito(favorito)) {
			return favorito;
		} else {
			Favorito favNull = new Favorito();
			return favNull;
		}
	}

	/**
	 * Método que elimina un objeto Favorito por su ID de lugar y correo electrónico
	 * en formato JSON.
	 * 
	 * @param id    ID del lugar del Favorito a eliminar.
	 * @param email Correo electrónico del Favorito a eliminar.
	 * @return Resultado de la operación de eliminación en formato JSON.
	 */
	@DeleteMapping(value = "deleteFavorito/{id}/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteFavorito(@PathVariable int id, @PathVariable String email) {
		FavoritoPK fav = new FavoritoPK();
		fav.setIdLugar(id);
		fav.setEmail(email);
		Favorito favorito = new Favorito();
		favorito.setId(fav);
		return String.valueOf(favoritoservice.deleteFavorito(favorito));
	}
}
