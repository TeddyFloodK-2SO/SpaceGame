package com.spacegame.service;

import com.spacegame.dao.PlayerDao;
import com.spacegame.entity.PlayerEntity;
import com.spacegame.entity.User;

import java.util.ArrayList;
import java.util.HashSet;

public class PlayerService {
    private final PlayerDao playerDao = new PlayerDao();

//    Загружает игрока по пользователю или создает нового
    public PlayerEntity getOrCreatePlayer(User user){
        PlayerEntity player = playerDao.findByUserId(user.getId());
        if(player == null){
            player = new PlayerEntity();
            player.setUser(user);
            player.setHealth(100);
            player.setMoney(0);
            player.setInventory(new HashSet<>());
            player.setCurrentLocation("arrival"); // Стартовая локация
            playerDao.savePlayer(player);
        }

        return player;
    }

//    Обновляет состояние игрока в БД
    public void updatePlayer(PlayerEntity player){
        playerDao.updatePlayer(player);
    }
}
