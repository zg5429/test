package com.zg.spark.summer.service

import com.zg.spark.summer.common.TService
import com.zg.spark.summer.dao.WordCountDao
import org.apache.spark.rdd.RDD

/**
 * @Project_name test
 * @Package_name com.zg.spark.summer.service
 * @author zhuguang
 * @date 2021-01-03-15:27
 */
class WordCountService extends TService {
  private val WordCountDao = new WordCountDao()

  //数据分析
  override def analysis() = {
    val rdd: RDD[String] = WordCountDao.readFile("data/word.txt")
    val wordRDD = rdd.flatMap(_.split(" "))
    val wordToOneRDD = wordRDD.map((_, 1))
    val wordToCountRDD = wordToOneRDD.reduceByKey(_ + _)
    wordToOneRDD.collect()
  }
}
