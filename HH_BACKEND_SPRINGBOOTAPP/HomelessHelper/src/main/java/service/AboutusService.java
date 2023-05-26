package service;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Aboutus;

@Service
public interface AboutusService {
	boolean addAboutus(Aboutus aboutus);

	List<Aboutus> retrieveAboutus();

	boolean updateAboutus(Aboutus aboutus);

	boolean deleteAboutus(int id);

	Aboutus retrieveAboutus(int id);
}