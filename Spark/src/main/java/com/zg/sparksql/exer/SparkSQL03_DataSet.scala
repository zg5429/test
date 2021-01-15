package com.zg.sparksql.exer

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
 * @Project_name test
 * @Package_name com.zg.sparksql.exer
 * @author zhuguang
 * @date 2021-01-04-18:59
 */
object SparkSQL03_DataSet {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    import spark.implicits._
    val list = List(
      Person("yyz", 25), Person("zzy", 30), Person("zyz", 35)
    )
    val ds: Dataset[Person] = list.toDS()
    ds.show()

    spark.stop()


  }

  case class Person(var name: String, var id: Int)

}
