package dao;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Lugarsugerido;

@Service
public interface LugarSugeridoDao {
	
	void addLugarsugerido(Lugarsugerido lugar);

	void removeLugarsugerido(Lugarsugerido lugar);

	List<Lugarsugerido> getLugarsugerido();

	void removeLugarsugerido(int idLugarsugerido);

	Lugarsugerido retrieveLugarsugerido(int idLugarsugerido);

	void updateLugarsugerido(Lugarsugerido lugar);

	List<Lugarsugerido> getLugaresPorTipo(int idTipoLugarsugerido);

	List<Lugarsugerido> findLugaresByWord(String valor);
}
