package com.zg.scala.repratice2

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice2
 * @author zhuguang
 * @date 2020-12-22-19:15
 */
object Scala05_Match {
  def main(args: Array[String]): Unit = {

    val yy = new User("yy",20 )
    yy match {
      case User("yy",20)=>print("用户为yy")
      case _=> print("不是")
    }



  }

  case class User(var name:String,age:Int)

}
