package com.zg.scala.collection

import scala.collection.mutable.ArrayBuffer

/**
 * @Project_name test
 * @Package_name com.zg.scala.collection
 * @author zhuguang
 * @date 2020-12-21-11:04
 */
object ArrayTest {
  def main(args: Array[String]): Unit = {
    val arr01 = new Array[Int](3)
    println(arr01)

    val ints = Array(1,2,3,4)
    val ints1 = Array(5,6,7,8)

    ints.foreach(println(_))

    val ints2 = Array.concat(ints,ints1)
    ints2.foreach(println(_))


    val buffer = ArrayBuffer[String]()
    buffer.append("a")
    buffer.append("b")
    buffer.append("c")
    buffer.foreach(println(_))
    buffer.update(0,"d")
    buffer.remove(2)
    buffer.foreach(println)

    var myMatrix = Array.ofDim[Int](3,3)
    myMatrix(1)(1)=3
    myMatrix.foreach(list=>list.foreach(println))



    val ints3 = ArrayBuffer(8,9,10,11)
    val ints4 = ArrayBuffer(8,9,10,11)

    val buffer1 = ints.toBuffer
    buffer1.foreach(print)

    val array = buffer.toArray






  }
}
