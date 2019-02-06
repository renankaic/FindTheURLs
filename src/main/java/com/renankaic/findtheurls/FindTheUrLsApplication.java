package com.renankaic.findtheurls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.renankaic.findtheurls.services.CrawledUrlService;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class FindTheUrLsApplication implements CommandLineRunner{
	
	@Autowired
	CrawledUrlService urlService;
	
	public static void main(String[] args) {
		SpringApplication.run(FindTheUrLsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		//Instantiate some example objects
		/*URL url1 = new URL("https://www.monkkee.com/en/");
		URL url2 = new URL("https://www.netflix.com/br/");		
		urlService.saveAll(Arrays.asList(url1, url2));*/
		//spiderTest.crawl();
		
	}

}

