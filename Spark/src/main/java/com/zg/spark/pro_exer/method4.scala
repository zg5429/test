package com.zg.spark.pro_exer

import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

/**
 * @Project_name test
 * @Package_name com.zg.spark.pro_exer
 * @author zhuguang
 * @date 2020-12-30-20:14
 */
object method4 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("top10")
    val sc = new SparkContext(sparkConf)
    val hcacc = new HotCategoryAccumulator
    sc.register(hcacc, "hot")
    // TODO 1. 读取用户行为数据
    val lines = sc.textFile("data/user_visit_action.txt")
    lines.foreach(
      line => {
        val datas = line.split("_")

        if (datas(6) != "-1") {
          // 点击的场合
          hcacc.add((datas(6), "click"))
        } else if (datas(8) != "null") {
          // 下单的场合
          val ids = datas(8).split(",")
          ids.foreach(
            id => {
              hcacc.add((id, "order"))
            }
          )
        } else if (datas(10) != "null") {
          val ids = datas(10).split(",")
          ids.foreach(
            id => {
              hcacc.add((id, "pay"))
            }
          )
        }
      }
    )
    val accValue: mutable.Map[String, HotCategory] = hcacc.value
    val categories: mutable.Iterable[HotCategory] = accValue.map(_._2)

    categories.toList.sortWith(
      (hc1, hc2) => {
        if (hc1.clickCnt > hc2.clickCnt) {
          true
        } else if (hc1.clickCnt == hc2.clickCnt) {
          if (hc1.orderCnt > hc2.orderCnt) {
            true
          } else if (hc1.orderCnt == hc2.orderCnt) {
            hc1.payCnt > hc2.payCnt
          } else {
            false
          }
        } else {
          false
        }
      }
    ).take(10).foreach(println)

    sc.stop()


  }

  case class HotCategory(
                          var cid: String,
                          var clickCnt: Long,
                          var orderCnt: Long,
                          var payCnt: Long
                        )

  class HotCategoryAccumulator extends AccumulatorV2[(String, String), mutable.Map[String, HotCategory]] {
    private var hcMap = mutable.Map[String, HotCategory]()

    override def isZero: Boolean = hcMap.isEmpty

    override def copy(): AccumulatorV2[(String, String), mutable.Map[String, HotCategory]] = {
      new HotCategoryAccumulator
    }

    override def reset(): Unit = {
      hcMap.clear()
    }

    override def add(v: (String, String)): Unit = {
      val cid: String = v._1
      val actionType = v._2
      val category = hcMap.getOrElse(cid, HotCategory(cid, 0, 0, 0))
      actionType match {
        case "click" => category.clickCnt += 1
        case "order" => category.orderCnt += 1
        case "pay" => category.payCnt += 1
      }
      hcMap.update(cid, category)

    }

    override def merge(other: AccumulatorV2[(String, String), mutable.Map[String, HotCategory]]): Unit = {
      other.value.foreach {
        case (cid, otherHC) => {
          val thisHC: HotCategory = hcMap.getOrElse(cid, HotCategory(cid, 0, 0, 0))
          thisHC.clickCnt += otherHC.clickCnt
          thisHC.orderCnt += otherHC.orderCnt
          thisHC.payCnt += otherHC.payCnt
          hcMap.update(cid, thisHC)
        }
      }

    }

    override def value: mutable.Map[String, HotCategory] = hcMap
  }

}
