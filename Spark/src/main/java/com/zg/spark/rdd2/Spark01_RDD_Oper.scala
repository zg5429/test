package com.zg.spark.rdd2

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd2
 * @author zhuguang
 * @date 2020-12-28-19:21
 */
object Spark01_RDD_Oper {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("wordcount")
    sparkConf.set("spark.local.dir", "dir")
    val sc = new SparkContext(sparkConf)
    val rdd1 = sc.makeRDD(
      List(1, 2, 3, 4)
    )
    val rdd2 = sc.makeRDD(
      List(3, 4, 5, 6)
    )
    println("交集:" + rdd1.intersection(rdd2).collect().mkString(","))
    println("并集:" + rdd1.union(rdd2).collect().mkString(","))
    println("差集:" + rdd1.subtract(rdd2).collect().mkString(","))


    sc.stop()

  }

}
