package service;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Tipolugar;

@Service
public interface TipolugarService {

	Tipolugar getTipoLugar(int idTipoLugar);

	List<Tipolugar> retrieveTipolugar();
}
