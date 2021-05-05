User table:
CREATE TABLE public.shop_user (
	user_id serial NOT NULL,
	user_name varchar(50) NULL,
	user_hashedpass varchar(500) NULL,
	first_name varchar(50) NULL,
	last_name varchar(50) NULL,
	user_type varchar(50) NULL,
	user_salt varchar(500) NULL,
	CONSTRAINT shop_user_pkey PRIMARY KEY (user_id)
);


Payment table:
CREATE TABLE public.shop_payments (
	payment_id serial NOT NULL,
	item_id int4 NULL,
	payment_status varchar(30) NULL,
	payment_amount float8 NULL,
	customer_id int4 NULL,
	remaining_payment float8 NULL,
	remaining_terms int4 NULL,
	CONSTRAINT shop_payments_pk PRIMARY KEY (payment_id)
);


-- public.shop_payments foreign keys

ALTER TABLE public.shop_payments ADD CONSTRAINT shop_payments_fk FOREIGN KEY (customer_id) REFERENCES shop_user(user_id);
ALTER TABLE public.shop_payments ADD CONSTRAINT shop_payments_fk_1 FOREIGN KEY (item_id) REFERENCES shop_item(item_id);


Offer table:
CREATE TABLE public.shop_offer (
	offer_id int4 NOT NULL DEFAULT nextval('shop_customer_offer_offer_id_seq'::regclass),
	customer_id int8 NOT NULL,
	offer_amount float8 NOT NULL,
	offer_status varchar(32) NULL,
	item_id int4 NULL,
	CONSTRAINT shop_offer_pk PRIMARY KEY (offer_id)
);


-- public.shop_offer foreign keys

ALTER TABLE public.shop_offer ADD CONSTRAINT shop_offer_fk FOREIGN KEY (customer_id) REFERENCES shop_user(user_id);


Item table:
CREATE TABLE public.shop_item (
	item_id serial NOT NULL,
	item_name varchar(30) NULL,
	item_description varchar NULL,
	item_status varchar(30) NULL,
	item_minimum_price float8 NULL,
	item_owner_id int4 NULL,
	CONSTRAINT shop_item_pk PRIMARY KEY (item_id)
);


-- public.shop_item foreign keys

ALTER TABLE public.shop_item ADD CONSTRAINT shop_item_fk FOREIGN KEY (item_owner_id) REFERENCES shop_user(user_id);


