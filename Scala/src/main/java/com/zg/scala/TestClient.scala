package com.zg.scala

import java.io.{OutputStreamWriter, PrintWriter}
import java.net.Socket

/**
 * @Project_name test
 * @Package_name com.zg.scala
 * @author zhuguang
 * @date 2020-12-15-18:39
 */
object TestClient {
  def main(args: Array[String]): Unit = {
    val client = new Socket("localhost", 6868)
    val out = new PrintWriter(
      new OutputStreamWriter(
        client.getOutputStream, "utf-8"
      )
    )

    out.print("asdjflkasdfmlsdfnl")
    out.flush()
    out.close()
    client.close()
  }

}
