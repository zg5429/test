package com.zg.scala

/**
 * @Project_name test
 * @Package_name
 * @author zhuguang
 * @date 2020-12-14-10:56
 */

object HelloScala {
  def main(args: Array[String]): Unit = {
    //    System.out.println("Hello Scala")
    //    val name = "aa"
    //    println("Hello Scala")
    //    println(name)
    var o = "\n"
    println("aa" + o + "bb")

    val a = new String("aaa")
    val b = new String("aaa")
    println(a == b)
    println(a.equals(b))
    println(a.eq(b))


    for (i <- Range(1, 5); j <- Range(1, 4)) {
      println("i = " + i + ",j = " + j)
    }
    println("---------------------")
    for (i <- Range(1, 5)) {
      for (j <- Range(1, 4)) {
        println("i = " + i + ",j = " + j)
      }

    }


    val result = for ( i <- Range(1,5) ) yield {
      i * 2
    }
    println(result)



  }

}
