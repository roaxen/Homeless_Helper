package service;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Tipoempleo;

@Service
public interface TipoempleoService {

	List<Tipoempleo> retrieveTipoempleo();

	Tipoempleo getTipoEmpleo(int idTipoempleo);
}
