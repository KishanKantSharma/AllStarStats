package com.example.NBA_PlayersCatalogue.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="player_stats")
public class PlayerEntity {
	
	@Id
	@Column(name = "player", unique = true)
	private String playerName; 
	private int age; 
	private String team;
	private String position; 
	private int gamesPlayed;
	private int freeThrows; 
	private int totalRebounds; 
	private int assists; 
	private int steals; 
	private int blocks; 
	private int fouls; 
	private int points; 

}
