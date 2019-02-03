package com.renankaic.findtheurls.services.crawlers;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpiderWebCrawler {
	
	private Set<String> pagesVisited = new HashSet<String>();
	private List<String> pagesToVisit = new LinkedList<String>();
	
	@Autowired
	private SpiderLegWebCrawlerService spiderLeg;
	
	public SpiderWebCrawler() {
		this.spiderLeg = new SpiderLegWebCrawlerService();
	}
	
	/**
	   * Creates spider "legs" that makes an HTTP request for the specified URL and parse the response
	   * 
	   * @param url
	   *            - The starting point of the spider
	   */
	public SpiderLegWebCrawlerService search(String url, Integer numberOfPagesToSearch) {
		
		resetSpiderLeg();
		
		while(this.pagesVisited.size() < numberOfPagesToSearch)
        {
            String currentUrl;            
            
            if(this.pagesToVisit.isEmpty()){
            	
                currentUrl = url;
                this.pagesVisited.add(url);
                
            } else {
                currentUrl = this.nextUrl();
            }
            
            spiderLeg.crawl(currentUrl); 
            
            this.pagesToVisit.addAll(spiderLeg.getLinks());
            
        }
		
        System.out.println(
        		String.format(
        				"**FINALIZED** Visited %s page(s) inside the specified URL",
        				this.pagesVisited.size()
        		)
        );
        
        return this.getSpiderLeg();
		
	}
	
	 /**	  
	   * Returns the next URL to visit. Also makes sure that doesn't return a URL that has already
	   * visited
	   * 
	   * @return
	   */
	private String nextUrl() {
		
		String nextUrl;
		do {
			nextUrl = this.pagesToVisit.remove(0);
		} while( this.pagesVisited.contains(nextUrl) );
		this.pagesVisited.add(nextUrl);
		return nextUrl;
		
	}
	
	private void resetSpiderLeg() {
		
		//Clear all links before start to crawl
		spiderLeg.getLinks().clear();
		pagesVisited.clear();
		pagesToVisit.clear();
		
	}
	
	public SpiderLegWebCrawlerService getSpiderLeg() {
		return this.spiderLeg;
	}
}	
