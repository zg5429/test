package com.zg.scala.repratice2

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice2
 * @author zhuguang
 * @date 2020-12-22-18:07
 */
object Scala04_Match_1 {
  def main(args: Array[String]): Unit = {
    val map1 = Map(
      ("a", 1), ("b", 2), ("C", 3)
    )
    for ((k, v) <- map1) {
      println(k + "=" + v)
    }
    for ((k, 2) <- map1) {
      println(k)
    }


    val (id, name, age) = (1, "zhangsan", 30)
    println(name)


    val list = List(
      ("a", 1), ("b", 2), ("c", 3)
    )


    //    val newlist = list.map(
    //      t => {
    //        (t._1, t._2)
    //      }
    //    )
    //    print(newlist)

    val newlist = list.map {
      case (word, cnt) => {
        (word, cnt * 2)
      }
    }
    print(newlist)

    val list2 = List(
      (("河北", "鞋"), 20),
      (("河北", "衣服"), 40),
      (("河北", "帽子"), 30)
    )

    val newlist3 = list2.map {
      case ((prv, item), cnt) => {
        (prv, (item, cnt * 2))
      }
    }
   print(newlist3)


  }


}
