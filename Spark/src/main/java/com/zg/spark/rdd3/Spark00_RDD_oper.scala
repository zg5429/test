package com.zg.spark.rdd3

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd3
 * @author zhuguang
 * @date 2020-12-28-21:11
 */
object Spark00_RDD_oper {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(
      List("a","a","b","c")
    )
    val newRdd = rdd.map((_, 1))
    newRdd.groupBy(_._1).mapValues(_.size).collect().foreach(println)


    sc.stop()
  }

}
