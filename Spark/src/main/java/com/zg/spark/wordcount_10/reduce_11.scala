package com.zg.spark.wordcount_10

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.wordcount_10
 * @author zhuguang
 * @date 2020-12-29-19:18
 */
object reduce_11 {
  def main(args: Array[String]): Unit = {
    val sparkconf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("reduce")
    val sc = new SparkContext(sparkconf)
    val rdd: RDD[String] = sc.textFile("data/word.txt")
    val lines: RDD[String] = rdd.flatMap(_.split(" "))
    val mapRDD: RDD[Map[String, Int]] = lines.map(str => {
      Map[String, Int](str -> 1)
    })
//    mapRDD.collect().foreach(println)
    val wordMap: Map[String, Int] = mapRDD.reduce((map1, map2) => {
      map1.foldLeft(map2) {
        case (map, (k, v)) => {
          val cnt: Int = map.getOrElse(k, 0) + v
          map.updated(k, cnt)
        }
      }
    })
    println(wordMap)
  }

}
