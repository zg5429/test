package com.zg.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd
 * @author zhuguang
 * @date 2020-12-28-15:32
 */
object wordcount_reduceByKey {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc: SparkContext = new SparkContext(sparkConf)
    val rdd1 = sc.makeRDD(
      List(("a", 1), ("b", 2), ("c", 3),("a",3)),2
    )
    val rdd2 = rdd1.reduceByKey(_ + _)
    val rdd3 = rdd1.reduceByKey(_ + _, 2)

    rdd2.collect().foreach(println)
    println("*****************")
    rdd3.collect().foreach(println)


    sc.stop()


  }

}
