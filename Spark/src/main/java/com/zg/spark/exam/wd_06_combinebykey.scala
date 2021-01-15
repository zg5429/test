package com.zg.spark.exam

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.exam
 * @author zhuguang
 * @date 2020-12-30-8:46
 */
object wd_06_combinebykey {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.textFile("data/word.txt").flatMap(_.split(" "))
    rdd.map((_, 1)).combineByKey(
      v => v,
      (v1: Int, v2) => {
        v1 + v2
      },
      (t1: Int, t2: Int) => {
        t1 + t2
      }
    ).collect().foreach(println)
    sc.stop()
  }

}
