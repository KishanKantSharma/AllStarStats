package com.example.NBA_PlayersCatalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WebsiteDTO {
	
	private String name; 
	private String address; 
	private String base; 
	private String selectorUrl; 
	private String selectorTitle; 
	
}
