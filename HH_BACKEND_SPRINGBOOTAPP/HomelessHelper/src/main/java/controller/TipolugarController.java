/*
 * 
 * Controlador de los EndPoints del modelo tipolugar,
 * recibe la información y la transmite a la capa "service"
 * 
 * @Author: HomelessHelper
 * @Version 1.0.0
 * @Since 30-05-2023
 * 
 */

package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Tipolugar;
import service.TipolugarService;

@RestController
public class TipolugarController {

	@Autowired
	TipolugarService tipolugarService;

	/**
	 * 
	 * Recupera una lista de tipos de lugar en formato JSON.
	 * 
	 * @return Lista de objetos Tipolugar.
	 */
	@GetMapping(value = "tiposLugar", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tipolugar> retrieveLugar() {
		return tipolugarService.retrieveTipolugar();
	}

	/**
	 * 
	 * Recupera un tipo de lugar específico por su ID en formato JSON.
	 * 
	 * @param idTipolugar El ID del tipo de lugar.
	 * @return El objeto Tipolugar que coincide con el ID.
	 */
	@GetMapping(value = "tipoLugar/{idTipolugar}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Tipolugar retrieveLugar(@PathVariable int idTipolugar) {
		return tipolugarService.getTipoLugar(idTipolugar);
	}
}