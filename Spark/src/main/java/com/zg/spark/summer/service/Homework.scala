package com.atguigu.shili

import org.apache.spark.{SparkConf, SparkContext}

object HomeWork {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("homework")
    val context = new SparkContext(conf)
    val value = context.textFile("data/user_visit_action.txt")
    val userRDD = value.map(
      line => {
        val strings = line.split("_")
        UserVisitAction(
          strings(0),
          strings(1).toLong,
          strings(2),
          strings(3).toLong,
          strings(4),
          strings(5),
          strings(6).toLong,
          strings(7).toLong,
          strings(8),
          strings(9),
          strings(10),
          strings(11),
          strings(12).toLong
        )
      }
    )
    val listRDD = userRDD.map(
      user => (user.session_id, (user.page_id, user.action_time))
    ).groupByKey().map(
      kv => {
        kv._2.toList.sortBy(_._2)
      }
    )
    val tRDD = listRDD.map(
      l => {
        l.map(
          t => {
            val strings = t._2.split(" ")
            val strings1 = strings(0).split("-")
            val strings2 = strings(1).split(":")
            (t._1, (strings1(2),strings2(0), strings2(1), strings2(2)))
          }
        )
      }
    )
    val timeRDD = tRDD.map(
      l => {
        var i = 1
        l.init.map(
          t => {
            var time = (l(i)._2._2.toInt - t._2._2.toInt) * 3600 + (l(i)._2._3.toInt - t._2._3.toInt) * 60 + (l(i)._2._4.toInt - t._2._4.toInt)
            if(t._2._1 != l(i)._2._1){
              time += 24*3600
            }
            i += 1
            (t._1, time)
          }
        )
      }
    )
    val result = timeRDD.flatMap(l => l).map(t=>(t._1,(t._2,1))).reduceByKey((t1,t2)=>(t1._1+t2._1,t1._2+t2._2)).mapValues(t =>(t._1.toDouble/t._2).formatted("%.2f"))
    result.sortBy(_._2,false).collect().foreach(println)

    context.stop()
  }
  case class UserVisitAction(
                              date: String,//用户点击行为的日期
                              user_id: Long,//用户的ID
                              session_id: String,//Session的ID
                              page_id: Long,//某个页面的ID
                              action_time: String,//动作的时间点
                              search_keyword: String,//用户搜索的关键词
                              click_category_id: Long,//某一个商品品类的ID
                              click_product_id: Long,//某一个商品的ID
                              order_category_ids: String,//一次订单中所有品类的ID集合
                              order_product_ids: String,//一次订单中所有商品的ID集合
                              pay_category_ids: String,//一次支付中所有品类的ID集合
                              pay_product_ids: String,//一次支付中所有商品的ID集合
                              city_id: Long
                            )
}
