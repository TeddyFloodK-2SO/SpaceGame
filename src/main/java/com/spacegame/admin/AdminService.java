package com.spacegame.admin;

import com.spacegame.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class AdminService {
    public void deleteAllAccount(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.createQuery("DELETE FROM PlayerEntity").executeUpdate();
            entityManager.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
            System.out.println("Все аккаунты удалены");
        }catch (Exception e) {
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }
}
