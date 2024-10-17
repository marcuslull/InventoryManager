package com.marcuslull;

import com.marcuslull.entities.*;
import com.marcuslull.entities.validators.ValidationChecker;
import com.marcuslull.persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), null);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();




            // READ EXAMPLE

            Product product1 = entityManager.find(Product.class, 1);
            System.out.println("Product from product: " + product1);
            product1.getCategories().forEach(category -> System.out.println("Category from product: " + category));
            product1.getSuppliers().forEach(supplier -> System.out.println("Supplier from product: " + supplier));
            product1.getPrices().forEach(price -> System.out.println("Price from product: " + price));
            product1.getNotes().forEach(note -> System.out.println("Note from product: " + note));

            Category category1 = entityManager.find(Category.class, 1);
            System.out.println("Category from category: " + category1);

            Supplier supplier1 = entityManager.find(Supplier.class, 1);
            System.out.println("Supplier from supplier: " + supplier1);

            Price price1 = entityManager.find(Price.class, 1);
            System.out.println("Price from price: " + price1);

            Note note1 = entityManager.find(Note.class, 1);
            System.out.println("Note from note: " + note1);




            // WRITE EXAMPLE

            Product product2 = new Product();
            product2.setProductName("Some Product");
            product2.setSku("123456789");
            product2.setQuantity(50);
            product2.setDescription("Some Description");
            product2.setWeight(BigDecimal.valueOf(2.5));

            Supplier supplier2 = new Supplier();
            supplier2.setSupplierName("Some Supplier");
            supplier2.setPhone("555-555-5555");
            supplier2.setWebsite("www.somewebsite.com");
            supplier2.setRepresentative("Some Representative");

            Category category2 = entityManager.find(Category.class, 1);

            Note note2 = new Note();
            note2.setNote("Some note about Some Product");

            Price price2 = new Price();
            price2.setPrice(BigDecimal.valueOf(.01));

            product2.getSuppliers().add(supplier2);
            product2.getCategories().add(category2);
            product2.getNotes().add(note2);
            product2.getPrices().add(price2);

            supplier2.getProducts().add(product2);
            supplier2.getPrices().add(price2);

            category2.getProducts().add(product2);

            note2.setProduct(product2);

            price2.setProduct(product2);
            price2.setSupplier(supplier2);

            entityManager.persist(product2);
            entityManager.persist(supplier2);
            entityManager.persist(note2);
            entityManager.persist(price2);




            // UPDATE EXAMPLE

            Product product3 = entityManager.find(Product.class, 1);
            product3.setDescription("An updated description");
            product3.getCategories().forEach(category -> category.getProducts().remove(product3));
            product3.getSuppliers().forEach(supplier -> supplier.getProducts().remove(product3));
            product3.getNotes().forEach(note -> {
                note.setProduct(null);
                if (ValidationChecker.isValidEntity(note)) entityManager.merge(note);
                else entityManager.remove(note);
            });
            entityManager.merge(product3);




            // HARD DELETE EXAMPLE

            Product product4 = entityManager.find(Product.class, 2);
            product4.getCategories().forEach(category -> category.getProducts().remove(product4));
            product4.getSuppliers().forEach(supplier -> supplier.getProducts().remove(product4));
            product4.getNotes().forEach(note -> {
                note.setProduct(null);
                if (ValidationChecker.isValidEntity(note)) entityManager.merge(note);
                else entityManager.remove(note);
            });
            entityManager.remove(product4);

            Supplier supplier4 = entityManager.find(Supplier.class, 2);
            supplier4.getNotes().forEach(note -> {
                note.setSupplier(null);
                if (ValidationChecker.isValidEntity(note)) entityManager.merge(note);
                else entityManager.remove(note);
            });
            entityManager.remove(supplier4);




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