package com.zg.spark.acc

import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.acc
 * @author zhuguang
 * @date 2020-12-30-18:11
 */
object Spark03_Acc {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)
    val acc = new MyAcc()
    sc.register(acc)
    val rdd = sc.makeRDD(List(1, 2, 3, 4))

    rdd.foreach(
      num => acc.add(num)
    )
    println(acc.value)
    sc.stop()


  }


  class MyAcc extends AccumulatorV2[Int, Int] {
    private var sum = 0

    override def isZero: Boolean = sum == 0

    override def copy(): AccumulatorV2[Int, Int] = {
      new MyAcc
    }

    override def reset(): Unit = {
      sum = 0
    }

    override def add(v: Int): Unit = {
      sum += v
    }

    override def merge(other: AccumulatorV2[Int, Int]): Unit = {
      sum += other.value
    }

    override def value: Int = sum
  }

}