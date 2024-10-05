-- Inserting test data for products
INSERT INTO public.products (sku, product_name, quantity, threshold, description, weight)
VALUES ('SKU123', 'Product 1', 100, 10, 'This is product 1', 1.25),
       ('SKU124', 'Product 2', 50, 5, 'This is product 2', 2.50),
       ('SKU125', 'Product 3', 200, 20, 'This is product 3', 0.75);

-- Inserting test data for categories
INSERT INTO public.categories (category_name)
VALUES ('Category 1'),
       ('Category 2'),
       ('Category 3');

-- Inserting test data for suppliers
INSERT INTO public.suppliers (supplier_name, website, representative, phone)
VALUES ('Supplier 1', 'https://supplier1.example.com', 'Rep 1', '123-456-7890'),
       ('Supplier 2', 'https://supplier2.example.com', 'Rep 2', '234-567-8901'),
       ('Supplier 3', 'https://supplier3.example.com', 'Rep 3', '345-678-9012');

-- Inserting test data for price
INSERT INTO public.price (price, product_id, supplier_id)
VALUES (9.99, 'SKU123', 1),
       (19.99, 'SKU124', 2),
       (29.99, 'SKU125', 3);

-- More price entries for each product from different suppliers
INSERT INTO public.price (price, product_id, supplier_id)
VALUES (11.99, 'SKU123', 2),
       (12.99, 'SKU123', 3),
       (18.99, 'SKU124', 1),
       (17.99, 'SKU124', 3),
       (22.99, 'SKU125', 1),
       (21.99, 'SKU125', 2),
       (10.50, 'SKU123', 1),
       (15.75, 'SKU124', 2),
       (20.25, 'SKU125', 3);

-- Inserting test data for notes
INSERT INTO public.notes (note, product_id, category_id, supplier_id)
VALUES ('Note 1 for Product 1', 'SKU123', 1, 1),
       ('Note 2 for Product 2', 'SKU124', 2, 2),
       ('Note 3 for Product 3', 'SKU125', 3, 3);

-- Inserting more test data for notes
INSERT INTO public.notes (note, product_id, category_id, supplier_id)
VALUES ('Additional Note 1 for Product 1 from Supplier 1', 'SKU123', 1, 1),
       ('Additional Note 2 for Product 2 from Supplier 2', 'SKU124', 2, 2),
       ('Additional Note 3 for Product 3 from Supplier 3', 'SKU125', 3, 3),
       ('General Note 1 for Category 1 from Supplier 1', NULL, 1, 1),
       ('General Note 2 for Category 2 from Supplier 2', NULL, 2, 2),
       ('General Note 3 for Category 3 from Supplier 3', NULL, 3, 3),
       ('Product Note for SKU123 in Category 2', 'SKU123', 2, NULL),
       ('Product Note for SKU124 in Category 3', 'SKU124', 3, NULL),
       ('Product Note for SKU125 in Category 1', 'SKU125', 1, NULL);

-- Inserting test data for products_categories
INSERT INTO public.products_categories (products_sku, categories_category_id)
VALUES ('SKU123', 1),
       ('SKU124', 2),
       ('SKU125', 3);

INSERT INTO public.products_categories (products_sku, categories_category_id)
VALUES ('SKU123', 2),
       ('SKU124', 1),
       ('SKU124', 3);

-- Inserting test data for products_suppliers
INSERT INTO public.products_suppliers (products_sku, suppliers_supplier_id)
VALUES ('SKU123', 1),
       ('SKU124', 2),
       ('SKU125', 3);

-- Adding more supplier for each product in products_suppliers table
INSERT INTO public.products_suppliers (products_sku, suppliers_supplier_id)
VALUES ('SKU123', 2),
       ('SKU124', 1),
       ('SKU125', 2);