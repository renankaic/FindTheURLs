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

import com.renankaic.findtheurls.crawlers.SpiderWebCrawler;
import com.renankaic.findtheurls.domain.Url;
import com.renankaic.findtheurls.services.UrlService;

@RestController
@RequestMapping(value="/urls")
public class UrlResource {
	
	@Autowired
	private UrlService service;
	
	@Autowired
	private SpiderWebCrawler crawlerSpider;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {		
		Url obj = service.find(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	@GetMapping()
	public List<Url> list() {
		return null;
	}
		
	@PostMapping(value="/find/crawler")
	public ResponseEntity<?> findAllByCrawler(@RequestBody String url){
		
		//This method use a simple type of Crawler to search some available links in a
		//specified URL
		//https://www.mkyong.com/java/jsoup-basic-web-crawler-example/
		
		try {
			
			URL objUrl = new URL(url);			
			HashSet<String> urls = service.findTheUrls(objUrl);
			
			for( String foundUrl : urls ) {
				
				System.out.println(foundUrl);
				
			}
			
			return ResponseEntity.ok().build();
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
			
		} 		
		
	}
	
	@PostMapping(value="/find/spider")
	public ResponseEntity<?> findAllBySpider(@RequestBody String url){
		
		//This method use a little more sophisticated way to find the available URLs in the
		//specified URL
		try {
			
			List<String> foundLinks = crawlerSpider.search(url, 20).getLinks();
			return ResponseEntity.ok(foundLinks);			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
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
