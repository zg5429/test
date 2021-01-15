package com.zg.spark.exam

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @Project_name test
 * @Package_name com.zg.spark.exam
 * @author zhuguang
 * @date 2020-12-30-8:50
 */
object wd_09_cogroup {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.textFile("data/word.txt").flatMap(_.split(" "))
    val rdd1= rdd.map((_, 1))
    val rdd2= rdd1.cogroup(rdd1)
    val map1= rdd2.map(kv => {
      val num: Int = kv._2._1.size
      (kv._1, num)
    })
    map1.collect.foreach(println)
    sc.stop()
  }

}
