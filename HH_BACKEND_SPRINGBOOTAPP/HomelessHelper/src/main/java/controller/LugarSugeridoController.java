/*
 * 
 * Controlador de los EndPoints del modelo Lugarsugerido,
 * recibe la información y la transmite a la capa "service"
 * 
 * @Author: HomelessHelper
 * @Version 1.0.0
 * @Since 30-05-2023
 * 
 */

package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import service.LugarSugeridoService;
import model.Credenciales;
import model.Lugarsugerido;
import model.Tipolugar;

import java.util.Collections;
import java.util.List;

@RestController
public class LugarSugeridoController {
	@Autowired
	LugarSugeridoService lugarService;

	/**
	 * Método que recupera una lista de objetos Lugarsugerido en formato JSON.
	 * 
	 * @return Lista de objetos Lugarsugerido.
	 */
	@GetMapping(value = "lugaresSug", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lugarsugerido> retrieveLugarsugerido() {
		List<Lugarsugerido> lugares = lugarService.retrieveLugarsugerido();
		Collections.shuffle(lugares);
		return lugares;
	}

	/**
	 * Método que recupera un objeto Lugarsugerido por su ID en formato JSON.
	 * 
	 * @param id ID del objeto Lugarsugerido a recuperar.
	 * @return Objeto Lugarsugerido recuperado en formato JSON.
	 */
	@GetMapping(value = "lugarSug/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Lugarsugerido retrieveLugarsugerido(@PathVariable("id") int id) {
		return lugarService.retrieveLugarsugerido(id);
	}

	/**
	 * Método que guarda un nuevo objeto Lugarsugerido.
	 * 
	 * @param lugar Objeto Lugarsugerido a guardar.
	 * @return Objeto Lugarsugerido guardado en formato JSON.
	 */
	@PostMapping(value = "addLugarsugerido", produces = MediaType.APPLICATION_JSON_VALUE)
	public Lugarsugerido saveLugarsugerido(@RequestBody Lugarsugerido lugar) {
		if (lugarService.addLugarsugerido(lugar)) {
			return lugar;
		} else {
			Lugarsugerido nullLugarsugerido = new Lugarsugerido();
			return nullLugarsugerido;
		}
	}

	/**
	 * Método que actualiza un objeto Lugarsugerido.
	 * 
	 * @param lugar Objeto Lugarsugerido a actualizar.
	 * @return Objeto Lugarsugerido actualizado en formato JSON.
	 */
	@PutMapping(value = "updateLugarsugerido", produces = MediaType.APPLICATION_JSON_VALUE)
	public Lugarsugerido updateLugarsugerido(@RequestBody Lugarsugerido lugar) {
		if (lugarService.updateLugarsugerido(lugar)) {
			return lugar;
		} else {
			Lugarsugerido nullLugarsugerido = new Lugarsugerido();
			return nullLugarsugerido;
		}
	}

	/**
	 * Método que elimina un objeto Lugarsugerido por su ID.
	 * 
	 * @param idLugarsugerido ID del objeto Lugarsugerido a eliminar.
	 * @return Valor booleano que indica si se ha eliminado correctamente.
	 */
	@DeleteMapping(value = "eliminarLugarsugerido/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean deleteLugarsugerido(@PathVariable("id") int idLugarsugerido) {
		return lugarService.deleteLugarsugerido(idLugarsugerido);
	}

	/**
	 * Método que recupera una lista de objetos Lugarsugerido por tipo en formato
	 * JSON.
	 * 
	 * @param idTipoLugarsugerido ID del tipo de lugar para filtrar los
	 *                            Lugarsugerido.
	 * @return Lista de objetos Lugarsugerido filtrados por tipo en formato JSON.
	 */
	@PostMapping(value = "getLugarsugeridoByTipo", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lugarsugerido> getLugarsugeridosPorTipo(@RequestBody Tipolugar idTipoLugarsugerido) {
		List<Lugarsugerido> lugares = lugarService.getLugaresPorTipo(idTipoLugarsugerido.getIdTipolugar());
		Collections.shuffle(lugares);
		return lugares;
	}

	/**
	 * Método que busca Lugarsugeridos por una palabra clave.
	 * 
	 * @param valor Objeto Credenciales que contiene el valor de búsqueda.
	 * @return Lista de objetos Lugarsugerido que coinciden con la palabra clave en
	 *         formato JSON.
	 */
	@PostMapping(value = "searchLugarsugeridos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lugarsugerido> findLugarsugeridosByWord(@RequestBody Credenciales valor) {
		return lugarService.findLugaresByWord(valor.getValor());
	}
}
