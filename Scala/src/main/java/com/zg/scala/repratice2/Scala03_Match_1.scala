package com.zg.scala.repratice2

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice2
 * @author zhuguang
 * @date 2020-12-22-18:07
 */
object Scala03_Match {
  def main(args: Array[String]): Unit = {

    for (arr <- Array(
      Array(0),
      Array(1, 0),
      Array(0, 1, 0),
      Array(1, 1, 0),
      Array(1, 1, 0, 1),
      Array("hello", 90))){
     var rs =  arr match{
       case Array(0)=>"0"
       case Array(x,y)=>x+","+y
       case Array(0,_*)=>"以零开头的数组"

     }
      println(rs)


    }

  }


}
