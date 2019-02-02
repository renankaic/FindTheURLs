package com.renankaic.findtheurls.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renankaic.findtheurls.domain.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {
	
	

}
