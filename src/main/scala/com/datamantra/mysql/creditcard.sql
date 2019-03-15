CREATE database IF NOT EXISTS ccfd;

USE creditcard;

CREATE TABLE IF NOT EXISTS customer (
  cc_num varchar(20),
  first varchar(20),
  last varchar(20),
  gender varchar(2),
  street varchar(50),
  city varchar(20),
  state varchar(20),
  zip varchar(5),
  lat double,
  lng double,
  job varchar(20),
  dob timestamp,
  PRIMARY KEY(cc_num)
);


LOAD DATA LOCAL INFILE '/home/rxie/data/customer.csv' 
INTO TABLE customer 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

CREATE TABLE IF NOT EXISTS transaction (
  cc_num varchar(20),
  trans_time timestamp,
  trans_num varchar(50),
  category varchar(20),
  merchant varchar(20),
  amt double,
  merch_lat double,
  merch_long double,
  distance double,
  age int,
  is_fraud boolean
);

awk -F\, '{print $1$2}' /home/rxie/data/transactions.csv| sort -n| uniq -c| grep -v "^\ *1"
wc -l /home/rxie/data/transactions.csv
13623


CREATE TABLE IF NOT EXISTS fraud_transaction (
  cc_num varchar(20),
  trans_time timestamp,
  trans_num varchar(50),
  category varchar(20),
  merchant varchar(20),
  amt double,
  merch_lat double,
  merch_long double,
  distance double,
  age int,
  is_fraud boolean
);

CREATE TABLE IF NOT EXISTS non_fraud_transaction (
  cc_num varchar(20),
  trans_time timestamp,
  trans_num varchar(50),
  category varchar(20),
  merchant varchar(20),
  amt double,
  merch_lat double,
  merch_long double,
  distance double,
  age int,
  is_fraud boolean
);

LOAD DATA LOCAL INFILE '/home/rxie/data/transactions.csv' 
INTO TABLE transaction 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

CREATE TABLE IF NOT EXISTS non_fraud_transaction (
  cc_num varchar(20),
  trans_time timestamp,
  trans_num varchar(50),
  category varchar(20),
  merchant varchar(20),
  amt double,
  merch_lat double,
  merch_long double,
  distance double,
  age int,
  is_fraud boolean,
  PRIMARY KEY(cc_num, trans_time)
);


CREATE TABLE IF NOT EXISTS kafka_offset (
  partition int,
  offset bigint,
  PRIMARY KEY(partition)
);


mysql> show tables;
+-----------------------+
| Tables_in_ccfd        |
+-----------------------+
| customer              |
| fraud_transaction     |
| kafka_offset          |
| non_fraud_transaction |
| transaction           |
+-----------------------+
5 rows in set (0.00 sec)
