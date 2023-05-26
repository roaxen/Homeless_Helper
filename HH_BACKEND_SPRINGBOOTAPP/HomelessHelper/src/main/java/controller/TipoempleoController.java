/*
 * 
 * Controlador de los EndPoints del modelo Tipoempleo,
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

import model.Tipoempleo;
import service.TipoempleoService;

@RestController
public class TipoempleoController {

	@Autowired
	TipoempleoService tipoempleoService;

	/**
	 * 
	 * Recupera una lista de tipos de empleo en formato JSON.
	 * 
	 * @return Lista de objetos Tipoempleo.
	 */
	@GetMapping(value = "tiposEmpleo", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tipoempleo> retrieveEmpleo() {
		return tipoempleoService.retrieveTipoempleo();
	}

	/**
	 * 
	 * Recupera un tipo de empleo específico por su ID en formato JSON.
	 * 
	 * @param idTipoempleo El ID del tipo de empleo.
	 * @return El objeto Tipoempleo que coincide con el ID.
	 */
	@GetMapping(value = "tipoEmpleo/{idTipoempleo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Tipoempleo retrieveEmpleo(@PathVariable int idTipoempleo) {
		return tipoempleoService.getTipoEmpleo(idTipoempleo);
	}
}
