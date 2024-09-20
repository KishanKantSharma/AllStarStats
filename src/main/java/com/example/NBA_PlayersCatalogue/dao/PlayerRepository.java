package com.example.NBA_PlayersCatalogue.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NBA_PlayersCatalogue.entity.PlayerEntity;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, String>{
	
	void deleteByPlayerName(String playerName); 
	
	Optional<PlayerEntity> findByPlayerName(String name); 

}
