package com.renankaic.findtheurls.services.crawlers;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class BasicWebCrawlerService {
	
	//Says the DEPTH that the crawler will scan in the URL
	//I recommend using "3" as depth value. For some reason, any value above 3 rarely find links
	//doesn't bring any URL
	private int desiredDepth;
	private HashSet<String> links;

    public BasicWebCrawlerService() {
        links = new HashSet<String>();
    }
    
    //Recursively gets the found URLs according to the specified depth
    public void getPageLinks(String URL, int depth) {
    	
        if ((!links.contains(URL) && (depth < desiredDepth))) {
        	
            try {
            	
                links.add(URL);
                
                //Jsoup connect to the specified URL and get the HTML content
                Document document = Jsoup.connect(URL).get();
                
                //Get all links from the HTML content
                Elements linksOnPage = document.select("a[href]");

                depth++;
                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"), depth);
                }
                
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
            
        }
        
    }
	
    public void setDesiredDepth(Integer deptInteger) {
    	this.desiredDepth = deptInteger;
    }
    
    public HashSet<String> getLinks(){
    	return links;
    }
}
