package com.zg.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd
 * @author zhuguang
 * @date 2020-12-28-18:14
 */
object Spark06_RDD_Oper_1 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    sparkConf.set("spark.local.dir", "dir")
    val sc = new SparkContext(sparkConf)


    val rdd = sc.makeRDD(
      List(1, 2, 3, 4),2
    )
   rdd.groupBy((num:Int)=>{num%2},10).collect().foreach(println)


    sc.stop()




  }

}
