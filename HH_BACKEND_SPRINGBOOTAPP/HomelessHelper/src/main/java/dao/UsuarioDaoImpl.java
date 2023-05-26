package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import model.Usuario;

@Repository

public class UsuarioDaoImpl implements UsuarioDao {
	@Autowired
	UsuarioJpaSpring usuarioDao;

	/**
	 * Agrega un nuevo usuario.
	 * 
	 * @param usuario El usuario a agregar.
	 */
	@Override
	public void addUsuario(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	/**
	 * Elimina un usuario.
	 * 
	 * @param usuario El usuario a eliminar.
	 */
	@Override
	public void removeUsuario(Usuario usuario) {
		usuarioDao.deleteById(usuario.getEmail());
	}

	/**
	 * Obtiene todos los usuarios.
	 * 
	 * @return Una lista de usuarios.
	 */
	@Override
	public List<Usuario> getUsuarios() {
		return usuarioDao.findAll();
	}

	/**
	 * Elimina un usuario por su email. Esto también elimina los favoritos, lugares
	 * y empleos asociados al usuario.
	 * 
	 * @param email El email del usuario a eliminar.
	 */
	@Override
	public void removeUsuario(String email) {
		usuarioDao.removeFavoritos(email);
		usuarioDao.removeLugares(email);
		usuarioDao.removeEmpleos(email);
		usuarioDao.removeByEmail(email);
	}

	/**
	 * Obtiene un usuario por su email.
	 * 
	 * @param email El email del usuario a obtener.
	 * @return El usuario encontrado, o null si no existe.
	 */
	@Override
	public Usuario retrieveUsuario(String email) {
		return usuarioDao.findById(email).orElse(null);
	}

	/**
	 * Actualiza los datos de un usuario.
	 * 
	 * @param usuario El usuario con los datos actualizados.
	 */
	@Override
	public void updateUsuario(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	/**
	 * Verifica las credenciales de un usuario.
	 * 
	 * @param email El email del usuario.
	 * @param clave La contraseña del usuario.
	 * @return El usuario si las credenciales son válidas, o un usuario nulo si no
	 *         son válidas.
	 */
	@Override
	public Usuario checkameUsuario(String email, String clave) {
		Usuario user = usuarioDao.checkUserExists(email, clave);
		Usuario nullUser = new Usuario();
		if (user != null) {
			return user;
		} else {
			return nullUser;
		}
	}

	/**
	 * Cambia la contraseña de un usuario.
	 * 
	 * @param email     El email del usuario.
	 * @param clave     La contraseña actual del usuario.
	 * @param new_clave La nueva contraseña del usuario.
	 */
	@Override
	public void changePwd(String email, String clave, String new_clave) {
		Usuario usuario = usuarioDao.checkUserExists(email, clave);
		usuario.setClave(new_clave);
		usuarioDao.save(usuario);
	}
}