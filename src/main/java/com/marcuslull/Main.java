package com.marcuslull;

import com.marcuslull.entities.*;
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

            Product product = em.find(Product.class, 1);
            System.out.println("Product from product: " + product);
            product.getCategories().forEach(category -> System.out.println("Category from product: " + category));
            product.getSuppliers().forEach(supplier -> System.out.println("Supplier from product: " + supplier));
            product.getPrices().forEach(price -> System.out.println("Price from product: " + price));
            product.getNotes().forEach(note -> System.out.println("Note from product: " + note));

            Category category = em.find(Category.class, 1);
            System.out.println("Category from category: " + category);

            Supplier supplier = em.find(Supplier.class, 1);
            System.out.println("Supplier from supplier: " + supplier);

            Price price = em.find(Price.class, 1);
            System.out.println("Price from price: " + price);

            Note note = em.find(Note.class, 1);
            System.out.println("Note from note: " + note);

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