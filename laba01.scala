object lab01 extends App{

  import scala.io.Source
  import java.io.PrintWriter

  val source = scala.io.Source.fromFile("/Users/TrueNil/ml-100k/ml-100k/u.data")
  var ListN = try source.mkString.split("\n").toList finally source.close()
  val list =
    ListN.
      map(s => s.split("\t")).
      map { case Array(f1,f2,f3,f4) => (f1,f2,f3,f4)}

  val groupAll = list.groupBy(_._3).mapValues(_.size).toSeq.sortBy(_._1)
  val groupMy = list.filter(x=> x._2=="216").groupBy(_._3).mapValues(_.size).toSeq.sortBy(_._1)

  var stringjson = "{\n\t\"hist_film\": [\n" +
    (groupMy.map(x=>"\t"+x._2.toString).mkString(",\n")) +
    "\n    ],\n    \"hist_all\": [\n" +
    (groupAll.map(x=>"\t"+x._2.toString).mkString(",\n")) +"\n\t]\n}"


  new PrintWriter("/Users/TrueNil/ml-100k/ml-100k/lab01.json")
  { write(stringjson); close }

}