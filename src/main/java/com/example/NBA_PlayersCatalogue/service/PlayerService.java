package com.example.NBA_PlayersCatalogue.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.example.NBA_PlayersCatalogue.dao.PlayerRepository;
import com.example.NBA_PlayersCatalogue.entity.PlayerEntity;

import jakarta.transaction.Transactional;

@Service
public class PlayerService {
	
	private PlayerRepository playerRepository; 
	
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository; 
	}
	
	public List<PlayerEntity> getPlayers(){
		return playerRepository.findAll();
	}
	
	public List<PlayerEntity> getPlayersFromTeam(String teamName){
		return playerRepository.findAll().stream()
				.filter(player -> teamName.equals(player.getTeam()))
				.toList();
	}
	
	public List<PlayerEntity> getPlayerByName(String searchText){
		return playerRepository.findAll().stream()
				.filter(player -> player.getPlayerName().toLowerCase().contains(searchText.toLowerCase()))
				.toList();
	}
	
	public List<PlayerEntity> getByPosition(String searchText){
		return playerRepository.findAll().stream()
				.filter(player -> player.getPosition().toLowerCase().contains(searchText.toLowerCase()))
				.toList();
	}
	
	public List<PlayerEntity> getPlayerByTeamAndPosition(String team, String position){
		return playerRepository.findAll().stream()
				.filter(player -> team.equals(player.getTeam()) && position.equals(player.getPosition()))
				.toList();
	}
	
	@Transactional
	public PlayerEntity addPlayer(PlayerEntity player) {
		playerRepository.save(player);
		return player; 
	}
	
	@Transactional
	public PlayerEntity updatePlayer(PlayerEntity updatedPlayer) {
		Optional<PlayerEntity> existingPlayer = playerRepository.findByPlayerName(updatedPlayer.getPlayerName());
		
		if(existingPlayer.isPresent()) {
			PlayerEntity playerToUpdate = existingPlayer.get();
			playerToUpdate.setPlayerName(updatedPlayer.getPlayerName());
			playerToUpdate.setTeam(updatedPlayer.getTeam());
			playerToUpdate.setPosition(updatedPlayer.getPosition());
			playerToUpdate.setPoints(updatedPlayer.getPoints());
			
			playerRepository.save(playerToUpdate); 
			
			return playerToUpdate; 
		}
		
		return null;
	}
	
	@Transactional
	public void deletePlayer(String playerName) {
		playerRepository.deleteByPlayerName(playerName);
	}
}





