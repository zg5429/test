package com.zg.spark.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name
 * @author zhuguang
 * @date 2020-12-24-16:19
 */
object wd {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc: SparkContext = new SparkContext(sparkConf)
    val lineRDD: RDD[String] = sc.textFile("data/word.txt")
    val wordRDD: RDD[String] = lineRDD.flatMap(_.split(" "))
    val wordToOneRDD: RDD[(String, Int)] = wordRDD.map((_, 1))
    val wordcnt = wordToOneRDD.reduceByKey(_ + _)
    val collect = wordcnt.collect()
    collect.foreach(println)


      sc.stop()
  }

}
