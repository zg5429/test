package com.zg.sparksql.exer

import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, LongType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
 * @Project_name test
 * @Package_name com.zg.sparksql.exer
 * @author zhuguang
 * @date 2021-01-04-19:53
 */
object SparkSQL06_UDAF {
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

    spark.udf.register("avgAVG", new MyAgeAvgUDAF())
    spark.sql("select avgAVG(age) from user").show()


    spark.stop()


  }


  class MyAgeAvgUDAF extends UserDefinedAggregateFunction {
    //向聚合函数中输入的数据结构
    override def inputSchema: StructType = {
      StructType(Array(
        StructField("age", LongType)
      ))
    }

    //聚合函数中用于处理计算的数据结构
    override def bufferSchema: StructType = {
      StructType(Array(
        StructField("total", LongType),
        StructField("total", LongType)
      ))
    }

    // 聚合函数处理结果的返回类型
    override def dataType: DataType = LongType

    //    稳定性
    override def deterministic: Boolean = true

    // 缓冲区的初始化
    override def initialize(buffer: MutableAggregationBuffer): Unit = {
      buffer.update(0, 0L)
      buffer.update(1, 0L)

    }

    // 将输入的值更新到缓冲区中
    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
      val inputVal: Long = input.getLong(0)
      val oldValue: Long = buffer.getLong(0)
      val oldCnt: Long = buffer.getLong(1)
      buffer.update(0, inputVal + oldValue)
      buffer.update(1, oldCnt + 1)
    }
    // 合并分布式计算中的多个缓冲区
    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
      buffer1.update(0,buffer1.getLong(0)+buffer2.getLong(0))
      buffer1.update(1,buffer1.getLong(1)+buffer2.getLong(1))
    }

    override def evaluate(buffer: Row): Any = {
      buffer.getLong(0) / buffer.getLong(1)
    }
  }


}
