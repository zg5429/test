package com.zg.spark.exam

import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

/**
 * @Project_name test
 * @Package_name com.zg.spark.exam
 * @author zhuguang
 * @date 2020-12-30-8:55
 */
object wd_10_Acc {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.textFile("data/word.txt").flatMap(_.split(" "))
    val acc = new WordCountAccumulator()
    sc.register(acc)
    rdd.foreach(
      word=>{
        acc.add(word)
      }
    )

    print(acc.value)

    sc.stop()
  }


  class WordCountAccumulator extends AccumulatorV2[String, mutable.Map[String, Int]] {
    private var wordCountMap = mutable.Map[String, Int]()

    override def isZero: Boolean = wordCountMap.isEmpty

    override def copy(): AccumulatorV2[String, mutable.Map[String, Int]] = {
      new WordCountAccumulator
    }

    override def reset(): Unit = {
      wordCountMap.clear()
    }

    override def add(word: String): Unit = {
      val oldcnt = wordCountMap.getOrElse(word,0)
      val newcnt = oldcnt+1
      wordCountMap.update(word,newcnt)

    }

    override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {
      val map1 = this.wordCountMap
      val map2 = other.value
      wordCountMap=map1.foldLeft(map2)(
        (map,kv)=>{

          val word = kv._1
          val cnt = kv._2

          val oldcnt = map.getOrElse(word,0)
          val newcnt = oldcnt+cnt
          map.update(word,newcnt)
          map

        }
      )

    }

    override def value: mutable.Map[String, Int] = wordCountMap
  }

}


