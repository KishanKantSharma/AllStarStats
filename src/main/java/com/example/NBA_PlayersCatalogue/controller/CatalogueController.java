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

import com.example.NBA_PlayersCatalogue.entity.PlayerEntity;
import com.example.NBA_PlayersCatalogue.service.PlayerService;

@RestController
@RequestMapping(path = "api/v1/players")
public class CatalogueController {
	
	private final PlayerService playerService;
	
	public CatalogueController(PlayerService playerService) {
		this.playerService = playerService; 
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
		return new ResponseEntity<PlayerEntity>(createdPlayer, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<PlayerEntity> updatePlayer(@RequestBody PlayerEntity player){
		PlayerEntity response = playerService.updatePlayer(player);
		
		if(response != null) {
			return new ResponseEntity<PlayerEntity>(response, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<PlayerEntity>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{playerName}")
	public ResponseEntity<String> deletePlayer(@PathVariable String playerName){
		playerService.deletePlayer(playerName);
		return new ResponseEntity<String>("Player Deleted Successdully.", HttpStatus.OK);
	}
	
}





