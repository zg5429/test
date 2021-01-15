package com.zg.scala

/**
 * @Project_name test
 * @Package_name com.zg.scala
 * @author zhuguang
 * @date 2020-12-15-18:15
 */

object ScalaIn {
  def main(args: Array[String]): Unit = {

//    println("请输入数字")
//    val age  = scala.io.StdIn.readLong()
//    println(age)
//
//    println("请输入字符")
//    val s = scala.io.StdIn.readLine()
//    println(s)

    scala.io.Source.fromFile("D:/mysqldb.txt").foreach(
      line => {
        print(line)
      }
    )
    val strings = scala.io.Source.fromFile("D:/mysqldb.txt").getLines()
    print(strings)

  }
}
