package com.zg.spark.summer.service

import com.zg.spark.summer.bean.UserVisitAction
import com.zg.spark.summer.common.TService
import com.zg.spark.summer.dao.{HotCategoryDao, PageFlowDao}
import org.apache.spark.rdd.RDD

/**
 * @Project_name test
 * @Package_name com.zg.spark.summer.service
 * @author zhuguang
 * @date 2021-01-03-16:51
 */
class PageFlowService extends TService {
  private val pageFlowDao = new PageFlowDao()

  override def analysis() = {

    val actionRDD = pageFlowDao.readFile("data/user_visit_action.txt")

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
    //计算分母
    val pageCountMap = actionObject.map(
      atcion => {
        (atcion.page_id, 1)

      }
    ).reduceByKey(_ + _).collect.toMap


    //计算分子

    val groupRDD: RDD[(String, Iterable[UserVisitAction])] = actionObject.groupBy(_.session_id)
//    groupRDD.collect().foreach(println)

    //    分组后的时间进行排序
    val zipRDD: RDD[(String, List[(String, Int)])] = groupRDD.mapValues(
      iter => {
        val actions: List[UserVisitAction] = iter.toList.sortBy(_.action_time)
        val flowids = actions.map(
          action => {
            (action.page_id)
          }
        )

        val idToid = flowids.zip(flowids.tail)
        idToid.map {
          case (id1, id2) => {
            (id1 + "-" + id2, 1)
          }
        }
      }
    )

    zipRDD.collect().foreach(println)

    val listRDD: RDD[List[(String, Int)]] = zipRDD.map(_._2)
    val flatRDD: RDD[(String, Int)] = listRDD.flatMap(list=>list)

    val resultRDD: RDD[(String, Int)] = flatRDD.reduceByKey(_+_)

    resultRDD.foreach{
      case(pageflow,sum)=>{
        val pageid1: String = pageflow.split("-")(0)
        val total: Int = pageCountMap.getOrElse(pageid1.toLong,1)
        println(s"页面跳转[${pageflow}} 转换率="+sum.toDouble/total)
      }
    }




  }
}
