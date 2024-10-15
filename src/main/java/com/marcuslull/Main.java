package com.marcuslull;

import com.marcuslull.entities.*;
import com.marcuslull.persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.hibernate.jpa.HibernatePersistenceProvider;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), null);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Product product = entityManager.find(Product.class, 1);
            System.out.println("Product from product: " + product);
            product.getCategories().forEach(category -> System.out.println("Category from product: " + category));
            product.getSuppliers().forEach(supplier -> System.out.println("Supplier from product: " + supplier));
            product.getPrices().forEach(price -> System.out.println("Price from product: " + price));
            product.getNotes().forEach(note -> System.out.println("Note from product: " + note));

            Category category = entityManager.find(Category.class, 1);
            System.out.println("Category from category: " + category);

            Supplier supplier = entityManager.find(Supplier.class, 1);
            System.out.println("Supplier from supplier: " + supplier);

            Price price = entityManager.find(Price.class, 1);
            System.out.println("Price from price: " + price);

            Note note = entityManager.find(Note.class, 1);
            System.out.println("Note from note: " + note);

            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
        finally {
            entityManager.close();
        }
    }
}