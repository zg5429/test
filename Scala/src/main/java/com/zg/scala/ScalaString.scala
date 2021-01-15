package com.zg.scala

/**
 * @Project_name test
 * @Package_name com.zg.scala
 * @author zhuguang
 * @date 2020-12-14-19:07
 */
object ScalaString {
  def main(args: Array[String]): Unit = {
    val name = "scala"
    val subname = name.substring(1,2)
//    println(name)
//    println(subname)
//    println("hello"+ name)
//
//    printf("name=%s\nsubname=%s\n",name,subname)
//
//
//    println(s"name=${name} ${subname}")

    println(
      s"""
         |Hello
         |${name}
         |          ${subname}
         |""".stripMargin)

  }


}
