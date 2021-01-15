package com.zg.spark.rdd3

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd3
 * @author zhuguang
 * @date 2020-12-28-20:38
 */
object Spark04_RDD_Oper_1 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(
      List(
        ("a", 1), ("b", 2), ("a", 3), ("c", 2)
      )
    )

    rdd.aggregateByKey(0)(_+_,_+_).collect().foreach(println)
    rdd.reduceByKey(_+_).collect().foreach(println)





    sc.stop()


  }

}
