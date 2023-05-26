package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UsuarioDao;
import model.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioDao usuarioDao;

	/**
	 * Agrega un nuevo usuario.
	 *
	 * @param usuario El objeto Usuario a agregar.
	 * @return true si se agrega correctamente, false si ya existe un usuario con el
	 *         mismo email.
	 */
	@Override
	public boolean addUsuario(Usuario usuario) {
		if (usuarioDao.retrieveUsuario(usuario.getEmail()) == null) {
			usuarioDao.addUsuario(usuario);
			return true;
		}
		return false;
	}

	/**
	 * Obtiene todos los usuarios registrados.
	 *
	 * @return Una lista de objetos Usuario.
	 */
	@Override
	public List<Usuario> retrieveUsuarios() {
		return usuarioDao.getUsuarios();
	}

	/**
	 * Actualiza los datos de un usuario.
	 *
	 * @param usuario El objeto Usuario con los nuevos datos.
	 * @return true si se actualiza correctamente, false si el usuario no existe o
	 *         las credenciales son incorrectas.
	 */
	@Override
	public boolean updateUsuario(Usuario usuario) {
		Usuario user = usuarioDao.checkameUsuario(usuario.getEmail(), usuario.getClave());

		if (user != null) {
			usuarioDao.updateUsuario(usuario);
			return true;
		}
		return false;
	}

	/**
	 * Elimina un usuario por su email.
	 *
	 * @param email El email del usuario a eliminar.
	 * @return true si se elimina correctamente, false si el usuario no existe.
	 */
	@Override
	public boolean deleteUsuario(String email) {
		if (usuarioDao.retrieveUsuario(email) != null) {
			usuarioDao.removeUsuario(email);
			return true;
		}
		return false;
	}

	/**
	 * Obtiene un usuario por su email.
	 *
	 * @param email El email del usuario a obtener.
	 * @return El objeto Usuario correspondiente al email especificado.
	 */
	@Override
	public Usuario retrieveUsuario(String email) {
		return usuarioDao.retrieveUsuario(email);
	}

	/**
	 * Verifica si un usuario existe y las credenciales son correctas.
	 *
	 * @param email El email del usuario a verificar.
	 * @param clave La clave del usuario a verificar.
	 * @return El objeto Usuario si las credenciales son correctas, null si no
	 *         existe o las credenciales son incorrectas.
	 */
	@Override
	public Usuario checkUserExists(String email, String clave) {
		return usuarioDao.checkameUsuario(email, clave);
	}

	/**
	 * Actualiza la contraseña de un usuario.
	 *
	 * @param email     El email del usuario.
	 * @param clave     La contraseña actual.
	 * @param new_clave La nueva contraseña a establecer.
	 * @return true si se actualiza correctamente, false si el usuario no existe o
	 *         las credenciales son incorrectas.
	 */
	@Override
	public boolean updatePassword(String email, String clave, String new_clave) {
		Usuario user = usuarioDao.checkameUsuario(email, clave);

		if (user != null) {
			user.setClave(new_clave);
			usuarioDao.updateUsuario(user);
			return true;
		}
		return false;
	}

}