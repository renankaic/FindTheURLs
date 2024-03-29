package com.renankaic.findtheurls.services.crawlers;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class SpiderLegWebCrawlerService {
	
	/*
	 USER_AGENT so the web server thinks the robot is a normal web browser.
	 This USER_AGENT is needed to tell to the web server from the URL 
	 that we are a "normal desktop browser".
	 Otherwise, if we don't do this some web servers won't give the web page at all because
	 it will not recognize the USER_AGENT	 
	*/
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    
	private HashSet<String> links = new HashSet<String>(); // List of found URLs
	private Document htmlDocument; // Document containg the HTML content
	
	/**
     * "Crawls" inside the content of a specified URL. Makes an HTTP request, checks the result,
     * and then gather all the links on the page.
     * 
     * @param url
     *            - The URL to crawl
     */
	public void crawl(String url) {
		
		try {
			
			//Connects to the specified URL using the specified USER_AGENT
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            
            //Gets the HTML Content
            this.htmlDocument = connection.get();

            System.out.println("Found a web page from URL: " + url);

            //Gets all <a href/> on page
            Elements linksOnPage = htmlDocument.select("a[href]");
            System.out.println("Found " + linksOnPage.size() + " links in [" + url + "] URL");
            
            //For each link, adds to the links List
            for(Element link : linksOnPage) {
            	
            	//Checks if this link wasn't add before
            	if ( !this.links.contains(link.absUrl("href")) ) {
                    this.links.add(link.absUrl("href"));
            	}
            	
            }
            
        } catch(IOException ioe) {
        	
            // If we get any error while sending the request
            System.out.println("HTTP Request error: " + ioe);
            
        }
		
	}
	
	public HashSet<String> getLinks() {
		
		return this.links;
		
	}
	
}
