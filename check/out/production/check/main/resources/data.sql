CREATE TABLE public.product (
                                id bigserial NOT NULL,
                                description varchar(50) NOT NULL,
                                price decimal NOT NULL,
                                quantity_in_stock int NULL,
                                wholesale_product boolean NULL,
                                CONSTRAINT product_pk PRIMARY KEY (id)
);
CREATE TABLE public.discount_card (
                                      id bigserial NOT NULL,
                                      "number" int NOT NULL,
                                      amount smallint NOT NULL,
                                      CONSTRAINT discount_card_pk PRIMARY KEY (id)
);
SELECT * FROM discount_card;
SELECT * FROM product;