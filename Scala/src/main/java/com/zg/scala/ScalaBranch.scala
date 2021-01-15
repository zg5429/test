package com.zg.scala

/**
 * @Project_name test
 * @Package_name com.zg.scala
 * @author zhuguang
 * @date 2020-12-15-19:39
 */
object ScalaBranch {
  def main(args: Array[String]): Unit = {
    //    var b = true
    //    if (b){
    //      println("true")
    //    }
    //
    //    if (b)
    //      println("没有大括号执行第一行")
    //    println("第二行")

    var age = scala.io.StdIn.readInt()
    //    if(age<18){
    //      println("童年")
    //    }
    //    else if (age<=30){
    //      println("青年")
    //    }
    //    else if (age<=50)
    //      println("中年")
    //
    //    else println("老年")
    var result = if (age < 18) {
      "童年"
    } else if (age <= 30) {
      "青年"
    } else {
      null
    }

    println(result)


  }


}
