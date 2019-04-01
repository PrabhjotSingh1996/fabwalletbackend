fab-wallet
This project is prototype of the fab wallet system.

This application uses maven as build tool and PostgresSql as database.

Pre-Requisits
Java 8 is installed
Port 9091 is empty , if not please change server.port property in application.properties in src/main/resources
PostgresSql is installed on the machine on port 5432(default port), if not please change details in application.properties

Follow these steps to run this project:

Clone the project git clone https://github.com/PrabhjotSingh1996/fabwalletbackend
Create a database in PostgresSql;

Build :
To build the application mvn clean install

Run :
Run the application using mvn spring-boot:run

Release :
To release a production version mvn release:clean release:prepare

Deploy :
To deploy build on nexus mvn clean deploy

Database Structure
Following is DB Script to create required tables (if not created automatically)

DROP SEQUENCE IF EXISTS hibernate_sequence;

CREATE SEQUENCE hibernate_sequence
       INCREMENT BY 1
       MINVALUE 1
       CACHE 1
       NO CYCLE;

COMMIT;

DROP TABLE IF EXISTS transaction CASCADE;

CREATE TABLE transaction
(
   transaction_id    bigint         NOT NULL,
   amount            integer        NOT NULL,
   msisdn            varchar(255)   NOT NULL,
   post_balance      integer        NOT NULL,
   previous_balance  integer        NOT NULL,
   transaction_date  varchar(255)   NOT NULL,
   transaction_type  varchar(255)   NOT NULL
);

ALTER TABLE transaction
   ADD CONSTRAINT transaction_pkey
   PRIMARY KEY (transaction_id);

COMMIT;

DROP TABLE IF EXISTS token CASCADE;

CREATE TABLE token
(
   msisdn        varchar(255)   NOT NULL,
   access_token  varchar(255),
   is_active     boolean
);

ALTER TABLE token
   ADD CONSTRAINT token_pkey
   PRIMARY KEY (msisdn);

COMMIT;

DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users
(
   msisdn     varchar(255)   NOT NULL,
   password   varchar(255)   NOT NULL,
   user_name  varchar(255)   NOT NULL
);

ALTER TABLE users
   ADD CONSTRAINT users_pkey
   PRIMARY KEY (msisdn);

COMMIT;

DROP TABLE IF EXISTS wallet CASCADE;

CREATE TABLE wallet
(
   msisdn   varchar(255)   NOT NULL,
   balance  integer
);

ALTER TABLE wallet
   ADD CONSTRAINT wallet_pkey
   PRIMARY KEY (msisdn);

COMMIT;