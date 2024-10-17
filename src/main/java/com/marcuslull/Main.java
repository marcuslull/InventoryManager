package com.marcuslull;

import com.marcuslull.entities.Product;
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

            // READ TEST

//            Product product = entityManager.find(Product.class, 1);
//            System.out.println("Product from product: " + product);
//            product.getCategories().forEach(category -> System.out.println("Category from product: " + category));
//            product.getSuppliers().forEach(supplier -> System.out.println("Supplier from product: " + supplier));
//            product.getPrices().forEach(price -> System.out.println("Price from product: " + price));
//            product.getNotes().forEach(note -> System.out.println("Note from product: " + note));
//
//            Category category = entityManager.find(Category.class, 1);
//            System.out.println("Category from category: " + category);
//
//            Supplier supplier = entityManager.find(Supplier.class, 1);
//            System.out.println("Supplier from supplier: " + supplier);
//
//            Price price = entityManager.find(Price.class, 1);
//            System.out.println("Price from price: " + price);
//
//            Note note = entityManager.find(Note.class, 1);
//            System.out.println("Note from note: " + note);


            // WRITE TEST

//            Product product = new Product();
//            product.setProductName("Some Product");
//            product.setSku("123456789");
//            product.setQuantity(50);
//            product.setDescription("Some Description");
//            product.setWeight(BigDecimal.valueOf(2.5));
//
//            Supplier supplier = new Supplier();
//            supplier.setSupplierName("Some Supplier");
//            supplier.setPhone("555-555-5555");
//            supplier.setWebsite("www.somewebsite.com");
//            supplier.setRepresentative("Some Representative");
//
//            Category category = entityManager.find(Category.class, 1);
//
//            Note note = new Note();
//            note.setNote("Some note about Some Product");
//
//            Price price = new Price();
//            price.setPrice(BigDecimal.valueOf(.01));
//
//            product.getSuppliers().add(supplier);
//            product.getCategories().add(category);
//            product.getNotes().add(note);
//            product.getPrices().add(price);
//
//            supplier.getProducts().add(product);
//            supplier.getPrices().add(price);
//
//            category.getProducts().add(product);
//
//            note.setProduct(product);
//
//            price.setProduct(product);
//            price.setSupplier(supplier);
//
//            entityManager.persist(product);
//            entityManager.persist(supplier);
//            entityManager.persist(category);
//            entityManager.persist(note);
//            entityManager.persist(price);

            // UPDATE TEST

            Product product = entityManager.find(Product.class, 1);
            product.setDescription("An updated description");
            product.getCategories().forEach(category -> category.getProducts().remove(product));
            product.getSuppliers().forEach(supplier -> supplier.getProducts().remove(product));
            product.getNotes().forEach(note -> {
                if (note.foreignKeyRemovalWillViolateConstraint(Product.class)) {
                    entityManager.remove(note);
                }
                else {
                    note.setProduct(null);
                    entityManager.merge(note);
                }
            });
            entityManager.merge(product);

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