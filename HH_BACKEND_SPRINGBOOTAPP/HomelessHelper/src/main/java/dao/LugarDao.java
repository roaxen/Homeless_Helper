package dao;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Lugar;

@Service
public interface LugarDao {
	void addLugar(Lugar lugar);

	void removeLugar(Lugar lugar);

	List<Lugar> getLugar();

	void removeLugar(int idLugar);

	Lugar retrieveLugar(int idLugar);

	void updateLugar(Lugar lugar);

	List<Lugar> getLugaresPorTipo(int idTipoLugar);

	List<Lugar> findLugaresByWord(String valor);
}
