package com.example.NBA_PlayersCatalogue.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.NBA_PlayersCatalogue.dto.ArticleDTO;
import com.example.NBA_PlayersCatalogue.entity.PlayerEntity;
import com.example.NBA_PlayersCatalogue.service.PlayerService;
import com.example.NBA_PlayersCatalogue.service.interfaces.NewsService;

@RestController
@RequestMapping(path = "v1/players")
public class CatalogueController {
	
	private final PlayerService playerService;
	
	private NewsService newsService; 
	
	public CatalogueController(PlayerService playerService, NewsService newsService) {
		this.playerService = playerService; 
		this.newsService = newsService;
	}
	
	@GetMapping
	public List<PlayerEntity> getPlayers(	
			@RequestParam(required = false) String playerName,
			@RequestParam(required = false) String position,
			@RequestParam(required = false) String team) {
		
		if (team != null  && position != null ) {
			return playerService.getPlayerByTeamAndPosition(team, position);
		}
		else if(team != null) {
			return playerService.getPlayersFromTeam(team);
		}
		else if(position != null) {
			return playerService.getByPosition(position);
		}
		else if(playerName != null) {
			return playerService.getPlayerByName(playerName);
		}
		else {
			return playerService.getPlayers();
		}
	}
	
	@PostMapping
	public ResponseEntity<PlayerEntity> addPlayer(@RequestBody PlayerEntity player){
		PlayerEntity createdPlayer = playerService.addPlayer(player); 
		return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<PlayerEntity> updatePlayer(@RequestBody PlayerEntity player){
		PlayerEntity response = playerService.updatePlayer(player);
		
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{playerName}")
	public ResponseEntity<String> deletePlayer(@PathVariable String playerName){
		playerService.deletePlayer(playerName);
		return new ResponseEntity<>("Player Deleted Successdully.", HttpStatus.OK);
	}
	
	@GetMapping("/news")
	public ResponseEntity<List<ArticleDTO>> getNews(){
		List<ArticleDTO> articles;
		
		articles = newsService.getArticles();
		
		System.out.println("Articles response: " + articles);
		
		return new ResponseEntity<>(articles, HttpStatus.OK);
	}
}





