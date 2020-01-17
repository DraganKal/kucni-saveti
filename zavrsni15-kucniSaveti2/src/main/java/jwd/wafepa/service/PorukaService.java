package jwd.wafepa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.wafepa.model.Glas;
import jwd.wafepa.model.Poruka;

public interface PorukaService {
	
	Poruka findOne(Long id);
	
	Page<Poruka> findAll( int pageNum);
	
	
	
	Poruka save(Poruka poruka);
	
	List<Poruka> save(List<Poruka> poruke);
	
	Poruka delete(Long id);
	
	Page<Poruka> findByZgradaId(Long id, int pageNum);
	
	Page<Poruka> search(
			@Param("zgradaId") Long zgradaId, 
			@Param("naslov") String naslov, 
			@Param("tip") String tip, 
			 int pageNum);
	
	Glas glasaj(Long id, Glas glas);

}
