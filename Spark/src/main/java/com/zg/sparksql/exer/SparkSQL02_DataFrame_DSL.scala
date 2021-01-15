package com.zg.sparksql.exer

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @Project_name test
 * @Package_name com.zg.sparksql.exer
 * @author zhuguang
 * @date 2021-01-04-18:59
 */
object SparkSQL02_DataFrame_DSL {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    import spark.implicits._
    val df: DataFrame = spark.read.json("data/user.json")
    //    df.show()

    df.select("id","username","age").show()

    df.select($"id",$"username",$"age").show()

    df.select('id,'username,'age).show()


    spark.stop()







  }

}
