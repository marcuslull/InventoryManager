
-- Inserting test data for categories
INSERT INTO public.categories (category_name)
VALUES ('ELECTRONICS'),
       ('CLOTHING'),
       ('BOOKS'),
       ('HOME_GOODS'),
       ('SPORTS_AND_OUTDOORS'),
       ('BEAUTY_AND_PERSONAL_CARE'),
       ('HEALTH_AND_WELLNESS'),
       ('GROCERY'),
       ('AUTOMOTIVE'),
       ('TOYS_AND_GAMES');



-- Inserting test data for products
INSERT INTO public.products (sku, product_name, quantity, threshold, description, weight)
VALUES ('SKU123', 'Product 1', 100, 10, 'This is product 1', 1.25),
       ('SKU124', 'Product 2', 50, 5, 'This is product 2', 2.50),
       ('SKU125', 'Product 3', 200, 20, 'This is product 3', 0.75);



-- Inserting test data for suppliers
INSERT INTO public.suppliers (supplier_name, website, representative, phone)
VALUES ('Supplier 1', 'https://supplier1.example.com', 'Rep 1', '123-456-7890'),
       ('Supplier 2', 'https://supplier2.example.com', 'Rep 2', '234-567-8901'),
       ('Supplier 3', 'https://supplier3.example.com', 'Rep 3', '345-678-9012');



-- Inserting test data for price
INSERT INTO public.price (price, product_id, supplier_id)
VALUES (9.99, 1, 1),
       (19.99, 2, 2),
       (29.99, 3, 3),
	   (11.99, 1, 2),
       (12.99, 1, 3),
       (18.99, 2, 1),
       (17.99, 2, 3),
       (22.99, 3, 1),
       (21.99, 3, 2),
       (10.50, 1, 1),
       (15.75, 2, 2),
       (20.25, 3, 3);



-- Inserting test data for notes
INSERT INTO public.notes (note, product_id, category_id, supplier_id)
VALUES ('Note 1 for Product 1', 1, 1, 1),
       ('Note 2 for Product 2', 2, 2, 2),
       ('Note 3 for Product 3', 3, 3, 3),
	   ('Additional Note 1 for Product 1 from Supplier 1', 1, 1, 1),
       ('Additional Note 2 for Product 2 from Supplier 2', 2, 2, 2),
       ('Additional Note 3 for Product 3 from Supplier 3', 3, 3, 3),
       ('General Note 1 for Category 1 from Supplier 1', NULL, 1, 1),
       ('General Note 2 for Category 2 from Supplier 2', NULL, 2, 2),
       ('General Note 3 for Category 3 from Supplier 3', NULL, 3, 3),
       ('Product Note for SKU123 in Category 2', 1, 2, NULL),
       ('Product Note for SKU124 in Category 3', 2, 3, NULL),
       ('Product Note for SKU125 in Category 1', 3, 1, NULL),
       ('This note should be deleted', 1, NULL, NULL);



-- Inserting test data for products_categories
INSERT INTO public.products_categories (products_product_id, categories_category_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
	   (1, 2),
       (2, 1);



-- Inserting test data for products_suppliers
INSERT INTO public.products_suppliers (products_product_id, suppliers_supplier_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
	   (1, 2),
       (2, 1),
       (3, 2);
