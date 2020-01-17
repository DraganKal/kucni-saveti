package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Poruka;
import jwd.wafepa.web.dto.PorukaDTO;

@Component
public class PorukaToDTO implements Converter<Poruka, PorukaDTO>{
	
	@Override
	public PorukaDTO convert(Poruka poruka) {
		
		PorukaDTO retValue = new PorukaDTO();
		
		retValue.setId(poruka.getId());
		retValue.setNaslov(poruka.getNaslov());
		retValue.setOpis(poruka.getOpis());
		retValue.setPotrebanProcenat(poruka.getPotrebanProcenat());
		retValue.setPrihvacen(poruka.isPrihvacen());
		retValue.setTip(poruka.getTip());
		retValue.setZgradaId(poruka.getZgrada().getId());
		retValue.setZgradaNaziv(poruka.getZgrada().getAdresa());
		
		return retValue;
	}

	public List<PorukaDTO> convert(List<Poruka> list){
		List<PorukaDTO> ret = new ArrayList<>();
		
		for(Poruka it : list){
			ret.add(convert(it));
		}
		
		return ret;
	}

}
