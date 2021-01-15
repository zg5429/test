package com.zg.scala

/**
 * @Project_name test
 * @Package_name com.zg.scala
 * @author zhuguang
 * @date 2020-12-16-15:56
 */
object test3 {
  def main(args: Array[String]): Unit = {

    def test() = {
      println("test....")
    }
    //    val f1 = test
    //    println(f1)

    val f2 = test _
    val f3: () => Unit = test
    val f4: Function0[Unit] = test

    //    f2()
    //    f3
    def f = () => {
      println("aaa")
    }
    //    f()


    def test1(i: Int, j: Int): Int = {
      i + j
    }

    val f1: (Int, Int) => Int = test1
    //    f1(90, 9)
    //    println(f1(10, 20))


    def test3(f: (Int, Int) => Int) = {
      println(f(10, 10))
    }

    def sum(i: Int, j: Int): Int = {
      i + j
    }

    def minus(i: Int, j: Int): Int = {
      i - j
    }

    def aaa(i: Int, j: String): String = {
      i + j
    }
    //
    //    test3(sum)
    //    test3(sum _)
    //    test3(minus)
    // TODO 将函数作为参数使用时，一般都是使用匿名函数
    // TODO 匿名函数也有至简原则
    test3((i: Int, j: Int) => {
      i + j
    })

    test3((i: Int, j: Int) => {
      i * j
    })
    // TODO 函数逻辑代码只有一行，大括号可以省略
    test3((i: Int, j: Int) => i + j)
    // TODO 函数的参数类型可以推断出来，那么参数类型可以省略
    test3((i, j) => i + j)
    // TODO 如果匿名函数中的参数按照顺序只使用一次，那么可以采用下划线代替参数
    test3(_ + _)


    def test4(f: (String) => Unit): Unit = {
      f("ssssss")
    }

    def aa(s: String): Unit = {
      println(s)
    }

    test4((s: String) => {
      println(s)
    })
    test4((s: String) => println(s))
    test4((s) => println(s))


  }

}
