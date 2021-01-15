package com.zg.sparksql.exer

import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql._

/**
 * @Project_name test
 * @Package_name com.zg.sparksql.exer
 * @author zhuguang
 * @date 2021-01-04-19:53
 */
object SparkSQL06_UDAF_Class_2X {
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
    val ds: Dataset[User] = df.as[User]

    val udaf = new MyAgeAvgClassUDAF()
    ds.select(udaf.toColumn).show()


    spark.stop()


  }

  case class AvgBuffer(var total: Int, var cnt: Int)

  case class User(id: Int, name: String, age: Int)


  class MyAgeAvgClassUDAF extends Aggregator[User, AvgBuffer, Int] {
    override def zero: AvgBuffer = {
      AvgBuffer(0, 0)
    }

    override def reduce(b: AvgBuffer, user: User): AvgBuffer = {
      b.total += user.age
      b.cnt += 1
      b
    }

    override def merge(b1: AvgBuffer, b2: AvgBuffer): AvgBuffer = {
      b1.total += b2.total
      b1.cnt += b2.cnt
      b1
    }

    override def finish(reduction: AvgBuffer): Int = {
      reduction.total / reduction.cnt
    }

    override def bufferEncoder: Encoder[AvgBuffer] = Encoders.product

    override def outputEncoder: Encoder[Int] = Encoders.scalaInt
  }


}
