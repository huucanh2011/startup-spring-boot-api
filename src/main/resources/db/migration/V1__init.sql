-- create extension uuid
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
-- create table users
CREATE TABLE IF NOT EXISTS users (
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  name VARCHAR(40) NOT NULL,
  email VARCHAR(60) NOT NULL,
  password VARCHAR(60) NOT NULL,
  avatar_path VARCHAR(255) DEFAULT NULL,
  address VARCHAR(100) DEFAULT NULL,
  phone_number VARCHAR(11) DEFAULT NULL,
  delivery_address VARCHAR(100) DEFAULT NULL,
  role VARCHAR(5) NOT NULL,
  status VARCHAR(10) NOT NULL,
  entry_date TIMESTAMPTZ NOT NULL,
  update_date TIMESTAMPTZ DEFAULT NULL,
  PRIMARY KEY (id)
);
-- create table users
CREATE TABLE IF NOT EXISTS password_resets (
  email VARCHAR(60) NOT NULL,
  token VARCHAR(60) NOT NULL,
  entry_date TIMESTAMPTZ NOT NULL
);
-- create table categories
CREATE TABLE IF NOT EXISTS categories (
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  name VARCHAR(40) NOT NULL,
  slug VARCHAR(40) NOT NULL,
  entry_date TIMESTAMPTZ NOT NULL,
  update_date TIMESTAMPTZ DEFAULT NULL,
  PRIMARY KEY (id)
);
-- create table m_brands
CREATE TABLE m_brands (
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  name VARCHAR(40) NOT NULL,
  slug VARCHAR(40) NOT NULL,
  PRIMARY KEY (id)
);
-- create table m_sizes
CREATE TABLE m_sizes (
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  name VARCHAR(3) NOT NULL,
  value VARCHAR(10) NOT NULL,
  is_shirt BOOLEAN NOT NULL,
  PRIMARY KEY (id)
);
-- create table slides
CREATE TABLE slides (
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  title VARCHAR(40) DEFAULT NULL,
  link VARCHAR(255) DEFAULT NULL,
  image VARCHAR(255) NOT NULL,
  is_active BOOLEAN NOT NULL,
  entry_date TIMESTAMPTZ NOT NULL,
  update_date TIMESTAMPTZ DEFAULT NULL,
  PRIMARY KEY (id)
);
-- create table products
CREATE TABLE products (
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  code VARCHAR(10) NOT NULL,
  name VARCHAR(100) NOT NULL,
  slug VARCHAR(100) NOT NULL,
  gallery JSON,
  image VARCHAR(255) NOT NULL,
  feature VARCHAR(255) NOT NULL,
  description text NOT NULL,
  price NUMERIC(10, 1) NOT NULL,
  stocks SMALLINT NOT NULL CHECK (stocks >= 0),
  is_active BOOLEAN NOT NULL,
  brand_id uuid,
  size_id uuid NOT NULL,
  category_id uuid NOT NULL,
  entry_date TIMESTAMPTZ NOT NULL,
  update_date TIMESTAMPTZ DEFAULT NULL,
  PRIMARY KEY (id)
);
-- create table ratings
CREATE TABLE ratings (
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  scores SMALLINT NOT NULL,
  content VARCHAR(255) DEFAULT NULL::VARCHAR,
  is_active BOOLEAN NOT NULL,
  product_id uuid NOT NULL,
  user_id uuid NOT NULL,
  entry_date TIMESTAMPTZ NOT NULL,
  update_date TIMESTAMPTZ DEFAULT NULL,
  PRIMARY KEY (id)
);
-- create table order_status
CREATE TABLE order_status (
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  name VARCHAR(40) NOT NULL,
  slug VARCHAR(40) NOT NULL,
  entry_date TIMESTAMPTZ NOT NULL,
  update_date TIMESTAMPTZ DEFAULT NULL,
  PRIMARY KEY (id)
);
-- create table orders
CREATE TABLE orders (
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  receiver_name VARCHAR(40) NOT NULL,
  receiver_email VARCHAR(60) NOT NULL,
  receiver_phone_number VARCHAR(11) NOT NULL,
  receiver_address VARCHAR(100) DEFAULT NULL::VARCHAR,
  total_amount NUMERIC(11, 1) NOT NULL,
  user_id uuid NOT NULL,
  status_id uuid NOT NULL,
  reason_cancel VARCHAR(255) DEFAULT NULL::VARCHAR,
  entry_date TIMESTAMPTZ NOT NULL,
  update_date TIMESTAMPTZ DEFAULT NULL,
  PRIMARY KEY (id)
);
-- create table order_details
CREATE TABLE order_details (
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  quantity SMALLINT NOT NULL,
  order_id uuid NOT NULL,
  product_id uuid NOT NULL,
  entry_date TIMESTAMPTZ NOT NULL,
  update_date TIMESTAMPTZ DEFAULT NULL,
  PRIMARY KEY (id)
);
-- create table order_histories
CREATE TABLE order_histories (
  id uuid DEFAULT uuid_generate_v4() NOT NULL,
  receiver_name VARCHAR(40) NOT NULL,
  receiver_email VARCHAR(60) NOT NULL,
  receiver_phone_number VARCHAR(11) NOT NULL,
  receiver_address VARCHAR(100) DEFAULT NULL::VARCHAR,
  total_amount NUMERIC(11, 1) NOT NULL,
  status_id uuid NOT NULL,
  user_id uuid NOT NULL,
  entry_date TIMESTAMPTZ NOT NULL,
  PRIMARY KEY (id)
);