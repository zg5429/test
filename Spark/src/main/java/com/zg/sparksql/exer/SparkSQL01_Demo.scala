package com.zg.sparksql.exer

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

/**
 * @Project_name test
 * @Package_name com.zg.sparksql.exer
 * @author zhuguang
 * @date 2021-01-04-15:10
 */
object SparkSQL01_Demo {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    import spark.implicits._
    val df: DataFrame = spark.read.json("data/user.json")



    //SQL风格语法
    df.createOrReplaceTempView("user")
    //    spark.sql("select username,age from user").show()

    //DSL风格语法
    //    df.select("username", "age").show()

    //*****RDD=>DataFrame=>DataSet***** 互相转换
    //rdd
    val rdd1: RDD[(Int, String, Int)] = spark.sparkContext.makeRDD(
      List((1, "zhangsan", 30), (2, "lisi", 28), (3, "wangwu", 20))
    )

    //DataFrame
    val df1: DataFrame = rdd1.toDF("id", "name", "age")
    //    df1.show()

    //DateSet
    val ds1: Dataset[User] = df1.as[User]
//    ds1.show()

    //*****DataSet=>DataFrame=>RDD*****
    val df2: DataFrame = ds1.toDF()
    val rdd2: RDD[Row] = df2.rdd
    rdd2.foreach(a=>println(a.getInt(0)))


    //*****RDD=>DataSet*****
    rdd1.map{
      case (id,name,age)=>{
        User(id,name,age)
      }
    }.toDS()

    //*****DataSet=>=>RDD*****
    ds1.rdd




    spark.stop()

  }

  case class User(id: Int, name: String, age: Int)

}
