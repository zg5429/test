package com.zg.spark.pro_exer

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.pro_exer
 * @author zhuguang
 * @date 2020-12-30-20:14
 */
object method1 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("top10")
    val sc = new SparkContext(sparkConf)
    // TODO 1. 读取用户行为数据
    val lines = sc.textFile("data/user_visit_action.txt")

    // TODO 2. 统计品类的点击数量 ( 品类, 点击 )
    // 2.1 将原始数据进行过滤，保留品类的点击数据
    val clickDatas = lines.filter(
      line => {
        val datas = line.split("_")
        datas(6) != "-1"
      }
    )
    // 2.2 将过滤后的数据进行统计
    // ( 品类, 1 )
    val clickCnt = clickDatas.map(
      line => {
        val datas = line.split("_")
        (datas(6), 1)
      }
    ).reduceByKey(_ + _)

    // TODO 3. 统计品类的下单数量
    // 3.1 将原始数据进行过滤，保留品类的下单数据
    val orderDatas = lines.filter(
      line => {
        val datas = line.split("_")
        datas(8) != "null"
      }
    )

    // 3.2 将过滤后的数据进行统计
    // 1-3-2 => 1,3,2 => (1,1),(3,1),(2,1)
    val orderCnt = orderDatas.flatMap(
      line => {
        val datas = line.split("_")
        val ids = datas(8).split(",")
        ids.map(
          id => {
            (id, 1)
          }
        )
      }
    ).reduceByKey(_ + _)

    // TODO 4. 统计品类的支付数量
    // 4.1 将原始数据进行过滤，保留品类的支付数据
    val payDatas = lines.filter(
      line => {
        val datas = line.split("_")
        datas(10) != "null"
      }
    )


    // 4.2 将过滤后的数据进行统计
    // 1-3-2 => 1,3,2 => (1,1),(3,1),(2,1)
    val payCnt = payDatas.flatMap(
      line => {
        val datas = line.split("_")
        val ids = datas(10).split(",")
        ids.map(
          id => {
            (id, 1)
          }
        )

      }
    ).reduceByKey(_ + _)


    // TODO 5. 将统计结果排序（点击，下单，支付）(降序)
    // （品类，点击）
    // （品类，下单）  => (（品类， （点击，下单，支付）)
    // （品类，支付）
    val groupRdd = clickCnt.cogroup(orderCnt, payCnt)
    //    groupRdd.collect().foreach(println)
    val allCnt = groupRdd.mapValues {
      case ((clickIter, orderIter, payIter)) => {
        var clickSum = 0
        val iterator = clickIter.iterator
        if (iterator.hasNext) {
          clickSum = iterator.next()
        }


        var orderSum = 0
        val iterator1 = orderIter.iterator
        if (iterator1.hasNext) {
          orderSum = iterator1.next()
        }

        var paySum = 0
        val iterator2 = payIter.iterator
        if (iterator2.hasNext) {
          paySum = iterator2.next()
        }

        (clickSum, orderSum, paySum)

      }
    }

    // TODO 6. 将排序后的结果取前10名

    val sortCnt = allCnt.sortBy(_._2, false)
    val result = sortCnt.take(10)
    // TODO 7. 将结果打印在控制台
    result.foreach(println)
    sc.stop()


  }

}
