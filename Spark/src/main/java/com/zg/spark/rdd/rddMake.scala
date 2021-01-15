package com.zg.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd
 * @author zhuguang
 * @date 2020-12-25-19:32
 */
object rddMake {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("spark")
    val sparkContext = new SparkContext(sparkConf)
    val seq = Seq(1, 2, 3, 4)
    val rdd1 = sparkContext.parallelize(seq)
    val rdd2 = sparkContext.makeRDD(seq)
    rdd1.collect().foreach(println)
    rdd2.collect().foreach(println)

    sparkContext.stop()

  }

}
