package com.marcuslull;

import com.marcuslull.entities.Product;
import com.marcuslull.persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), null);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Product product = em.find(Product.class, "SKU123");
            System.out.println(product);


            em.getTransaction().commit();
        }
        catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
        finally {
            em.close();
        }
    }
}