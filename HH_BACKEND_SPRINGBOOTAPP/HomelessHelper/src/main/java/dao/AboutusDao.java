package dao;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Aboutus;

@Service
public interface AboutusDao {
	void addAboutus(Aboutus aboutus);

	void removeAboutus(Aboutus aboutus);

	List<Aboutus> getAboutus();

	void removeAboutus(int id);

	Aboutus retrieveAboutus(int id);

	void updateAboutus(Aboutus aboutus);
}