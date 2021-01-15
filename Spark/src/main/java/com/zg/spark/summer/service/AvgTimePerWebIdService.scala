package com.zg.spark.summer.service

import java.text.SimpleDateFormat
import java.util.Date

import com.zg.spark.summer.bean.UserVisitAction
import com.zg.spark.summer.common.TService
import com.zg.spark.summer.dao.{AvgTimePerWebIdDao, HotCategoryDao}
import org.apache.spark.rdd.RDD


/**
 * @Project_name test
 * @Package_name com.zg.spark.summer.service
 * @author zhuguang
 * @date 2021-01-03-16:51
 */
class AvgTimePerWebIdService extends TService {
  private val avgTimePerWebIdDao = new AvgTimePerWebIdDao()

  override def analysis() = {
    val actionRDD = avgTimePerWebIdDao.readFile("data/user_visit_action.txt")

    //    将原始数据转化内类（对象）使用
    val actionObject = actionRDD.map(
      line => {
        val datas = line.split("_")
        UserVisitAction(
          datas(0),
          datas(1).toLong,
          datas(2),
          datas(3).toLong,
          datas(4),
          datas(5),
          datas(6).toLong,
          datas(7).toLong,
          datas(8),
          datas(9),
          datas(10),
          datas(11),
          datas(12).toLong
        )

      }
    )
    actionObject.cache()

    //    计算分母

    val webIdCountMap = actionObject.map(
      atcion => {
        (atcion.page_id, 1)

      }
    ).reduceByKey(_ + _).collect.toMap


    //    计算分子
    val groupRDD: RDD[(String, Iterable[UserVisitAction])] = actionObject.groupBy(_.session_id)

    //    分组
    val zipRDD = groupRDD.mapValues(
      iter => {
        val actions: List[UserVisitAction] = iter.toList.sortBy(_.action_time)
        val flowids = actions.map(
          action => {
            (action.page_id, action.action_time)
          }
        )
        flowids.map(
          lt => {
            val time = lt._2
            val timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val truetime: Date = timeFormat.parse(time)

            (lt._1, truetime)
          }
        )

      }
    )
    //    zipRDD.collect().foreach(println)

    //    (07c08858-8686-4cd0-9088-34202751d0a3,List((28,02:45:35), (26,02:45:44), (3,02:45:54), (36,02:46:03), (37,02:46:06), (21,02:46:07), (23,02:46:09), (50,02:46:12)))
    //    (179b326a-3475-4f26-b16c-5be5fa5420bf,List((5,Tue Jul 23 06:22:33 CST 2019), (40,Tue Jul 23 06:22:33 CST 2019), (14,Tue Jul 23 06:22:37 CST 2019)

    val timesizeRDD: RDD[(String, List[(Long, Long)])] = zipRDD.mapValues(
      iter => {
        val actions = iter.toList.sortBy(_._2)
        val ctTime = actions.zip(actions.tail)
        ctTime.map {
          case ((pageid1, id1), (pageid2, id2)) => {
            val timesize: Long = id2.getTime() - id1.getTime()
            (pageid1, timesize)

          }
        }

      }
    )
//    timesizeRDD.collect().foreach(println)

    val timegroup1: RDD[List[(Long, Long)]] = timesizeRDD.map(_._2)
    val timegroup2: RDD[(Long, Long)] = timegroup1.flatMap(list => list)
    val result: RDD[(Long, Long)] = timegroup2.reduceByKey(_ + _)

    val rs: Any = result.foreach {
      case (pagect, sum) => {
        val total: Int = webIdCountMap.getOrElse(pagect, 1)
        val avttimebysessionid = (sum.toDouble / total).toLong

                println(s"页面pageid-[${pagect}]} 平均停留时间=" + sum.toLong / total)

      }

    }
    result.map {
      case (pagect, sum) => {
        val total: Int = webIdCountMap.getOrElse(pagect, 1)
        val avttimebysessionid = (sum.toDouble / total).toLong
        (pagect, avttimebysessionid)
      }
    }.sortBy(_._2,false).take(3)




  }
}
