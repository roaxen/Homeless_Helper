/*
 * 
 * Controlador de los EndPoints del modelo Usuario,
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import service.UsuarioService;
import model.Usuario;
import model.Credenciales;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioservice;

	/**
	 * Recupera una lista de usuarios en formato JSON.
	 * 
	 * @return Lista de objetos Usuario.
	 */

	@GetMapping(value = "profiles", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Usuario> retrieveUsuarios() {
		return usuarioservice.retrieveUsuarios();
	}

	/**
	 * Realiza el inicio de sesión de un usuario con las credenciales
	 * proporcionadas.
	 * 
	 * @param credenciales Las credenciales del usuario (email y clave).
	 * @return El objeto Usuario correspondiente al inicio de sesión exitoso, o null
	 *         si no se encuentra coincidencia.
	 */

	@PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
	public Usuario loginUsuario(@RequestBody Credenciales credenciales) {
		Usuario user = usuarioservice.checkUserExists(credenciales.getEmail(), credenciales.getClave());
		return user;
	}

	/**
	 * Recupera un usuario específico por su dirección de correo electrónico en
	 * formato JSON.
	 * 
	 * @param email La dirección de correo electrónico del usuario.
	 * @return El objeto Usuario que coincide con la dirección de correo
	 *         electrónico.
	 */
	@GetMapping(value = "profile/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Usuario retrieveUsuario(@PathVariable("email") String email) {
		return usuarioservice.retrieveUsuario(email);
	}

	/**
	 * Guarda un nuevo usuario en el sistema.
	 * 
	 * @param usuario El objeto Usuario a guardar.
	 * @return El objeto Usuario guardado, o un objeto Usuario vacío si no se pudo
	 *         guardar.
	 */
	@PostMapping(value = "signin", produces = MediaType.APPLICATION_JSON_VALUE)
	public Usuario saveUsuario(@RequestBody Usuario usuario) {
		if (usuarioservice.addUsuario(usuario)) {
			return usuario;
		} else {
			Usuario nullUser = new Usuario();
			return nullUser;
		}
	}

	/**
	 * Actualiza los datos de un usuario existente.
	 * 
	 * @param usuario El objeto Usuario con los nuevos datos a actualizar.
	 * @return El objeto Usuario actualizado, o un objeto Usuario vacío si no se
	 *         pudo actualizar.
	 */
	@PutMapping(value = "profile", produces = MediaType.APPLICATION_JSON_VALUE)
	public Usuario updateUsuario(@RequestBody Usuario usuario) {
		if (String.valueOf(usuarioservice.updateUsuario(usuario)) != null) {
			return usuario;
		} else {
			Usuario nullUser = new Usuario();
			return nullUser;
		}
	}

	/**
	 * Actualiza la contraseña de un usuario.
	 * 
	 * @param credenciales Las credenciales del usuario, incluyendo la nueva
	 *                     contraseña.
	 * @return El objeto Usuario con los datos actualizados después del cambio de
	 *         contraseña, o un objeto Usuario vacío si no se pudo realizar el
	 *         cambio.
	 */

	@PutMapping(value = "changeUserPwd", produces = MediaType.APPLICATION_JSON_VALUE)
	public Usuario updatePassword(@RequestBody Credenciales credenciales) {
		if (String.valueOf(usuarioservice.updatePassword(credenciales.getEmail(), credenciales.getClave(),
				credenciales.getNew_clave())) != null) {
			Usuario user = retrieveUsuario(credenciales.getEmail());
			return user;
		} else {
			Usuario nullUser = new Usuario();
			return nullUser;
		}
	}

	/**
	 * Elimina un usuario del sistema.
	 * 
	 * @param email La dirección de correo electrónico del usuario a eliminar.
	 * @return "true" si el usuario se eliminó correctamente, "false" si no se pudo
	 *         eliminar.
	 */

	@DeleteMapping(value = "user/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteUsuario(@PathVariable("email") String email) {
		return String.valueOf(usuarioservice.deleteUsuario(email));
	}
}