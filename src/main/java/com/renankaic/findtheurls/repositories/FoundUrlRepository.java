package com.renankaic.findtheurls.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renankaic.findtheurls.domain.FoundUrl;

@Repository
public interface FoundUrlRepository extends JpaRepository<FoundUrl, Long> {
	
	

}
