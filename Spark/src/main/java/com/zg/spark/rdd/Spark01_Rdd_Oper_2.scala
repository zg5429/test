package com.zg.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd
 * @author zhuguang
 * @date 2020-12-27-19:11
 */
object Spark01_Rdd_Oper_2 {
  def main(args: Array[String]): Unit = {
   val sparkConf = new SparkConf().setMaster("local[*]").setAppName("wordcount")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(
      List(1, 2, 3, 4),2
    )

    val newrdd = rdd.map(
      num => {
        println("*************"+num)
        num
      }
    )

    val newrdd1 = newrdd.map(
      num => {
        println("###########"+num)
        num
      }
    )
    newrdd1.collect()


    sc.stop()






  }

}
