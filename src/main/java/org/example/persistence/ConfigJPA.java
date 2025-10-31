package org.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConfigJPA {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PuClientes");

    public static EntityManager getEntityMenager(){
        return emf.createEntityManager();
    }

    public static void close(){
        emf.close();
    }
}
