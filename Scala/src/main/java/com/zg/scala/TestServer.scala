package com.zg.scala

import java.io.{BufferedReader, InputStreamReader}
import java.net.ServerSocket

/**
 * @Project_name test
 * @Package_name com.zg.scala
 * @author zhuguang
 * @date 2020-12-15-18:33
 */
object TestServer {
  def main(args: Array[String]): Unit = {
    val server = new ServerSocket(6868)
    while (true) {
      val socket = server.accept()
      val reader = new BufferedReader(
        new InputStreamReader(socket.getInputStream,
          "utf-8")
      )

      var s = ""
      var flg = true
      while (flg) {
        s = reader.readLine()
        if (s != null) {
          print(s)
        } else {
          flg = false
        }
      }


    }

  }

}
