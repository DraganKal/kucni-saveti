package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Poruka;
import jwd.wafepa.model.Zgrada;
import jwd.wafepa.service.PorukaService;
import jwd.wafepa.service.ZgradaService;
import jwd.wafepa.web.dto.PorukaDTO;

@Component
public class DTOtoPoruka implements Converter<PorukaDTO, Poruka>{
	
	@Autowired
	private PorukaService porukaService;
	@Autowired
	private ZgradaService zgradaService;
	
	@Override
	public Poruka convert(PorukaDTO dto) {
		
		Zgrada zgrada = zgradaService.findOne(dto.getZgradaId());
	
		if(zgrada != null) {
			
			Poruka poruka = null;
			
			if(dto.getId() != null) {
				poruka = porukaService.findOne(dto.getId());
			}
			else {
				poruka = new Poruka();
			}
			poruka.setId(dto.getId());
			poruka.setNaslov(dto.getNaslov());
			poruka.setOpis(dto.getOpis());
			poruka.setPotrebanProcenat(dto.getPotrebanProcenat());
			poruka.setPrihvacen(dto.isPrihvacen());
			poruka.setTip(dto.getTip());
			poruka.setZgrada(zgrada);
			
			return poruka;
		}else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Poruka> convert(List<PorukaDTO> dtos){
		List<Poruka> ret = new ArrayList<>();
		
		for(PorukaDTO it : dtos){
			ret.add(convert(it));
		}
		
		return ret;
	}

}
