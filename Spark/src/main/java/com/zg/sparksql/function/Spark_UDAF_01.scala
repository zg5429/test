package com.zg.sparksql.function

import org.apache.avro.generic.GenericData.StringType
import org.apache.parquet.format.IntType
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, IntegerType, LongType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
 * @Project_name test
 * @Package_name com.zg.sparksql.function
 * @author zhuguang
 * @date 2021-01-05-20:51
 */
object Spark_UDAF_01 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("UDF")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    import spark.implicits._
    val rdd: RDD[(String, Int)] = spark.sparkContext.makeRDD(List(("zhangsan", 20), ("lisi", 30), ("wangw", 40)))


    val df: DataFrame = rdd.toDF("name", "age")
    df.createOrReplaceTempView("user")

    spark.udf.register("ageAVG", new MyAverage())

    spark.sql("select ageAVG(age) from user").show()


    spark.stop()

  }

  class MyAverage extends UserDefinedAggregateFunction {

    override def inputSchema: StructType = {
      StructType(Array(
        StructField("age", LongType),
        StructField("name", DoubleType)
      ))
    }

    override def bufferSchema: StructType = {
      StructType(Array(
        StructField("sum", LongType),
        StructField("cnt", LongType)
      ))
    }

    override def dataType: DataType = DoubleType

    override def deterministic: Boolean = true

    override def initialize(buffer: MutableAggregationBuffer): Unit = {
      buffer(0) = 0L
      buffer(1) = 0L
    }

    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
      if (!input.isNullAt(0)) {
        buffer(0) = buffer.getLong(0) + input.getLong(0)
        buffer(1) = buffer.getLong(1) + 1
      }
    }

    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
      buffer1(0) = buffer1.getLong(0) + buffer2.getLong(0)
      buffer1(1) = buffer1.getLong(1) + buffer2.getLong(1)
    }

    override def evaluate(buffer: Row): Any = {
      buffer.getLong(0).toDouble / buffer.getLong(1)
    }
  }

}
