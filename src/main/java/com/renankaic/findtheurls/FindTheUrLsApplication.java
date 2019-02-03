package com.renankaic.findtheurls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.renankaic.findtheurls.services.UrlService;

@SpringBootApplication
public class FindTheUrLsApplication implements CommandLineRunner{
	
	@Autowired
	UrlService urlService;
	
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

