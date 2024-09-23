package com.example.NBA_PlayersCatalogue.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.NBA_PlayersCatalogue.dto.WebsiteDTO;

@Component
public class NewsServiceHelper {
	
	public static List<WebsiteDTO> getWebsites() {
        return Arrays.asList(
        		new WebsiteDTO("espn", "https://www.espn.com/nba/", "https://www.espn.com", ".headlineStack__header + section > ul > li > a", ""),
                new WebsiteDTO("bleacher_report", "https://bleacherreport.com/nba", "", ".articleTitle", ""),
                new WebsiteDTO("slam", "https://www.slamonline.com/", "", ".h-bloglist-block-content-top > h3 > a", ""),
                new WebsiteDTO("yahoo", "https://sports.yahoo.com/nba/?guccounter=1", "https://sports.yahoo.com", ".js-content-viewer", ""),
                new WebsiteDTO("nba", "https://www.nba.com/news/category/top-stories", "https://www.nba.com", ".flex-1 > a", "")
        );
    }
	
	public static List<WebsiteDTO> getNbaWebsites(){
		return Arrays.asList(
				 new WebsiteDTO("nba", "https://www.nba.com/news/category/top-stories", "https://www.nba.com", ".ArticleTile_tileMainContent__c_bU1 > a", ".ArticleTile_tileMainContent__c_bU1 > a > header > h3 > span"),
			     new WebsiteDTO("nba_canada", "https://www.sportingnews.com/ca/nba/news", "https://www.sportingnews.com", ".list-item__title > a", ".list-item__title > a")
				);
				
	}

}
