Files under this folder is for mysql.

Note to mysql:
There seems to be a bug in mysql as per https://bugs.mysql.com/bug.php?id=72220
When running:
mysql> LOAD DATA LOCAL INFILE '/home/rxie/data/customer.csv'  INTO TABLE customer  FIELDS TERMINATED BY ','  ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 LINES;
Error:
ERROR 1148 (42000): The used command is not allowed with this MySQL version

Work around:

Start mysql with option:
mysql -u root -p  --local-infile=1
and run below to make sure infile loading is enabled:
SET GLOBAL local_infile = 1;

Note to create mysql jdbc connection:
Notebook Connect2mysqlWithExternalJAR.ipynb shows how to create a jdbc mysql connection in Spark Scala, be aware the Scala kernel is created based on Toree
%AddJar http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.15/mysql-connector-java-5.1.15.jar


