https://stackoverflow.com/questions/55154372/com-mysql-jdbc-driver-not-found-in-spark2-scala
Cannot generate a jdbc connection to mysql from Spark2:

val query_customer = "select * from customer"

val df_customer = sqlContext.format("jdbc").
  option("url", "jdbc:mysql://localhost:3306/ccfd").
  option("driver", "com.mysql.jdbc.Driver").
  option("useUnicode", "true").
  option("continueBatchOnError","true").
  option("useSSL", "false").
  option("user", "root").
  option("password", "n0va2020").
  option("dbtable",query_customer).
  load()



val sql="""select * from customer"""
val df_customer = spark.read
  .format("jdbc")
  .option("url", "jdbc:mysql://localhost:3306/ccfd")
  .option("driver", "com.mysql.jdbc.Driver").
  .option("dbtable",  s"( $sql ) t")
  .option("user", "root")
  .option("password", "n0va2020")
  .load()

