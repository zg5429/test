package com.zg.scala.class_practice

/**
 * @Project_name test
 * @Package_name com.zg.scala.class_practice
 * @author zhuguang
 * @date 2020-12-19-9:11
 */
object Scala06_Object_Fileld {
  def main(args: Array[String]): Unit = {
    val user = new User()
//    user.name="lisi"
    println(user.name)




  }


  class User {
    var name: String = "zhangsang"
    val age: Int = 30
  }


}
