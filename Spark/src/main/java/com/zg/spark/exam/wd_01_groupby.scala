package com.zg.spark.exam

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.exam
 * @author zhuguang
 * @date 2020-12-30-8:31
 */
object wd_01_groupby {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.textFile("data/word.txt").flatMap(_.split(" "))
    rdd.map((_,1)).groupBy(_._1).mapValues(_.size).collect().foreach(println)
    sc.stop()

  }

}
