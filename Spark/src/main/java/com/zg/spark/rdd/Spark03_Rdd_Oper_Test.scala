package com.zg.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd
 * @author zhuguang
 * @date 2020-12-27-19:11
 */
object Spark03_Rdd_Oper_Test {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("wordcount")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.makeRDD(List(1, 2, 3, 4,5,6),3)
    val newrdd = rdd.mapPartitionsWithIndex(
      (index, list) => {
        if (index == 1) {
          list
        } else {
          List().iterator
        }
      }
    )


    newrdd.collect().foreach(println)


    sc.stop()


  }

}
