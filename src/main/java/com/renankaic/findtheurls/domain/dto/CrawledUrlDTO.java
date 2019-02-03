package com.renankaic.findtheurls.domain.dto;

import java.io.Serializable;
import java.util.Set;

import com.renankaic.findtheurls.domain.CrawledUrl;
import com.renankaic.findtheurls.domain.FoundUrl;

/*
 * I use this class to transform a Crawled Url to DTO (Data Transfer Object)
 * It makes easier to customize how the JSON will be returned in the requests
 */
public class CrawledUrlDTO implements Serializable{
		
	private static final long serialVersionUID = 1L;
	private CrawledUrl crawledUrl;
	private Set<FoundUrl> foundUrls;
	
	public CrawledUrlDTO() {
		
	}

	public CrawledUrlDTO(CrawledUrl crawledUrl, Set<FoundUrl> foundUrls) {
		super();
		this.crawledUrl = crawledUrl;
		this.foundUrls = foundUrls;
	}
	
	public CrawledUrlDTO toDto(CrawledUrl crawledUrl) {
		
		//Transform a CrawledURL to a CrawledUrlDTO which customize the JSON that will be sent as response from a request
		
		this.crawledUrl = crawledUrl;
		this.foundUrls = crawledUrl.getFoundUrls();
		return this;
		
	}

	public CrawledUrl getCrawledUrl() {
		return crawledUrl;
	}

	public void setCrawledUrl(CrawledUrl crawledUrl) {
		this.crawledUrl = crawledUrl;
	}

	public Set<FoundUrl> getFoundUrls() {
		return foundUrls;
	}

	public void setFoundUrls(Set<FoundUrl> foundUrls) {
		this.foundUrls = foundUrls;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crawledUrl == null) ? 0 : crawledUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CrawledUrlDTO other = (CrawledUrlDTO) obj;
		if (crawledUrl == null) {
			if (other.crawledUrl != null)
				return false;
		} else if (!crawledUrl.equals(other.crawledUrl))
			return false;
		return true;
	}
	
	
	

}
