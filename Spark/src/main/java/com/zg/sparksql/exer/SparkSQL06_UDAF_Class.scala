package com.zg.sparksql.exer

import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.{Aggregator, MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, LongType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Encoder, Encoders, Row, SparkSession, functions}

/**
 * @Project_name test
 * @Package_name com.zg.sparksql.exer
 * @author zhuguang
 * @date 2021-01-04-19:53
 */
object SparkSQL06_UDAF_Class {
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
    // 用户自定义聚合函数需要额外定义
    spark.udf.register("avgAVG", functions.udaf(new MyAgeAvgClassUDAF()))
    spark.sql("select avgAVG(age) from user").show()


    spark.stop()


  }

  case class AvgBuffer(var total: Int, var cnt: Int)


  class MyAgeAvgClassUDAF extends Aggregator[Int, AvgBuffer, Int] {
    // 缓冲区的初始化
    override def zero: AvgBuffer = {
      AvgBuffer(0,0)
    }
//    将输入的值聚合到缓冲区中
    override def reduce(b: AvgBuffer, a: Int): AvgBuffer = {
      b.total+=a
      b.cnt+=1
      b
    }

    // 合并分布式计算中的缓冲区
    override def merge(b1: AvgBuffer, b2: AvgBuffer): AvgBuffer = {
      b1.total+=b2.total
      b1.cnt+=b2.cnt
      b1

    }
    // 计算完毕时返回结果
    override def finish(reduction: AvgBuffer): Int = {
      reduction.total/reduction.cnt
    }

    override def bufferEncoder: Encoder[AvgBuffer] = Encoders.product

    override def outputEncoder: Encoder[Int] = Encoders.scalaInt
  }


}
