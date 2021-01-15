package com.zg.spark.pro_exer

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.pro_exer
 * @author zhuguang
 * @date 2020-12-30-20:14
 */
object method3 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("top10")
    val sc = new SparkContext(sparkConf)
    // TODO 1. 读取用户行为数据
    val lines = sc.textFile("data/user_visit_action.txt")
    val mapRDD = lines.flatMap(
      line => {
        val datas = line.split("_")
        if (datas(6) != "-1") {
          List((datas(6), (1, 0, 0)))
        } else if (datas(8) != "null") {
          val ids = datas(8).split(",")
          ids.map(id => (id, (0, 1, 0)))
        } else if (datas(10) != "null") {
          val ids = datas(10).split(",")
          ids.map(id => (id, (0, 0, 1)))
        } else {
          List()
        }
      }
    )


    val allCnt = mapRDD.reduceByKey(
      (t1, t2) => {
        (t1._1 + t2._1, t1._2 + t2._2, t1._3 + t2._3)
      }
    )


    // TODO 6. 将排序后的结果取前10名

    val sortCnt = allCnt.sortBy(_._2, false)
    val result = sortCnt.take(10)
    // TODO 7. 将结果打印在控制台
    result.foreach(println)
    sc.stop()


  }

}
