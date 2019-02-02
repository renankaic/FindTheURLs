package com.renankaic.findtheurls.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.renankaic.findtheurls.domain.Url;
import com.renankaic.findtheurls.services.UrlService;

@RestController
@RequestMapping(value="/urls")
public class UrlResource {
	
	@Autowired
	private UrlService service;

	@GetMapping(value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {		
		Url obj = service.find(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	@GetMapping()
	public List<Url> list() {
		return null;
	}
	
	@PostMapping()
	public List<Url> create(@RequestBody Url body){
		return null;		
	}
	
	@PutMapping()
	public List<Url> update(@RequestBody Url body){
		return null;
	}
	
	@DeleteMapping()
	public List<Url> delete(@RequestParam Integer id){
		return null;
	}
	
}
