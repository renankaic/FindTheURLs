package com.renankaic.findtheurls.services;


import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.renankaic.findtheurls.domain.CrawledUrl;



@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawledUrlServiceTest {
	

	@Autowired
	private CrawledUrlService crawledUrlService;
	
	private CrawledUrl expectedObject;
	
	@Before
	public void setup() throws MalformedURLException {
		
		//I will crawl a URL before the test starts
		URL objUrl = new URL("http://monkkee.com");			
		HashSet<String> urls = crawledUrlService.findTheUrlsSpiderCrawler(objUrl, 5);
		expectedObject = crawledUrlService.saveCrawledUrl(objUrl, urls, "Monkke");
		
	}
	
	@Test
	public void testGetCrawledUrlById() {
		
		//Scenario
		long id = 1;
		
		//action
		CrawledUrl crawledUrl = crawledUrlService.find(id);
		
		//verify
		assertThat(crawledUrl).isNotNull().isEqualTo(expectedObject);
		
	}
	
	@Test
	public void testGetCrawledUrlByUrl() throws MalformedURLException {
		
		//Scenario
		String url = "http://monkkee.com";
		
		//action
		CrawledUrl crawledUrl = crawledUrlService.findByUrl(new URL(url));
		
		//verify
		assertThat(crawledUrl).isNotNull().isEqualTo(expectedObject);
		
	}
	
	@Test
	public void testGetAllCrawledUrls() {
		
		//Scenario
		//No scenario, I just want to get all the crawled urls
		
		//Action
		List<CrawledUrl> list = crawledUrlService.findAll();
		
		//verify
		assertThat(list).isNotNull().isNotEmpty().containsAnyOf(expectedObject);		
		
	}
	
	
}

