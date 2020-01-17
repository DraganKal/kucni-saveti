package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Poruka;
import jwd.wafepa.model.Zgrada;
import jwd.wafepa.service.PorukaService;
import jwd.wafepa.service.ZgradaService;
import jwd.wafepa.support.PorukaToDTO;
import jwd.wafepa.support.ZgradaToDTO;
import jwd.wafepa.web.dto.PorukaDTO;
import jwd.wafepa.web.dto.ZgradaDTO;

@RestController
@RequestMapping(value="/api/zgrade")
public class ApiZgradaController {
	
	@Autowired
	private ZgradaService zgradaService;
	@Autowired
	private PorukaService porukaService;
	@Autowired
	private ZgradaToDTO toDto;
	@Autowired
	private PorukaToDTO porukaToDto;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<ZgradaDTO>> getZgrade(){
		
		List<Zgrada> zgrade = zgradaService.findAll();
		
		return new ResponseEntity<>(
				toDto.convert(zgrade), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<ZgradaDTO> getZgrada(@PathVariable Long id){
		Zgrada zgrada = zgradaService.findOne(id);
		if(zgrada==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDto.convert(zgrada),
				HttpStatus.OK);
	}
	
	@RequestMapping(value= "/{id}/poruke", method=RequestMethod.GET)
	ResponseEntity<List<PorukaDTO>> getZgradaPoruke(
			@PathVariable Long id, @RequestParam(defaultValue="0") int pageNum){
		
		Page<Poruka> porukePage = porukaService.findByZgradaId(id, pageNum);
		
		if(porukePage == null || porukePage.getContent().isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(porukePage.getTotalPages()) );
		
		return new ResponseEntity<>(
				porukaToDto.convert(porukePage.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	
	
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
