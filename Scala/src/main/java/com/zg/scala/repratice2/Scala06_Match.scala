package com.zg.scala.repratice2

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice2
 * @author zhuguang
 * @date 2020-12-22-19:15
 */
object Scala06_Match {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5, 6, "test")

//    val newlist1 = list.filter(!_.isInstanceOf[String]).map(_.asInstanceOf[Int] + 1)


    //偏函数
    val newList = list.collect({
      case num: Int => num + 1
    })
    println(newList)

  }
}
