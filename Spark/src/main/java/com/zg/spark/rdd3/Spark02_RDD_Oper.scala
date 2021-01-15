package com.zg.spark.rdd3

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd3
 * @author zhuguang
 * @date 2020-12-28-20:38
 */
object Spark02_RDD_Oper {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(
      List(
        ("a", 1), ("b", 2), ("a", 3),("c",2)
      )
    )
    rdd.reduceByKey(_+_).collect().foreach(println)


    val rdd1 = rdd.reduceByKey(_+_)

    println(rdd1.collect().mkString(","))

        sc.stop()


  }

}
