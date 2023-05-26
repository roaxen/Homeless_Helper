package dao;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Tipolugar;

@Service
public interface TipolugarDao {

	Tipolugar getTipoLugar(int idTipoLugar);

	List<Tipolugar> retrieveTipolugar();

}
