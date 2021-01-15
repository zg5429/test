package com.zg.spark.wordcount_10

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.wordcount_10
 * @author zhuguang
 * @date 2020-12-29-18:37
 */
object aggregate_9 {
  def main(args: Array[String]): Unit = {
    val sparkconf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("aggregate")
    val sc = new SparkContext(sparkconf)
    val lines: RDD[String] = sc.textFile("data/word.txt")
    val newRdd: RDD[String] = lines.flatMap(_.split(" "))
    val mapRDD: RDD[(String, Int)] = newRdd.map((_, 1))
    val wordmap: Map[String, Int] = mapRDD.aggregate(Map[String, Int]())  ((kv1, kv2) => {
      val v: Int = kv1.getOrElse(kv2._1, 0) + kv2._2
      kv1.updated(kv2._1, v)
    }, (map1, map2) => {
      map1.foldLeft(map2) {
        case (map, (k, v)) => {
          map + (k -> (map.getOrElse(k, 0) + v))
        }
      }
    })
    println(wordmap)
  }
}