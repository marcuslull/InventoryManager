BEGIN;



DROP TRIGGER IF EXISTS update_categories_updated_at ON categories;

DROP TRIGGER IF EXISTS update_notes_updated_at ON notes;

DROP TRIGGER IF EXISTS update_price_updated_at ON price;

DROP TRIGGER IF EXISTS update_products_updated_at ON products;

DROP TRIGGER IF EXISTS update_suppliers_updated_at ON suppliers;



DROP FUNCTION IF EXISTS update_updated_at_column();



ALTER TABLE IF EXISTS public.notes DROP CONSTRAINT IF EXISTS notes_category_id_fkey;

ALTER TABLE IF EXISTS public.notes DROP CONSTRAINT IF EXISTS notes_product_id_fkey;

ALTER TABLE IF EXISTS public.notes DROP CONSTRAINT IF EXISTS notes_supplier_id_fkey;

ALTER TABLE IF EXISTS public.price DROP CONSTRAINT IF EXISTS price_product_id_fkey;

ALTER TABLE IF EXISTS public.price DROP CONSTRAINT IF EXISTS price_supplier_id_fkey;

ALTER TABLE IF EXISTS public.products_categories DROP CONSTRAINT IF EXISTS products_categories_categories_category_id_fkey;

ALTER TABLE IF EXISTS public.products_categories DROP CONSTRAINT IF EXISTS products_categories_products_product_id_fkey;

ALTER TABLE IF EXISTS public.products_suppliers DROP CONSTRAINT IF EXISTS products_suppliers_products_product_id_fkey;

ALTER TABLE IF EXISTS public.products_suppliers DROP CONSTRAINT IF EXISTS products_suppliers_suppliers_supplier_id_fkey;



DROP TABLE IF EXISTS public.products_categories;

DROP TABLE IF EXISTS public.products_suppliers;

DROP TABLE IF EXISTS public.price;

DROP TABLE IF EXISTS public.notes;

DROP TABLE IF EXISTS public.suppliers;

DROP TABLE IF EXISTS public.categories;

DROP TABLE IF EXISTS public.products;



-- DROP SEQUENCE IF EXISTS categories_category_id_seq;

-- DROP SEQUENCE IF EXISTS notes_note_id_seq;

-- DROP SEQUENCE IF EXISTS price_price_id_seq;

-- DROP SEQUENCE IF EXISTS products_product_id_seq;

-- DROP SEQUENCE IF EXISTS suppliers_supplier_id_seq;



CREATE TABLE IF NOT EXISTS public.categories
(
    category_id serial NOT NULL,
    category_name character varying(50) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    is_deleted boolean DEFAULT false,
    version integer DEFAULT 0,
    CONSTRAINT categories_pkey PRIMARY KEY (category_id)
);

CREATE TABLE IF NOT EXISTS public.notes
(
    note_id serial NOT NULL,
    note text NOT NULL,
    category_id integer,
    supplier_id integer,
    product_id integer,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    is_deleted boolean DEFAULT false,
    version integer DEFAULT 0,
    CONSTRAINT notes_pkey PRIMARY KEY (note_id),
	CONSTRAINT notes_at_least_one_id_required CHECK (category_id IS NOT NULL OR supplier_id IS NOT NULL OR product_id IS NOT NULL)
);

CREATE TABLE IF NOT EXISTS public.price
(
    price_id serial NOT NULL,
    price numeric(7, 2) NOT NULL,
    supplier_id integer NOT NULL,
    product_id integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    is_deleted boolean DEFAULT false,
    version integer DEFAULT 0,
    CONSTRAINT price_pkey PRIMARY KEY (price_id)
);

CREATE TABLE IF NOT EXISTS public.products
(
    product_id serial NOT NULL,
	sku character varying(20) NOT NULL,
    product_name character varying(50) NOT NULL,
    quantity integer NOT NULL DEFAULT 0,
    threshold integer,
    description text,
    weight numeric(6, 2),    
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    is_deleted boolean DEFAULT false,
    version integer DEFAULT 0,
    CONSTRAINT products_pkey PRIMARY KEY (product_id),
    CONSTRAINT products_sku_key UNIQUE (sku),
	CONSTRAINT products_positive_threshold CHECK (threshold > 0)
);

CREATE TABLE IF NOT EXISTS public.suppliers
(
    supplier_id serial NOT NULL,
    supplier_name character varying(50) NOT NULL,
    website character varying(200) NOT NULL,
    representative character varying(50),
    phone character varying(15) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    is_deleted boolean DEFAULT false,
    version integer DEFAULT 0,
    CONSTRAINT suppliers_pkey PRIMARY KEY (supplier_id)
);

CREATE TABLE IF NOT EXISTS public.products_categories
(
    categories_category_id integer NOT NULL,
    products_product_id integer NOT NULL,
	CONSTRAINT products_categories_pkey PRIMARY KEY (categories_category_id, products_product_id)
);

CREATE TABLE IF NOT EXISTS public.products_suppliers
(
    suppliers_supplier_id integer NOT NULL,
    products_product_id integer NOT NULL,
	CONSTRAINT products_suppliers_pkey PRIMARY KEY (suppliers_supplier_id, products_product_id)
);



ALTER TABLE IF EXISTS public.notes
    ADD CONSTRAINT notes_category_id_fkey FOREIGN KEY (category_id)
    REFERENCES public.categories (category_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.notes
    ADD CONSTRAINT notes_product_id_fkey FOREIGN KEY (product_id)
    REFERENCES public.products (product_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.notes
    ADD CONSTRAINT notes_supplier_id_fkey FOREIGN KEY (supplier_id)
    REFERENCES public.suppliers (supplier_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.price
    ADD CONSTRAINT price_product_id_fkey FOREIGN KEY (product_id)
    REFERENCES public.products (product_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.price
    ADD CONSTRAINT price_supplier_id_fkey FOREIGN KEY (supplier_id)
    REFERENCES public.suppliers (supplier_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.products_categories
    ADD CONSTRAINT products_categories_categories_category_id_fkey FOREIGN KEY (categories_category_id)
    REFERENCES public.categories (category_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.products_categories
    ADD CONSTRAINT products_categories_products_product_id_fkey FOREIGN KEY (products_product_id)
    REFERENCES public.products (product_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.products_suppliers
    ADD CONSTRAINT products_suppliers_products_product_id_fkey FOREIGN KEY (products_product_id)
    REFERENCES public.products (product_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.products_suppliers
    ADD CONSTRAINT products_suppliers_suppliers_supplier_id_fkey FOREIGN KEY (suppliers_supplier_id)
    REFERENCES public.suppliers (supplier_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;



CREATE OR REPLACE FUNCTION update_updated_at_column() 
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW; 
END;
$$ language 'plpgsql';



CREATE TRIGGER update_categories_updated_at
BEFORE UPDATE ON categories
FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_column();

CREATE TRIGGER update_notes_updated_at
BEFORE UPDATE ON notes
FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_column();

CREATE TRIGGER update_price_updated_at
BEFORE UPDATE ON price
FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_column();

CREATE TRIGGER update_products_updated_at
BEFORE UPDATE ON products
FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_column();

CREATE TRIGGER update_suppliers_updated_at
BEFORE UPDATE ON suppliers
FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_column();



ALTER SEQUENCE categories_category_id_seq RESTART WITH 1;
ALTER SEQUENCE notes_note_id_seq RESTART WITH 1;
ALTER SEQUENCE price_price_id_seq RESTART WITH 1;
ALTER SEQUENCE products_product_id_seq RESTART WITH 1;
ALTER SEQUENCE suppliers_supplier_id_seq RESTART WITH 1;


END;