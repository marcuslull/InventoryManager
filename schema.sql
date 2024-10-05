
-- DROP TABLE IF EXISTS public.products_categories CASCADE;
-- DROP TABLE IF EXISTS public.products_suppliers CASCADE;
-- DROP TABLE IF EXISTS public.products CASCADE;
-- DROP TABLE IF EXISTS public.categories CASCADE;
-- DROP TABLE IF EXISTS public.suppliers CASCADE;
-- DROP TABLE IF EXISTS public.price CASCADE;
-- DROP TABLE IF EXISTS public.notes CASCADE;


CREATE TABLE IF NOT EXISTS public.products
(
    sku character varying(20) NOT NULL,
    product_name character varying(50) NOT NULL,
    quantity integer NOT NULL DEFAULT 0,
    threshold integer CHECK (threshold >= 0),
    description text,
    weight numeric(6, 2),
    PRIMARY KEY ("sku")
);

CREATE TABLE IF NOT EXISTS public.categories
(
    category_id serial NOT NULL,
    category_name character varying(50) NOT NULL,
    PRIMARY KEY (category_id)
);

CREATE TABLE IF NOT EXISTS public.suppliers
(
    supplier_id serial NOT NULL,
    supplier_name character varying(50) NOT NULL,
    website character varying(200) NOT NULL,
    representative character varying(50),
    phone character varying(15) NOT NULL,
    PRIMARY KEY (supplier_id)
);

CREATE TABLE IF NOT EXISTS public.price
(
    price_id serial NOT NULL,
    price numeric(7, 2) NOT NULL,
    product_id character varying(20) NOT NULL,
    supplier_id integer NOT NULL,
    PRIMARY KEY (price_id)
);

CREATE TABLE IF NOT EXISTS public.notes
(
    note_id serial NOT NULL,
    note text NOT NULL,
    product_id character varying(20),
    category_id integer,
    supplier_id integer,
    PRIMARY KEY (note_id)
);

CREATE TABLE IF NOT EXISTS public.products_categories
(
    products_sku character varying(20) NOT NULL,
    categories_category_id serial NOT NULL
);

CREATE TABLE IF NOT EXISTS public.products_suppliers
(
    products_sku character varying(20) NOT NULL,
    suppliers_supplier_id serial NOT NULL
);

ALTER TABLE IF EXISTS public.price
    ADD FOREIGN KEY (product_id)
    REFERENCES public.products (sku) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.price
    ADD FOREIGN KEY (supplier_id)
    REFERENCES public.suppliers (supplier_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.notes
    ADD FOREIGN KEY (product_id)
    REFERENCES public.products (sku) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.notes
    ADD FOREIGN KEY (category_id)
    REFERENCES public.categories (category_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.notes
    ADD FOREIGN KEY (supplier_id)
    REFERENCES public.suppliers (supplier_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.products_categories
    ADD FOREIGN KEY (products_sku)
    REFERENCES public.products (sku) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.products_categories
    ADD FOREIGN KEY (categories_category_id)
    REFERENCES public.categories (category_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.products_suppliers
    ADD FOREIGN KEY (products_sku)
    REFERENCES public.products (sku) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.products_suppliers
    ADD FOREIGN KEY (suppliers_supplier_id)
    REFERENCES public.suppliers (supplier_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;