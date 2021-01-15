package com.zg.spark.action

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.action
 * @author zhuguang
 * @date 2020-12-29-18:15
 */
object Spark04_RDD_OPer {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)
    val rdd1 = sc.makeRDD(
            List(1,2,3,1),2
//      List(("a", 1), ("b", 2), ("a", 3), ("c", 2)), 2

    )

    val map1 = rdd1.countByValue()
    println(map1)


  }

}
