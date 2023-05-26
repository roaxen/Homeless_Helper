package service;

import java.util.List;

import model.Lugarsugerido;
import org.springframework.stereotype.Service;

@Service
public interface LugarSugeridoService {

	boolean addLugarsugerido(Lugarsugerido lugar);

	List<Lugarsugerido> retrieveLugarsugerido();

	boolean updateLugarsugerido(Lugarsugerido lugar);

	boolean deleteLugarsugerido(int idLugarsugerido);

	Lugarsugerido retrieveLugarsugerido(int idLugarsugerido);

	List<Lugarsugerido> getLugaresPorTipo(int idTipoLugarsugerido);

	List<Lugarsugerido> findLugaresByWord(String valor);

}
