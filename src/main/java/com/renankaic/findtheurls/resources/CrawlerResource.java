package com.renankaic.findtheurls.resources;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.renankaic.findtheurls.domain.dto.CrawledUrlDTO;
import com.renankaic.findtheurls.domain.dto.CrawlerParameterDTO;
import com.renankaic.findtheurls.services.CrawledUrlService;
import com.renankaic.findtheurls.services.crawlers.SpiderWebCrawler;

@RestController
@RequestMapping(value="/urls")
public class CrawlerResource {
	
	@Autowired
	private CrawledUrlService crawledUrlService;
	
	@Autowired
	private SpiderWebCrawler crawlerSpider;
	
	@GetMapping()
	public ResponseEntity<List<CrawledUrl>> list() {
		
		//List all the Crawled Urls
		List<CrawledUrl> list = crawledUrlService.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CrawledUrlDTO> listFoundUrls(@PathVariable Long id) {	
		
		//List a specific Crawled URL by Id
		CrawledUrl obj = crawledUrlService.find(id);
		
		if( obj == null)
			return ResponseEntity.notFound().build();
		
		//Transform to DTO
		CrawledUrlDTO crawledUrlDTO = new CrawledUrlDTO(obj, obj.getFoundUrls());
		
		return ResponseEntity.ok().body(crawledUrlDTO);	
		
	}
				
			
	@PostMapping(value="/find/crawler",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CrawledUrlDTO> findAllByCrawler(@RequestBody CrawlerParameterDTO crawlerDto){
		
		//This method use a simple type of Crawler to search some available links in a
		//specified URL
		//This one doesn't use "USER_AGENT". Some web servers will not allow to get
		//all links on a page - findAllBySpider is better
		//https://www.mkyong.com/java/jsoup-basic-web-crawler-example/
		
		try {
			
			URL objUrl = new URL(crawlerDto.getUrl());			
			HashSet<String> urls = crawledUrlService.findTheUrls(objUrl, crawlerDto.getDepth());						
			CrawledUrl savedCrawledUrl = crawledUrlService.saveCrawledUrl(objUrl, urls, crawlerDto.getSiteName());	
		
			//Transform to DTO
			CrawledUrlDTO crawledUrlDTO = new CrawledUrlDTO(savedCrawledUrl, savedCrawledUrl.getFoundUrls());
		
			return ResponseEntity.ok().body(crawledUrlDTO);
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
			
		} 		
		
	}
	
	@PostMapping(value="/find/spider",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CrawledUrlDTO> findAllBySpider(@RequestBody CrawlerParameterDTO crawlerDto){
		
		//This method use a little more sophisticated way to find the available URLs in the
		//specified URL
		//http://www.netinstructions.com/how-to-make-a-simple-web-crawler-in-java/
		
		try {
			
			URL objUrl = new URL(crawlerDto.getUrl());			
			HashSet<String> urls = crawlerSpider.search(objUrl.toString(), crawlerDto.getDepth()).getLinks();
			CrawledUrl savedCrawledUrl = crawledUrlService.saveCrawledUrl(objUrl, urls,crawlerDto.getSiteName());
			
			//Transform to DTO
			CrawledUrlDTO crawledUrlDTO = new CrawledUrlDTO(savedCrawledUrl, savedCrawledUrl.getFoundUrls());
		
			return ResponseEntity.ok().body(crawledUrlDTO);		
			
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
