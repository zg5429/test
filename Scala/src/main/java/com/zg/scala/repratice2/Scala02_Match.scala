package com.zg.scala.repratice2

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice2
 * @author zhuguang
 * @date 2020-12-22-18:07
 */
object Scala02_Match {
  def main(args: Array[String]): Unit = {



    var a :Int=10
    var b :Int=20
    var operator:Char='a'
    val rs = operator match {
      case '+' => a + b
      case '-' => a - b
      case '*' => a * b
      case '/' => a / b
      case _ => "illegal"
    }

    println(rs)






  }


}
