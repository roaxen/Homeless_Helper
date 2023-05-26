package service;

import java.util.List;

import model.Lugar;
import org.springframework.stereotype.Service;

@Service
public interface LugarService {

	boolean addLugar(Lugar lugar);

	List<Lugar> retrieveLugar();

	boolean updateLugar(Lugar lugar);

	boolean deleteLugar(int idLugar);

	Lugar retrieveLugar(int idLugar);

	List<Lugar> getLugaresPorTipo(int idTipoLugar);

	List<Lugar> findLugaresByWord(String valor);

}
