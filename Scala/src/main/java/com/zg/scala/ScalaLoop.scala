package com.zg.scala

/**
 * @Project_name test
 * @Package_name com.zg.scala
 * @author zhuguang
 * @date 2020-12-15-20:03
 */
object ScalaLoop {
  def main(args: Array[String]): Unit = {
    //    for (i<- 1 to 5){
    //      println(i)
    //    }

    //    for (j <- 1 until 5) {
    //      println(j)
    //    }

    //    for (i <- 1 to 5 by 2) {
    //      println(i)
    //    }

    //    for(i <- Range(1,5,2)){
    //      println(i)
    //    }

    //    for (i<- Range(1,10) if i %3==0){
    //      println(i)
    //    }

    //    for (i<-Range(1,5); j<-Range(1,4)){
    //      println("i = "+i+",j="+j)
    //    }

    //     for(i<-Range(1,5)){
    //      for (j<-Range(1,4)){
    //        println("i = "+i+",j="+j)
    //      }
    //    }


    //    for (i<-Range(1,9);j<-Range(1,2*i-1)){
    //      println("*")
    //    }

    //    for (i <- Range(0, 10)) {
    //      for (j <- Range(0, 10 - i)) {
    //        print(" ")
    //      }
    //      for (k <- Range(0, 2 * i - 1)) {
    //        print("*")
    //      }
    //      println()
    //    }

    //    for (i <- Range(1, 5); j = i - 1) {
    //      println("j = " + j)
    //    }


    //    for (i <- Range(0, 10)) {
    //      for (j <- Range(0, 10 - i)) {
    //        print(" ")
    //      }
    //      for (k <- Range(0, 2 * i - 1)) {
    //        print("*")
    //      }
    //      println()
    //    }


    for (i <- Range(0, 10); j = " " * (10 - i) + "*" * (2 * i - 1)) {
      println(j)
    }

    for (i <- Range(1, 10)) {
      for (j <- Range(1, i+1); k = j + "*" + i + "=" + j * i + " ") {
        print(k)

      }
      println()
    }



  }

}
