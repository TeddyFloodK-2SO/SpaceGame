package com.spacegame.dao;

import com.spacegame.entity.User;
import com.spacegame.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

//     Добавляем акк
public class UserDao {
    public void saveUser(User user) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(user);
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

//    Вспомогательный метод для поиска аккаунта. Нужен для уникальности логинов
    public User findByUsername(String username) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        User user = null;
        try {
            user = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();

        } catch (Exception e) {
            System.out.println("User : " + username + " not found");

        } finally {
            entityManager.close();
        }

        return user;
    }
}
