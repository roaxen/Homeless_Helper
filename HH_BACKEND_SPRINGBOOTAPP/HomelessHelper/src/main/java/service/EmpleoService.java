package service;

import java.util.List;

import model.Empleo;
import org.springframework.stereotype.Service;

@Service
public interface EmpleoService {

	boolean addEmpleo(Empleo empleo);

	List<Empleo> retrieveEmpleo();

	boolean updateEmpleo(Empleo empleo);

	boolean deleteEmpleo(int idEmpleo);

	Empleo retrieveEmpleo(int idEmpleo);

	List<Empleo> getEmpleosPorTipo(int idTipoEmpleo);

	List<Empleo> findEmpleosByWord(String valor);

	List<Empleo> findEmpleosByEmail(String email);

	List<Empleo> retrieveEmpleos(String email);

}
