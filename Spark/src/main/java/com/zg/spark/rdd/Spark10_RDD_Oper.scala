package com.zg.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd
 * @author zhuguang
 * @date 2020-12-28-18:47
 */
object Spark10_RDD_Oper {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    sparkConf.set("spark.local.dir", "dir")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.makeRDD(
      List(1, 2, 3, 4, 5, 6, 7), 3
    )
    // coalesce方法可以缩减分区，但是数据不会被打乱重新组合
    // coalesce方法在缩减分区时，需要合并分区，合并的原则其实依赖于计算的位置
    // coalesce方法默认处理是没有shuffle操作，但是如果想要数据均衡一些，那么可以设定shuffle
    rdd.coalesce(2, true).saveAsTextFile("output")
    sc.stop()
  }

}
