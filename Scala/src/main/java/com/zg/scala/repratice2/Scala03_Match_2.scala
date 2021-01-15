package com.zg.scala.repratice2

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice2
 * @author zhuguang
 * @date 2020-12-22-18:07
 */
object Scala03_Match_2 {
  def main(args: Array[String]): Unit = {

    // TODO 匹配元组
    for (tuple <- Array(
      (0, 1),
      (1, 0),
      (1, 1),
      (1, 0, 2))) {

      val result = tuple match {
        case (0, _) => "0 ..." //是第一个元素是0的元组
        case (y, 0) => "" + y + "0" // 匹配后一个元素是0的对偶元组
        case (a, b) => "" + a + " " + b
        case _ => "something else" //默认
      }
      println(result)


    }

  }


}
