package com.zg.spark.wordcount_10

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.wordcount_10
 * @author zhuguang
 * @date 2020-12-29-18:26
 */
object cogroup_10 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)
    val lines = sc.textFile("data/word.txt")

    val flatRDD: RDD[String] = lines.flatMap(_.split(" "))
    val mapRDD: RDD[(String, Int)] = flatRDD.map((_, 1))
    val cogroupRDD: RDD[(String, (Iterable[Int], Iterable[Int]))] = mapRDD.cogroup(mapRDD)
//    cogroupRDD.collect().foreach(println)
    val map1RDD: RDD[(String, Int)] = cogroupRDD.map(kv => {
      val num: Int = kv._2._1.size
      (kv._1, num)
    })
    map1RDD.collect.foreach(println)
    sc.stop()
  }

}
