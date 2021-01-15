package com.zg.spark.exam

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.exam
 * @author zhuguang
 * @date 2020-12-30-8:55
 */
object wd_10_reduce {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.textFile("data/word.txt").flatMap(_.split(" "))
    val rdd1 = rdd.map(str => {
      Map[String, Int](str -> 1)
    })
    val map1 = rdd1.reduce((map1, map2) => {
      map1.foldLeft(map2) {
        case (map, (k, v)) => {
          val cnt: Int = map.getOrElse(k, 0) + v
          map.updated(k, cnt)
        }
      }
    })
    println(map1)
    sc.stop()
  }

}


