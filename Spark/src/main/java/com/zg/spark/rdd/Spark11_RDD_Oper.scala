package com.zg.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd
 * @author zhuguang
 * @date 2020-12-28-18:47
 */
object Spark11_RDD_Oper {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    sparkConf.set("spark.local.dir", "dir")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.makeRDD(
      List(1, 2, 3, 4, 5, 6, 7), 2)
    rdd.sortBy(num => num, false).collect().foreach(println)

    sc.stop()

  }

}
