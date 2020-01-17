package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Zgrada;
import jwd.wafepa.web.dto.ZgradaDTO;

@Component
public class ZgradaToDTO implements Converter<Zgrada, ZgradaDTO>{
	
	@Override
	public ZgradaDTO convert(Zgrada zgrada) {
		
		ZgradaDTO retValue = new ZgradaDTO();
		
		retValue.setAdresa(zgrada.getAdresa());
		retValue.setBrojStanara(zgrada.getBrojStanara());
		retValue.setBrojStanova(zgrada.getBrojStanova());
		retValue.setId(zgrada.getId());
		retValue.setPredsednikKs(zgrada.getPredsednikKs());
		
		return retValue;
	}

	public List<ZgradaDTO> convert(List<Zgrada> dtos){
		List<ZgradaDTO> ret = new ArrayList<>();
		
		for(Zgrada it : dtos){
			ret.add(convert(it));
		}
		
		return ret;
	}

}
