package com.zg.scala.class_practice

import scala.beans.BeanProperty

/**
 * @Project_name test
 * @Package_name com.zg.scala.class_practice
 * @author zhuguang
 * @date 2020-12-19-9:11
 */
object Scala06_Object_Fileld_2 {
  def main(args: Array[String]): Unit = {

    val user = new User()

    println(user.name)



  }


  class User {
    //var使用下划线初始化
    var name: String = _
    val age: Int = 0
  }


}
