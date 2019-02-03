package com.renankaic.findtheurls.domain.dto;

import java.io.Serializable;

public class SpiderCrawlerParameterDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String url;
	private Integer pagesToCrawl;
	
	public SpiderCrawlerParameterDTO() {
		
	}

	public SpiderCrawlerParameterDTO(String url, Integer pagesToCrawl) {
		super();
		this.url = url;
		this.pagesToCrawl = pagesToCrawl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPagesToCrawl() {
		return pagesToCrawl;
	}

	public void setPagesToCrawl(Integer pagesToCrawl) {
		this.pagesToCrawl = pagesToCrawl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pagesToCrawl == null) ? 0 : pagesToCrawl.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		SpiderCrawlerParameterDTO other = (SpiderCrawlerParameterDTO) obj;
		if (pagesToCrawl == null) {
			if (other.pagesToCrawl != null)
				return false;
		} else if (!pagesToCrawl.equals(other.pagesToCrawl))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	
	
}
