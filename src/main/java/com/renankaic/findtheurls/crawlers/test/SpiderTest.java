package com.renankaic.findtheurls.crawlers.test;
import com.renankaic.findtheurls.crawlers.SpiderWebCrawler;

public class SpiderTest {

	 /**
     * This is our test. It creates a spider (which creates spider legs) and crawls the web.
     * 
     * @param args
     *            - not used
     */
    public static void main(String args[])
    {
        SpiderWebCrawler spider = new SpiderWebCrawler();
        spider.search("http://monkkee.com/", 25);        
    }
	
}
