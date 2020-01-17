package jwd.wafepa.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Glas;
import jwd.wafepa.model.Poruka;
import jwd.wafepa.service.PorukaService;
import jwd.wafepa.support.DTOtoPoruka;
import jwd.wafepa.support.GlasToDTO;
import jwd.wafepa.support.PorukaToDTO;
import jwd.wafepa.web.dto.GlasDTO;
import jwd.wafepa.web.dto.PorukaDTO;

@RestController
@RequestMapping(value="/api/poruke")
public class ApiPorukaController {
	
	@Autowired
	private PorukaService porukaService;
	@Autowired
	private PorukaToDTO toDto;
	@Autowired 
	private DTOtoPoruka toPoruka;
	@Autowired
	private GlasToDTO glasToDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<PorukaDTO>> getPoruke(
			@RequestParam(required=false) Long zgradaId,
			@RequestParam(required=false) String naslov,
			@RequestParam(required=false) String tip,
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<Poruka> porukePage = null;
		if(zgradaId != null || naslov != null || tip != null) {
			porukePage = porukaService.search(zgradaId, naslov, tip, pageNum);
		}
		else {
			porukePage = porukaService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(porukePage.getTotalPages()) );
		
		return new ResponseEntity<>(
				toDto.convert(porukePage.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<PorukaDTO> getPoruka(@PathVariable Long id){
		Poruka poruka = porukaService.findOne(id);
		if(poruka==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDto.convert(poruka),
				HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST,
			consumes="application/json")
	public ResponseEntity<PorukaDTO> add(
		@Validated @RequestBody PorukaDTO newPorukaDTO){
	
	Poruka saved = porukaService.save(
			toPoruka.convert(newPorukaDTO));
	
	return new ResponseEntity<>(
			toDto.convert(saved), 
			HttpStatus.CREATED);
	}
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}",
			consumes="application/json")
	public ResponseEntity<PorukaDTO> edit(
			@Validated @RequestBody PorukaDTO porukaDTO,
			@PathVariable Long id){
		
		if(!id.equals(porukaDTO.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Poruka persisted = porukaService.save(
				toPoruka.convert(porukaDTO));
		
		return new ResponseEntity<>(
				toDto.convert(persisted),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<PorukaDTO> delete(@PathVariable Long id){
		Poruka deleted = porukaService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDto.convert(deleted),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/glasaj/{id}" ,method=RequestMethod.POST,
			consumes="application/json")
	public ResponseEntity<GlasDTO> glasaj(
		@PathVariable Long id,
		@Validated @RequestBody Glas glas){
	
	Glas glasano = porukaService.glasaj(id, glas);
	
	return new ResponseEntity<>(
			glasToDTO.convert(glasano), 
			HttpStatus.CREATED);
	}
	
	
	
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	

}
