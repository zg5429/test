package com.zg.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd
 * @author zhuguang
 * @date 2020-12-27-19:11
 */
object Spark01_Rdd_Oper_Test {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("wordcount")
    val sc = new SparkContext(sparkConf)

    val logrdd = sc.textFile("data/apache.log")
    val url = logrdd.map(
      line => {
        val datas = line.split(" ")
        datas(6)
      }
    )
    url.collect().foreach(println)





    sc.stop()


  }

}
