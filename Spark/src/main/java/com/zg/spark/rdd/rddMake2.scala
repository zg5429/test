package com.zg.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd
 * @author zhuguang
 * @date 2020-12-25-19:32
 */
object rddMake2 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("spark")
    val sparkContext = new SparkContext(sparkConf)
    //20/3=6
    //20/6=3...2=>4
    /*
    [0-6]  123 kk k
    [6-12]
    [12-18] jj
    [18-20]

         */
    val fileEDD = sparkContext.textFile("data/word.txt", 3)
    fileEDD.saveAsTextFile("output")
    sparkContext.stop()

  }

}
