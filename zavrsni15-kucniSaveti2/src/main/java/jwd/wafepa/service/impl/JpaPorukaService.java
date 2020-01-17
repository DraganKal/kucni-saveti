package jwd.wafepa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Glas;
import jwd.wafepa.model.Poruka;
import jwd.wafepa.repository.GlasRepository;
import jwd.wafepa.repository.PorukaRepository;
import jwd.wafepa.service.PorukaService;

@Service
public class JpaPorukaService implements PorukaService{
	
	@Autowired
	private PorukaRepository porukaRepository;
	@Autowired
	private GlasRepository glasRepository;
	
	@Override
	public Poruka findOne(Long id) {
		// TODO Auto-generated method stub
		return porukaRepository.findOne(id);
	}

	@Override
	public Page<Poruka> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return porukaRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Poruka save(Poruka poruka) {
		if(!(poruka.getTip().equalsIgnoreCase("obavestenje") || poruka.getTip().equalsIgnoreCase("predlog"))) {
			throw new IllegalArgumentException("Tip mora "
					+ "biti obavestenje ili predlog");
		}
		if(poruka.getTip().equalsIgnoreCase("obavestenje")) {
			poruka.setPotrebanProcenat(0);
		}
		poruka.setPrihvacen(false);
		
		return porukaRepository.save(poruka);
	}

	@Override
	public List<Poruka> save(List<Poruka> poruke) {
		// TODO Auto-generated method stub
		return porukaRepository.save(poruke);
	}

	@Override
	public Poruka delete(Long id) {
		Poruka poruka = porukaRepository.findOne(id);
		if(poruka == null){
			throw new IllegalArgumentException("Pokusavate da obrisete"
					+ "nepostojecu knjigu");
		}
		porukaRepository.delete(poruka);
		return poruka;
	}

	@Override
	public Page<Poruka> findByZgradaId(Long id, int pageNum) {
		// TODO Auto-generated method stub
		return porukaRepository.findByZgradaId(id, new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Poruka> search(Long zgradaId, String naslov, String tip, int pageNum) {
		if(naslov != null) {
			naslov = '%' + naslov + '%';
		}
		if(tip != null) {
			tip = '%' + tip + '%';
		}
		return porukaRepository.search(zgradaId, naslov, tip, new PageRequest(pageNum, 5));
	}

	@Override
	public Glas glasaj(Long id, Glas glas) {
		Poruka poruka = findOne(id);
		if(poruka == null){
			throw new IllegalArgumentException("Pokusavate da glasate za"
					+ "nepostojecu knjigu");
		}
		if(glas.getPredlogPodrzan().equalsIgnoreCase("DA")) {
			poruka.setGlasoviZa(poruka.getGlasoviZa()+1);
		}
		int glasoviPre = poruka.getGlasovi().size();
		if(glasoviPre >= poruka.getZgrada().getBrojStanara()) {
			throw new IllegalArgumentException("Svi stanari su"
					+ "glasali");
		}
		poruka.getGlasovi().add(glas);
		glas.setPoruka(poruka);
		int glasovi = poruka.getGlasovi().size();
		System.out.println("svi glasovi " + glasovi);
		System.out.println("glasovi za " + poruka.getGlasoviZa());
		
		int stanari = poruka.getZgrada().getBrojStanara();
		double procenat = 100 * poruka.getGlasoviZa() / stanari;
		if(procenat >= poruka.getPotrebanProcenat()) {;
			poruka.setPrihvacen(true);
		}
		porukaRepository.save(poruka);
		
		
		
		return glas;
	}

}
