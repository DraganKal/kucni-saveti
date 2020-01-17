package jwd.wafepa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Zgrada;
import jwd.wafepa.repository.ZgradaRepository;
import jwd.wafepa.service.ZgradaService;

@Service
public class JpaZgradaService implements ZgradaService{
	
	@Autowired
	private ZgradaRepository zgradaRepository;

	@Override
	public Zgrada findOne(Long id) {
		// TODO Auto-generated method stub
		return zgradaRepository.findOne(id);
	}

	@Override
	public List<Zgrada> findAll() {
		// TODO Auto-generated method stub
		return zgradaRepository.findAll();
	}

	@Override
	public Zgrada save(Zgrada zgrada) {
		// TODO Auto-generated method stub
		return zgradaRepository.save(zgrada);
	}

	@Override
	public List<Zgrada> save(List<Zgrada> zgrade) {
		// TODO Auto-generated method stub
		return zgradaRepository.save(zgrade);
	}

	@Override
	public Zgrada delete(Long id) {
		Zgrada zgrada = zgradaRepository.findOne(id);
		if(zgrada == null){
			throw new IllegalArgumentException("Pokusavate da obrisete"
					+ "nepostojecu knjigu");
		}
		zgradaRepository.delete(zgrada);
		return zgrada;
	}

}
