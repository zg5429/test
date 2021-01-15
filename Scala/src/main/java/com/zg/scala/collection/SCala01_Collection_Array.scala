package com.zg.scala.collection

/**
 * @Project_name test
 * @Package_name com.zg.scala.collection
 * @author zhuguang
 * @date 2020-12-21-9:20
 */
object SCala01_Collection_Array {
  def main(args: Array[String]): Unit = {

    ///*
    val arr = new Array[String](9)
    arr(0) = "zbangsan"
    arr(1) = "ls"
    arr(2) = "ww"
    println(arr)
    //*/
    //    val array:Array[String] = Array.apply("aa","bb","cc")
    val array: Array[String] = Array("aa", "bb", "cc")
    println(array(1))

    //    for(i<-0 until  array.length){
    //      println(array(i))
    //    }

    //    def test(s:String)={
    //      println(s)
    //    }
    //    array.foreach(test)
    //       array.foreach((s:String)=>{println(s)})
    //       array.foreach((s:String)=>println(s))
    //       array.foreach((s)=>println(s))
    //       array.foreach(s=>println(s))
    //       array.foreach(println(_))
    array.foreach(println)

    println(array.mkString(","))


  }

}
