package jwd.wafepa;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Poruka;
import jwd.wafepa.model.Zgrada;
import jwd.wafepa.service.PorukaService;
import jwd.wafepa.service.ZgradaService;


@Component
public class TestData {
	
	@Autowired
	private ZgradaService zgradaService;
	@Autowired
	private PorukaService porukaService;
	
	

	
	@PostConstruct
	public void init() {
		
		Zgrada z1 = new Zgrada("Adresa1", "Predsednik ks 1", 5, 6);
		zgradaService.save(z1);
		Zgrada z2 = new Zgrada("Adresa2", "Predsednik ks 2", 10, 12);
		zgradaService.save(z2);
		
		Poruka p1 = new Poruka();
		p1.setNaslov("Naslov1");
		p1.setOpis("Opis1");
		p1.setPotrebanProcenat(50);
		p1.setTip("obavestenje");
		p1.setZgrada(z1);
		porukaService.save(p1);
		zgradaService.save(z1);
		
		Poruka p2 = new Poruka();
		p2.setNaslov("Naslov2");
		p2.setOpis("Opis2");
		p2.setPotrebanProcenat(70);
		p2.setTip("predlog");
		p2.setZgrada(z1);
		porukaService.save(p2);
		zgradaService.save(z1);
		
		
		
		
	}

}
