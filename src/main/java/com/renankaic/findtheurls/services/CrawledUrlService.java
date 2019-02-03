package com.renankaic.findtheurls.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renankaic.findtheurls.domain.CrawledUrl;
import com.renankaic.findtheurls.domain.FoundUrl;
import com.renankaic.findtheurls.repositories.CrawledUrlRepository;
import com.renankaic.findtheurls.services.crawlers.BasicWebCrawlerService;

@Service
public class CrawledUrlService {
	
	@Autowired
	private CrawledUrlRepository crawledUrlRepository;
	
	@Autowired
	BasicWebCrawlerService basicWebCrawler;
	
	public CrawledUrl find(Long id) {		
		Optional<CrawledUrl> obj = crawledUrlRepository.findById(id);
		return obj.orElse(null);	
	}
	
	public CrawledUrl findByUrl(URL url) {
		Optional<CrawledUrl> obj = crawledUrlRepository.findByUrl(url);
		return obj.orElse(null);
	}
	
	public CrawledUrl save(CrawledUrl obj) {
		if(  obj != null ) {			
			crawledUrlRepository.save(obj);		
			return obj;
		} else {
			return null;
		}
	}
	
	public CrawledUrl saveCrawledUrl(URL objUrl, HashSet<String> urls) {
		
		//Checks if the URL already exists in database
		CrawledUrl crawledUrl = this.findByUrl(objUrl);
		if ( crawledUrl != null ) {
			
			//Clear all the found URLs before to add the new ones
			crawledUrl.getFoundUrls().clear();
			crawledUrl.setLastUpdate(new Date());
			
		} else {
			
			//Creates a new obj to record in database
			crawledUrl = new CrawledUrl();
			crawledUrl.setUrl(objUrl);
			crawledUrl.setCreationDate(new Date());
			crawledUrl.setLastUpdate(crawledUrl.getCreationDate());
			this.save(crawledUrl);
							
		}			
		
		//Adds the found urls to the crawledURL
		for ( String url: urls ) {
			this.addFoundUrl(url, crawledUrl);
		}
		
		//Saves the crawledURL to database and returns the entity
		crawledUrl = save(crawledUrl);	
		return crawledUrl;
		
	}
	
	public void addFoundUrl(String url, CrawledUrl fatherUrl) {
		
		//Tries to add a found url into a CrawledUrl (father URL)
		try {
			
			FoundUrl fUrl = new FoundUrl();
			fUrl.setUrl(new URL(url));
			fUrl.setCrawledUrl(fatherUrl);
			fatherUrl.getFoundUrls().add(fUrl);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public HashSet<String> findTheUrls( URL url, Integer depth ) {	
		
		//Gets and clear all the strings inside the HashSet
		//If I don't do this, for some reason, the HashSet comes with
		//the previous found URLs
		basicWebCrawler.getLinks().clear();
		basicWebCrawler.setDesiredDepth(depth);
		basicWebCrawler.getPageLinks(url.toString(), 1);
		return basicWebCrawler.getLinks();
		
	}
}
