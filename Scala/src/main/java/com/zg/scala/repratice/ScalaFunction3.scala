package com.zg.scala.repratice

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice
 * @author zhuguang
 * @date 2020-12-16-19:50
 */
object ScalaFunction3 {
  def main(args: Array[String]): Unit = {

    //    函数作为值

    def fun1(): String = {
      "aaaaa"
    }

    val a = fun1
    val b = fun1 _
    //    val f3: () => Unit = fun1
    //    val f4: Function0[Unit] = fun1
    println(a) //将函数结果赋值给a
    println(b) // 将函数赋值为对象


    //    5.2.2 函数作为参数
    def fun2(i: Int): Int = {
      i * i
    }

    def fun22(f: Int => Int): Int = {
      f(10)
    }

    println(fun22(fun2))

    //    5.2.3 函数作为返回值
    def fun3(i: Int): Int = {
      i * 2
    }

    def fun33() = {
      fun3 _
    }

    println(fun33()(10))


    //    5.2.4 匿名函数

    def fun4(f: Int => Int): Int = {
      f(10)
    }

    println(fun4((x: Int) => {
      x * 2
    }))
    println(fun4((x: Int) => {
      x / 2
    }))
    println(fun4((x: Int) => {
      x + 2
    }))

    println(fun4((x) => {
      x * 2
    }))
    println(fun4((x) => x * 2))
    println(fun4(x => x * 2))
    println(fun4(_ * 2))




//    5.2.5 闭包
    def fun8()={
      val i=2
      def fun88()={
        i*2
      }
      fun88 _
    }
    fun8()()

  }

}
