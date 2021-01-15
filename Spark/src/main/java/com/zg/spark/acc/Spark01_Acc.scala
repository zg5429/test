package com.zg.spark.acc

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.acc
 * @author zhuguang
 * @date 2020-12-30-18:11
 */
object Spark01_Acc {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)
    val sum = sc.longAccumulator("sum")
    //声明累加器
    val rdd = sc.makeRDD(
      List(
        ("a", 1),
        ("a", 2),
        ("a", 3),
        ("a", 1)
      )
    )

    rdd.foreach {
      case (word, cnt) => {
        //使用累加器
        sum.add(cnt)
      }
    }
    //获取累加器的结果
    println("a", sum.value)

    sc.stop()

  }
}