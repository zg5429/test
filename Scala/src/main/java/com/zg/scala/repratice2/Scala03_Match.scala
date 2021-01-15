package com.zg.scala.repratice2

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice2
 * @author zhuguang
 * @date 2020-12-22-18:07
 */
object Scala03_Match {
  def main(args: Array[String]): Unit = {

    def describe(x:Any)={
      x match {
        case i: Int => i + 1
        case s: String => "String hello"
        case m: List[Int] => "List"
        case c: Array[Int] => "Array[Int]"
        case someThing => "something else " + someThing
      }
    }


    println(describe(5))
    println(describe("aaaa"))
    println(describe(List(1,2)))
    println(describe(Array(1,2,3)))
    println(describe(Array("1","2","3")))
    println(describe('a'))




  }


}
