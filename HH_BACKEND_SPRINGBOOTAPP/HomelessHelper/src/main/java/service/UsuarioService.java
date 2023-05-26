package service;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Usuario;

@Service
public interface UsuarioService {

	boolean addUsuario(Usuario Usuario);

	List<Usuario> retrieveUsuarios();

	boolean updateUsuario(Usuario Usuario);

	boolean deleteUsuario(String email);

	Usuario retrieveUsuario(String email);

	boolean updatePassword(String email, String clave, String new_clave);

	Usuario checkUserExists(String email, String clave);

}
