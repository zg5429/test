package com.zg.spark.rdd3

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.rdd3
 * @author zhuguang
 * @date 2020-12-28-20:38
 */
object Spark06_RDD_Oper {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(
      List(
        ("a", 88), ("b", 95), ("a", 91),
        ("b", 93), ("a", 95), ("b", 98)
      )
    )

    rdd.combineByKey(
      v=> v,
      (v1:Int,v2)=>{
        // v1 = （88，1）
        // v2 = 91
        v1+v2
      },
      (t1:Int,t2:Int)=>{
        t1+t2
      }
    ).collect().foreach(println)





    sc.stop()


  }

}
