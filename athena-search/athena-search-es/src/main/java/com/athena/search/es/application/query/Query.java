package com.athena.search.es.application.query;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Query {
	
	private String name;
	
	private Integer pageSize = 10;
	
	private Integer pageNumber = 1;
	
	private Long cityId;
	
	private Double longitude;
	
	private Double latitude;
	
	private List<Integer> idList;
	
	private List<Integer> starList;
	
	private Double minPrice;
	
	private Double maxPrice;
	
	private Boolean sortByScore;
	
	private Boolean sortByPrice;
	
	private List<String> fan;
	
	private Integer roomCount;
	
	private Integer roomCount2;

	@Override
	public String toString() {
		return "Query [name=" + name + ", pageSize=" + pageSize
				+ ", pageNumber=" + pageNumber + ", cityId=" + cityId
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", idList=" + idList + ", starList=" + starList
				+ ", minPrice=" + minPrice + ", maxPrice=" + maxPrice
				+ ", sortByScore=" + sortByScore + ", sortByPrice="
				+ sortByPrice + ", fan=" + fan + ", roomCount=" + roomCount
				+ ", roomCount2=" + roomCount2 + "]";
	}

}
