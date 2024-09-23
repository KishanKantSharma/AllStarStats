package com.example.NBA_PlayersCatalogue.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.example.NBA_PlayersCatalogue.dto.ArticleDTO;
import com.example.NBA_PlayersCatalogue.dto.WebsiteDTO;
import com.example.NBA_PlayersCatalogue.service.NewsServiceHelper;
import com.example.NBA_PlayersCatalogue.service.interfaces.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	
	private static final List<WebsiteDTO> website = NewsServiceHelper.getWebsites();
	private static final List<WebsiteDTO> nbawebsite = NewsServiceHelper.getNbaWebsites();

	public static List<ArticleDTO> fetchArticles(WebsiteDTO website) {
		
		List<ArticleDTO> articles = new ArrayList<>();
        try {
            Document document = Jsoup.connect(website.getAddress()).get();
            Elements elements = document.select(website.getSelectorUrl());
            for (org.jsoup.nodes.Element element : elements) {
                String title = element.text().trim();
                String articleUrl = element.attr("href");

                // Prepend base URL if it's a relative link
                if (!articleUrl.startsWith("http")) {
                    articleUrl = website.getBase() + articleUrl;
                }

                articles.add(new ArticleDTO(title, articleUrl, website.getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articles;
	}
	
	public static List<ArticleDTO> fetchNbaArticles(WebsiteDTO website) {
        List<ArticleDTO> nbaArticles = new ArrayList<>();
        try {
            Document document = Jsoup.connect(website.getAddress()).get();
            Elements titles = document.select(website.getSelectorTitle());
            Elements urls = document.select(website.getSelectorUrl());

            for (int i = 0; i < titles.size(); i++) {
                String title = titles.get(i).text().trim();
                String url = website.getBase() + urls.get(i).attr("href");
                nbaArticles.add(new ArticleDTO(title, url, website.getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nbaArticles;
    }

	@Override
	 public List<ArticleDTO> getArticles() {
        List<ArticleDTO> articles = new ArrayList<>();
        
        for (WebsiteDTO website : website) {
            articles.addAll(fetchArticles(website));
        }

        for (WebsiteDTO website : nbawebsite) {
            articles.addAll(fetchNbaArticles(website));
        }

        return articles;
    }
	

}
