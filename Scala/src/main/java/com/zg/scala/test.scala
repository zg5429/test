package com.zg.scala

/**
 * @Project_name test
 * @Package_name com.zg.scala
 * @author zhuguang
 * @date 2020-12-16-14:11
 */
object test {
  def main(args: Array[String]): Unit = {
    def test(s: String): Unit = {
      println(s)
    }

    def fun1(): Unit = {
      println("无参 无返回值")
    }

    def fun2(): String = {
      "无参  有返回值"
    }

    def fun3(s: String): Unit = {
      println("有参  无返回值")
    }

    def fun4(S: String): String = {
      "有参 有返回值"
    }

    def fun5(s: String, name: String): Unit = {
      println("多参 无返回值" + s + name)
    }

    def fun6(s: String, name: String): String = {
      s + name
    }

    //  可变参数
    def fun7(names: String*): Unit = {
      println(names)
    }

    def fun8(age: Int, names: String*): Unit = {
      println(names)
    }

    //参数默认值
    def fun9(name: String, password: String = "111"): Unit = {
      println(name + "," + password)
    }

    //呆名参数
    def fun10(password: String = "111", name: String): Unit = {
      println(name + "," + password)
    }
    fun10(name = "fyc")




  }

}
