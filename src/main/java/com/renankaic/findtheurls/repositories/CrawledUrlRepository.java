package com.renankaic.findtheurls.repositories;

import java.net.URL;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renankaic.findtheurls.domain.CrawledUrl;

@Repository
public interface CrawledUrlRepository extends JpaRepository<CrawledUrl, Long> {

	Optional<CrawledUrl> findByUrl(URL url);
	
	

}
