package com.zg.sparksql.exer

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @Project_name test
 * @Package_name com.zg.sparksql.exer
 * @author zhuguang
 * @date 2021-01-04-19:53
 */
object SparkSQL05_UDF {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    import spark.implicits._
    val rdd = spark.sparkContext.makeRDD(
      List(
        (1, "zzy", 25),
        (2, "yyz", 15),
        (3, "zyz", 25)
      )
    )
    val df: DataFrame = rdd.toDF("id", "name", "age")
    df.createOrReplaceTempView("user")

    spark.udf.register("prefixName", (name: String) => {
      "Name :" + name
    })
    spark.udf.register("ageplus", (age: Int) => {
      age+1
    })

    spark.sql("select id, prefixName(name), age from user").show()
    spark.sql("select id, name, ageplus(age) from user").show()


    spark.stop()


  }

}
