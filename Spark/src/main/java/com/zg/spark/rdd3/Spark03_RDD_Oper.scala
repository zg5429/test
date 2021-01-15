package com.zg.spark.rdd3

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd3
 * @author zhuguang
 * @date 2020-12-28-20:38
 */
object Spark03_RDD_Oper {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(
      List(
        ("a", 1), ("b", 2), ("a", 3), ("c", 2)
      )
    )
//    val newRdd = rdd.groupBy(_._1)

    val newRdd1 = rdd.groupByKey()



//    newRdd.collect().foreach(println)
    newRdd1.collect().foreach(println)
    val wordCnt = newRdd1.mapValues(_.sum)
    wordCnt.collect().foreach(println)






    sc.stop()


  }

}
