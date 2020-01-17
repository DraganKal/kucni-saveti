package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Zgrada;

public interface ZgradaService {
	
	Zgrada findOne(Long id);
	
	List<Zgrada> findAll();
	
	Zgrada save(Zgrada zgrada);
	
	List<Zgrada> save(List<Zgrada> zgrade);
	
	Zgrada delete(Long id);

}
