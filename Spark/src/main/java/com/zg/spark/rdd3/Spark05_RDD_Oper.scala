package com.zg.spark.rdd3

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd3
 * @author zhuguang
 * @date 2020-12-28-20:38
 */
object Spark05_RDD_Oper {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(
      List(
        ("a", 1), ("b", 2), ("a", 3), ("c", 2)
      )
    )
    // 当aggregateByKey的分区内计算规则和分区间计算规则相同时，可以简化为一个
//    rdd.aggregateByKey(0)(_+_,_+_).collect().foreach(println)
  rdd.foldByKey(0)(_+_).collect().foreach(println)





    sc.stop()


  }

}
