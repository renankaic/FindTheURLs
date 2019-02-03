package com.renankaic.findtheurls.domain;

import java.io.Serializable;
import java.net.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FoundUrl implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(1000)")
	private URL url;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="crawledurl_id")
	private CrawledUrl crawledUrl;
	
	public FoundUrl() {
		
	}

	public FoundUrl(Long id, URL url, CrawledUrl crawledUrl) {
		super();
		this.id = id;
		this.url = url;
		this.crawledUrl = crawledUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public CrawledUrl getCrawledUrl() {
		return crawledUrl;
	}

	public void setCrawledUrl(CrawledUrl crawledUrl) {
		this.crawledUrl = crawledUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crawledUrl == null) ? 0 : crawledUrl.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		FoundUrl other = (FoundUrl) obj;
		if (crawledUrl == null) {
			if (other.crawledUrl != null)
				return false;
		} else if (!crawledUrl.equals(other.crawledUrl))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	

}
