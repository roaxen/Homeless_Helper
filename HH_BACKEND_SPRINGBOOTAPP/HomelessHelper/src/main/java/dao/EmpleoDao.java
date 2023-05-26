package dao;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Empleo;

@Service
public interface EmpleoDao {
	void addEmpleo(Empleo empleo);

	void removeEmpleo(Empleo empleo);

	List<Empleo> getEmpleo();

	void removeEmpleo(int idEmpleo);

	Empleo retrieveEmpleo(int idEmpleo);

	void updateEmpleo(Empleo empleo);

	List<Empleo> getEmpleosPorTipo(int idTipoEmpleo);

	List<Empleo> findEmpleosByWord(String valor);

	List<Empleo> findEmpleosByEmail(String email);

	List<Empleo> getEmpleos(String email);
}