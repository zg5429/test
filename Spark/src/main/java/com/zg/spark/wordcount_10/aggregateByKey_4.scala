package com.zg.spark.wordcount_10

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.wordcount_10
 * @author zhuguang
 * @date 2020-12-29-18:37
 */
object aggregateByKey_4 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)
    val lines = sc.textFile("data/word.txt")
    val newRdd = lines.flatMap(_.split(" "))
    newRdd.map((_, 1)).aggregateByKey(0)(_+_,_+_).collect().foreach(println)
    sc.stop()
  }

}
