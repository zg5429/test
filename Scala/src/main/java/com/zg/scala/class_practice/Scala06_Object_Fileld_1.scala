package com.zg.scala.class_practice

import scala.annotation.meta.beanSetter
import scala.beans.BeanProperty

/**
 * @Project_name test
 * @Package_name com.zg.scala.class_practice
 * @author zhuguang
 * @date 2020-12-19-9:11
 */
object Scala06_Object_Fileld_1 {
  def main(args: Array[String]): Unit = {
    val user = new User()
//    user.name="lisi"
    user.setName("lisi")
    println(user.name)




  }


  class User {
    @BeanProperty
    var name: String = "zhangsang"
    val age: Int = 30
  }


}
