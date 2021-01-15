package com.zg.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd
 * @author zhuguang
 * @date 2020-12-28-18:24
 */
object Spark06_RDD_Oper_Test_1 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    sparkConf.set("spark.local.dir", "dir")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.textFile("data/apache.log")
    val hourRdd = rdd.map(
      line => {
        val datas = line.split(" ")
        val time = datas(3)
        val hour = time.substring(11, 13)
        (hour, 1)
      }
    )
    //    hourRdd.collect().foreach(println)
    val groupRDD = hourRdd.groupBy(_._1)
    groupRDD.mapValues(
      iter => {
//        iter.map(_._2).sum
        val ints = iter.map(_._2)
        ints.sum
      }
    ).collect().foreach(println)


  }

}
