package com.zg.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd
 * @author zhuguang
 * @date 2020-12-27-19:11
 */
object Spark02_Rdd_Oper {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("wordcount")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.makeRDD(List(1, 2, 3, 4),2)
    val newrdd = rdd.mapPartitions(
      list => {
        list.map(_ * 2)
//        List(1,2).iterator
//        List(list.max).iterator


      }
    )
    newrdd.collect().foreach(println)


    sc.stop()


  }

}
