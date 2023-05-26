package dao;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Tipoempleo;

@Service
public interface TipoempleoDao {

	List<Tipoempleo> retrieveTipoempleo();

	Tipoempleo getTipoempleo(int idTipoempleo);

}
