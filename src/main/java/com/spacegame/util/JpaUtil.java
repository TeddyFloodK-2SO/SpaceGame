package com.spacegame.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil{
    private static final EntityManagerFactory entityManagerFactory;

    static{
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("spacegamePU");
        }
        catch(Throwable e){
            System.err.println("Error initializing EntityManagerFactory: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    public static void close(){
        entityManagerFactory.close();
    }
}
