package com.renankaic.findtheurls.crawlers;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class BasicWebCrawler {
	
	//Says the DEPTH that the crawler will scan in the URL
	//I recommend using "3" as depth value. For some reason, any value above 3
	//doesn't bring any URL
	private static final int MAX_DEPTH = 6;
	private HashSet<String> links;

    public BasicWebCrawler() {
        links = new HashSet<String>();
    }
    
    //Recursively gets the found URLs according to the specified depth
    public void getPageLinks(String URL, int depth) {
    	
        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
        	
            //System.out.println(">> Depth: " + depth + " [" + URL + "]");
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

	
    public HashSet<String> getLinks(){
    	return links;
    }
}
