package com.zg.sparksql.exer

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
 * @Project_name test
 * @Package_name com.zg.sparksql.exer
 * @author zhuguang
 * @date 2021-01-04-18:59
 */
object SparkSQL04_TRansform {
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

    //    rdd->dataframe
    val df: DataFrame = rdd.toDF("id", "name", "age")
    //    df.show()

    // dataframe ->dataset
    val ds: Dataset[Person] = df.as[Person]
    //    ds.show()

    //    rdd->dataset

    val ds1: Dataset[Person] = rdd.map {
      case (id, name, age) => {
        Person(id, name, age)
      }
    }.toDS()
    //    ds1.show()


    //    dataset ->datafraome
    val df1 = ds.toDF()

    //    dataset->RDD
    val rdd2: RDD[Person] = ds.rdd

    spark.stop()


  }

  case class Person(var id: Int, var name: String, var age: Int)

}
