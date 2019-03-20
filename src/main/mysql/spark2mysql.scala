spark2-shell --driver-class-path mysql-connector-java-5.1.15.jar --jars /opt/cloudera/parcels/SPARK2/lib/spark2/jars/mysql-connector-java-5.1.15.jar

import org.apache.spark.sql.SQLContext
val sqlcontext = new org.apache.spark.sql.SQLContext(sc)
val q="""select cc_num, first, last from customer"""
val df = spark.read.format("jdbc").option("url", "jdbc:mysql://drivernode:3306/ccfd?useSSL=false").option("dbtable",  s"( $q ) t").option("user", "jdoe").option("password", "Password1").load()
scala> df.show
+----------------+--------+---------+
|          cc_num|   first|     last|
+----------------+--------+---------+
|3526015186182660|    Carl|    Gomez|
|4170242670039985| Rebecca| Trujillo|
|   4006862159277|  Cheryl|     Rice|
|3593533875650654|   Cindy|      Ray|
|5421885738881170|  Jeremy|   Torres|
|4048725581466255| Theresa|     Cole|
|   4092259246729| Krystal|   Harper|
|5417459284978545|   Angel|    Smith|
|6011779269963768|Jennifer|   Harvey|
|  30405027360515|  Monica|    Brown|
|6546851067101927| Brianna|   Mercer|
|   4483018920250|   Helen|   Strong|
| 379684560931689|   Cindy|   Martin|
|4361646620879135|Kimberly|   Hudson|
|  30021746099829|   James|Rodriguez|
|5590294502817012|Kathleen|Maldonado|
|    676165681542|    John|   Garcia|
|6011537727192499|   James|   Walker|
|  30157941709315| Maurice|    Simon|
| 180094108369013|    John|  Holland|
+----------------+--------+---------+
only showing top 20 rows


NOTE:
1. third party jar file is saved in /opt/cloudera/parcels/SPARK2/lib/spark2/jars
2. MySQL driver jar file should be same version for --driver-class-path and --jars
3. For case of mysql exists in all worker nodes, the user should be granted privileges as 'jdoe'@'%'
4. For case of mysql exists in only the driver node, the jdbc connection should indicate the drive node's hostname as jdbc:mysql://drivernode:3306
×
Drag and Drop
The image will be downloaded by Fatkun
