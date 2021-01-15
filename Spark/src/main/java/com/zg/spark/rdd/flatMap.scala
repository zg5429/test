package com.zg.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd
 * @author zhuguang
 * @date 2020-12-28-8:47
 */
object flatMap {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("spark")
    val sparkContext = new SparkContext(sparkConf)
    val dataRDD = sparkContext.makeRDD(List(
      List(1,2),List(3,4)
    ),1)
    val dataRDD1 = dataRDD.flatMap(
      list => list
    )
    dataRDD1.collect().foreach(println)

  }
}
