package com.zg.sparksql.function

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @Project_name test
 * @Package_name com.zg.sparksql.function
 * @author zhuguang
 * @date 2021-01-05-20:51
 */
object Spark_UDF {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("UDF")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    val df: DataFrame = spark.read.json("data/user.json")
    df.createOrReplaceTempView("user")

    spark.udf.register("addName", (x: String) => "Name: " + x)

    spark.sql("select addName(username) ,age from user").show()
    spark.stop()

  }
}
