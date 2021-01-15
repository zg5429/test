package com.zg.sparksql.exer

import org.apache.spark.SparkConf
import org.apache.spark.sql._
import org.apache.spark.sql.expressions.Aggregator

/**
 * @Project_name test
 * @Package_name com.zg.sparksql.exer
 * @author zhuguang
 * @date 2021-01-04-19:53
 */
object SparkSQL07_Hive {
  def main(args: Array[String]): Unit = {
//
//    val spark: SparkSession = SparkSession
//      .builder().config("spark.sql.warehouse.dir", "hdfs://hadoop102:8020/user/hive/warehouse")
////            .builder().config("hive.metastore.uris", "thrift://hadoop102:9083")
//      .enableHiveSupport()
//      .master("local[*]")
//      .appName("sql")
//      .getOrCreate()
//
//    spark.sql("show tables").show()
//    spark.stop()

    // TODO 连接外置的Hive
    // 创建环境

    // 连接Hive，必须启用Hive的支持
    val spark: SparkSession = SparkSession
//      .builder().config("spark.sql.warehouse.dir", "hdfs://hadoop102:8020/user/hive/warehouse")
      .builder().config("hive.metastore.uris", "thrift://hadoop102:9083")
      .enableHiveSupport()
      .master("local[*]")
      .appName("sql")
      .getOrCreate()

    import spark.implicits._

    spark.sql("show tables").show


    spark.stop()


  }


}
