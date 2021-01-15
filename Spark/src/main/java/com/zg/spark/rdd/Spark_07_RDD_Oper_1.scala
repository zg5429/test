package com.zg.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd
 * @author zhuguang
 * @date 2020-12-28-18:39
 */
object Spark_07_RDD_Oper_1 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    sparkConf.set("spark.local.dir", "dir")
    val sc = new SparkContext(sparkConf)

    // TODO - filter
    val rdd = sc.textFile("data/apache.log")
    rdd.filter(
      line => {
        val datas = line.split(" ")
        val time = datas(3)
        time.startsWith("17/05/2015")

      }
    ).map(
      line => {
        val datas = line.split(" ")
        datas(6)
      }
    ).collect().foreach(println)


  }

}
