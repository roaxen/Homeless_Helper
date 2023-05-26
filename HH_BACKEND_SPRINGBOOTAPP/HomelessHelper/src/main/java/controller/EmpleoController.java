/*
 * 
 * Controlador de los EndPoints del modelo empleo,
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

import service.EmpleoService;
import model.Credenciales;
import model.Empleo;
import model.Tipoempleo;
import model.Usuario;

import java.util.Collections;
import java.util.List;

@RestController
public class EmpleoController {
	@Autowired
	EmpleoService empleoService;

	/**
	 * Método que recupera una lista de objetos Empleo en formato JSON. Los empleos
	 * se ordenan por fecha de forma descendente.
	 * 
	 * @return Lista de objetos Empleo ordenados por fecha.
	 */
	@GetMapping(value = "empleos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Empleo> retrieveEmpleo() {
		List<Empleo> empleos = empleoService.retrieveEmpleo();
		empleos.sort((o1, o2) -> o2.getFecha().compareTo(o1.getFecha()));
		return empleos;
	}

	/**
	 * Método que recupera un objeto Empleo por su ID en formato JSON.
	 * 
	 * @param id ID del objeto Empleo a recuperar.
	 * @return Objeto Empleo en formato JSON.
	 */
	@GetMapping(value = "empleo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Empleo retrieveEmpleo(@PathVariable("id") int id) {
		return empleoService.retrieveEmpleo(id);
	}

	/**
	 * Método que recupera una lista de objetos Empleo para un Usuario en formato
	 * JSON. Los empleos se obtienen utilizando el email del Usuario. Los empleos se
	 * ordenan por fecha de forma descendente.
	 * 
	 * @param user Objeto Usuario que contiene el email para recuperar los empleos.
	 * @return Lista de objetos Empleo ordenados por fecha.
	 */
	@PostMapping(value = "otrosEmpleos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Empleo> retrieveEmpleos(@RequestBody Usuario user) {
		List<Empleo> empleos = empleoService.retrieveEmpleos(user.getEmail());
		empleos.sort((o1, o2) -> o2.getFecha().compareTo(o1.getFecha()));
		return empleos;
	}

	/**
	 * Método que guarda un nuevo objeto Empleo.
	 * 
	 * @param empleo Objeto Empleo a guardar.
	 * @return Objeto Empleo guardado en formato JSON.
	 */
	@PostMapping(value = "addEmpleo", produces = MediaType.APPLICATION_JSON_VALUE)
	public Empleo saveEmpleo(@RequestBody Empleo empleo) {
		if (empleoService.addEmpleo(empleo)) {
			return empleo;
		} else {
			Empleo nullEmpleo = new Empleo();
			return nullEmpleo;
		}
	}

	/**
	 * Método que actualiza un objeto Empleo.
	 * 
	 * @param empleo Objeto Empleo a actualizar.
	 * @return Objeto Empleo actualizado en formato JSON.
	 */
	@PutMapping(value = "updateEmpleo", produces = MediaType.APPLICATION_JSON_VALUE)
	public Empleo updateEmpleo(@RequestBody Empleo empleo) {
		if (empleoService.updateEmpleo(empleo)) {
			return empleo;
		} else {
			Empleo nullEmpleo = new Empleo();
			return nullEmpleo;
		}
	}

	/**
	 * Método que elimina un objeto Empleo por su ID.
	 * 
	 * @param idEmpleo ID del objeto Empleo a eliminar.
	 * @return Resultado de la operación de eliminación en formato JSON.
	 */
	@DeleteMapping(value = "eliminarEmpleo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean deleteEmpleo(@PathVariable("id") int idEmpleo) {
		return empleoService.deleteEmpleo(idEmpleo);
	}

	/**
	 * Método que recupera una lista de objetos Empleo por tipo de empleo en formato
	 * JSON. Los empleos se obtienen utilizando el ID del Tipoempleo. Los empleos se
	 * ordenan en orden inverso.
	 * 
	 * @param tipoempleo Objeto Tipoempleo que contiene el ID para recuperar los
	 *                   empleos.
	 * @return Lista de objetos Empleo ordenados en orden inverso.
	 */
	@PostMapping(value = "getEmpleoByTipo", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Empleo> getEmpleosPorTipo(@RequestBody Tipoempleo tipoempleo) {
		List<Empleo> empleos = empleoService.getEmpleosPorTipo(tipoempleo.getId());
		Collections.reverse(empleos);
		return empleos;
	}

	/**
	 * Método que busca empleos por palabra clave en formato JSON. Los empleos se
	 * buscan utilizando el valor de búsqueda proporcionado.
	 * 
	 * @param valor Objeto Credenciales que contiene el valor de búsqueda.
	 * @return Lista de objetos Empleo que coinciden con la palabra clave.
	 */
	@PostMapping(value = "searchEmpleos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Empleo> findEmpleosByWord(@RequestBody Credenciales valor) {
		return empleoService.findEmpleosByWord(valor.getValor());
	}

	/**
	 * Método que busca los empleos de un usuario por su correo electrónico en
	 * formato JSON. Los empleos se obtienen utilizando el correo electrónico del
	 * Usuario. Los empleos se ordenan por fecha de forma descendente.
	 * 
	 * @param email Objeto Usuario que contiene el correo electrónico para buscar
	 *              los empleos.
	 * @return Lista de objetos Empleo ordenados por fecha.
	 */
	@PostMapping(value = "myEmpleos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Empleo> findEmpleosByEmail(@RequestBody Usuario email) {
		List<Empleo> empleos = empleoService.findEmpleosByEmail(email.getEmail());
		empleos.sort((o1, o2) -> o2.getFecha().compareTo(o1.getFecha()));
		return empleos;
	}
}
