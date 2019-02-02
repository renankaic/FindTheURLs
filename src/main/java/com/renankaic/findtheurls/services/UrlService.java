package com.renankaic.findtheurls.services;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renankaic.findtheurls.domain.Url;
import com.renankaic.findtheurls.repositories.UrlRepository;

@Service
public class UrlService {
	
	@Autowired
	private UrlRepository repository;
	
	public Url find(Integer id) {		
		Optional<Url> obj = repository.findById(id);
		return obj.orElse(null);	
	}
	
	public Url save(Url obj) {
		if(  obj != null ) {			
			repository.save(obj);		
			return obj;
		} else {
			return null;
		}
	}
	
	public List<Url> saveAll(List<URL> list){
		
		List<Url> savedUrls = new ArrayList<Url>();
		for(URL url: list) {
			
			Url obj = new Url();
			obj.setCreation_date(new Date());
			obj.setUrl(url);
			repository.save(obj);
			savedUrls.add(obj);
		
		}
		return savedUrls;		
		
	}

}
