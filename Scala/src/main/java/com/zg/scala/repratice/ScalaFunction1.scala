package com.zg.scala.repratice

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice
 * @author zhuguang
 * @date 2020-12-16-19:50
 */
object ScalaFunction1 {
  def main(args: Array[String]): Unit = {
    //    1)	可变参数
    def fun7(name: String*) = {
      println(name)
    }

    fun7("aa", "bb", "cc")


    //    2)	参数默认值

    def fun8(name: String, password: String = "090909") = {
      println(name + "  " + password)
    }

    fun8("aa")
    fun8("bb", "11111")

    //    3)	带名参数
    def fun9(password: String = "090909", name: String) = {
      println(password + " " + name)
    }

    fun9("2222", "cc")
    fun9(name = "dd")












  }

}
