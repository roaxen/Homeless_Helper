/*
 * 
 * Controlador de los EndPoints del modelo Lugar,
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

import service.LugarService;
import model.Credenciales;
import model.Lugar;
import model.Tipolugar;

import java.util.Collections;
import java.util.List;

@RestController
public class LugarController {
	@Autowired
	LugarService lugarService;

	/**
	 * Método que recupera una lista de objetos Lugar en formato JSON.
	 * 
	 * @return Lista de objetos Lugar.
	 */
	@GetMapping(value = "lugares", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lugar> retrieveLugar() {
		List<Lugar> lugares = lugarService.retrieveLugar();
		Collections.shuffle(lugares);
		return lugares;
	}

	/**
	 * Método que recupera un objeto Lugar por su ID en formato JSON.
	 * 
	 * @param id ID del objeto Lugar a recuperar.
	 * @return Objeto Lugar recuperado en formato JSON.
	 */
	@GetMapping(value = "lugar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Lugar retrieveLugar(@PathVariable("id") int id) {
		return lugarService.retrieveLugar(id);
	}

	/**
	 * Método que guarda un nuevo objeto Lugar.
	 * 
	 * @param lugar Objeto Lugar a guardar.
	 * @return Objeto Lugar guardado en formato JSON.
	 */
	@PostMapping(value = "addLugar", produces = MediaType.APPLICATION_JSON_VALUE)
	public Lugar saveLugar(@RequestBody Lugar lugar) {
		if (lugarService.addLugar(lugar)) {
			return lugar;
		} else {
			Lugar nullLugar = new Lugar();
			return nullLugar;
		}
	}

	/**
	 * Método que actualiza un objeto Lugar.
	 * 
	 * @param lugar Objeto Lugar a actualizar.
	 * @return Objeto Lugar actualizado en formato JSON.
	 */
	@PutMapping(value = "updateLugar", produces = MediaType.APPLICATION_JSON_VALUE)
	public Lugar updateLugar(@RequestBody Lugar lugar) {
		if (lugarService.updateLugar(lugar)) {
			return lugar;
		} else {
			Lugar nullLugar = new Lugar();
			return nullLugar;
		}
	}

	/**
	 * Método que elimina un objeto Lugar por su ID en formato JSON.
	 * 
	 * @param idLugar ID del objeto Lugar a eliminar.
	 * @return Booleano que indica si el Lugar fue eliminado o no.
	 */
	@DeleteMapping(value = "eliminarLugar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean deleteLugar(@PathVariable("id") int idLugar) {
		return lugarService.deleteLugar(idLugar);
	}

	/**
	 * Método que recupera una lista de lugares por tipo en formato JSON. Los
	 * lugares se obtienen utilizando el ID del Tipo de Lugar.
	 * 
	 * @param idTipoLugar Objeto Tipolugar que contiene el ID del tipo de lugar para
	 *                    buscar los lugares.
	 * @return Lista de objetos Lugar que corresponden al tipo de lugar
	 *         especificado.
	 */
	@PostMapping(value = "getLugarByTipo", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lugar> getLugaresPorTipo(@RequestBody Tipolugar idTipoLugar) {
		List<Lugar> lugares = lugarService.getLugaresPorTipo(idTipoLugar.getIdTipolugar());
		Collections.shuffle(lugares);
		return lugares;
	}

	/**
	 * Método que busca lugares por palabra clave en formato JSON.
	 * 
	 * @param valor Objeto Credenciales que contiene la palabra clave para buscar
	 *              lugares.
	 * @return Lista de objetos Lugar que coinciden con la palabra clave
	 *         especificada.
	 */
	@PostMapping(value = "searchLugares", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lugar> findLugaresByWord(@RequestBody Credenciales valor) {
		return lugarService.findLugaresByWord(valor.getValor());
	}
}
