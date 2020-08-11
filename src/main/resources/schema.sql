CREATE TABLE Account_type(
    ID SERIAL,
    Account_type text NOT NULL,
	primary key (ID)
);
create table account (
id SERIAL,
account_name text,
account_type integer,
market_cap integer, 
primary key (id),
unique (account_name)
);
create table customer(
id SERIAL ,
customer_name text,
customer_num integer,

primary key (id)
);
create table ACCOUNT_CUSTOMER(ACCOUNT_ID integer,CUSTOMER_ID integer,primary key(ACCOUNT_ID,CUSTOMER_ID));