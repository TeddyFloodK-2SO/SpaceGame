package com.spacegame.dao;

import com.spacegame.entity.PlayerEntity;
import com.spacegame.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PlayerDao {
    public void savePlayer(PlayerEntity player) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(player);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();

        } finally {
            entityManager.close();
        }
    }

    public PlayerEntity findByUserId(Integer userId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        PlayerEntity player = null;

        try {
            player = entityManager.createQuery("SELECT p FROM PlayerEntity p WHERE p.user.id = :uid", PlayerEntity.class)
                    .setParameter("uid", userId)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Player for userId " + userId + " not found");
        } finally {
            entityManager.close();
        }

        return player;
    }

    public void updatePlayer(PlayerEntity player){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(player);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }
}
