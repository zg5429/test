package com.zg.spark.rdd3

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd3
 * @author zhuguang
 * @date 2020-12-28-20:38
 */
object Spark01_RDD_Oper {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    val rdd1: RDD[Int] = sc.makeRDD(
      List(1, 2, 3, 4), 2
    )

    val rdd2 = rdd1.map((_, 1))
    rdd2.collect().foreach(println)
    rdd2.partitionBy(new HashPartitioner(2)).saveAsTextFile("output")
    sc.stop()


  }

}
