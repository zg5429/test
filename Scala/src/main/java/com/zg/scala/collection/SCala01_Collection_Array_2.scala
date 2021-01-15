package com.zg.scala.collection

/**
 * @Project_name test
 * @Package_name com.zg.scala.collection
 * @author zhuguang
 * @date 2020-12-21-9:20
 */
object SCala01_Collection_Array_02 {
  def main(args: Array[String]): Unit = {
    val aray:Array[Int]= Array(1,2,3,4)
//    val newArray = aray.+:(5)
    val newArray = aray :+5
    val newArray1 = 5 +:aray

    println(aray.eq(newArray))

  }

}
