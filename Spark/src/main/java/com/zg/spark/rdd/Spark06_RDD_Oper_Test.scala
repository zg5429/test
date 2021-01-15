package com.zg.spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd
 * @author zhuguang
 * @date 2020-12-28-18:24
 */
object Spark06_RDD_Oper_Test {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    sparkConf.set("spark.local.dir", "dir")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List("Hello", "hive", "hbase", "Hadoop"))
    rdd.groupBy(_.substring(0,1)).collect().foreach(println)
    rdd.groupBy(_.substring(0,1).toLowerCase).collect().foreach(println)


}

}
