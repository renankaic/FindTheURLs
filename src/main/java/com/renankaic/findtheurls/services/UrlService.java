package com.renankaic.findtheurls.services;

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

}
