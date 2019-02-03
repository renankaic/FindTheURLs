package com.renankaic.findtheurls.resources;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
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

import com.renankaic.findtheurls.domain.CrawledUrl;
import com.renankaic.findtheurls.domain.dto.CrawlerParameterDTO;
import com.renankaic.findtheurls.services.CrawledUrlService;
import com.renankaic.findtheurls.services.crawlers.SpiderWebCrawler;

@RestController
@RequestMapping(value="/urls")
public class UrlResource {
	
	@Autowired
	private CrawledUrlService crawledUrlService;
	
	@Autowired
	private SpiderWebCrawler crawlerSpider;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {		
		CrawledUrl obj = crawledUrlService.find(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	@GetMapping()
	public List<CrawledUrl> list() {
		return null;
	}
		
	@PostMapping(value="/find/crawler")
	public ResponseEntity<CrawledUrl> findAllByCrawler(@RequestBody CrawlerParameterDTO crawlerDto){
		
		//This method use a simple type of Crawler to search some available links in a
		//specified URL
		//This one doesn't use "USER_AGENT". Some web servers will not allow to get
		//all links on a page - findAllBySpider is better
		//https://www.mkyong.com/java/jsoup-basic-web-crawler-example/
		
		try {
			
			URL objUrl = new URL(crawlerDto.getUrl());			
			HashSet<String> urls = crawledUrlService.findTheUrls(objUrl, crawlerDto.getDepth());						
			CrawledUrl savedCrawledUrl = crawledUrlService.saveCrawledUrl(objUrl, urls);	
						
			return ResponseEntity.ok().body(savedCrawledUrl);
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
			
		} 		
		
	}
	
	@PostMapping(value="/find/spider")
	public ResponseEntity<CrawledUrl> findAllBySpider(@RequestBody CrawlerParameterDTO crawlerDto){
		
		//This method use a little more sophisticated way to find the available URLs in the
		//specified URL
		//http://www.netinstructions.com/how-to-make-a-simple-web-crawler-in-java/
		
		try {
			
			URL objUrl = new URL(crawlerDto.getUrl());			
			HashSet<String> urls = crawlerSpider.search(objUrl.toString(), crawlerDto.getDepth()).getLinks();
			CrawledUrl savedCrawledUrl = crawledUrlService.saveCrawledUrl(objUrl, urls);	
			
			return ResponseEntity.ok().body(savedCrawledUrl);			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@PostMapping()
	public List<CrawledUrl> create(@RequestBody CrawledUrl body){
		return null;		
	}
	
	@PutMapping()
	public List<CrawledUrl> update(@RequestBody CrawledUrl body){
		return null;
	}
	
	@DeleteMapping()
	public List<CrawledUrl> delete(@RequestParam Integer id){
		return null;
	}
	
}
