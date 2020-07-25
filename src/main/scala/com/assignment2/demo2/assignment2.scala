package com.assignment2.demo2
import scala.io.Source
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.io.PrintWriter 
import scala.collection.JavaConverters._
import com.typesafe.config.ConfigFactory

object hello {
  def main(args: Array[String])
  {
   val confFile=ConfigFactory.load()
   val inputpath=confFile.getString("file.input")
   println(inputpath)
   val outputpath=confFile.getString("file.output")
   println(outputpath)
   
   val inputCsvFile=new File(inputpath)
   val csvSchema = CsvSchema.builder().setUseHeader(true).build()
   val csvMapper = new CsvMapper()
   val readAll = csvMapper
    .readerFor(classOf[java.util.Map[String, String]])
    .`with`(csvSchema)
    .readValues(inputCsvFile)
    .readAll()

   val mapper = new ObjectMapper()
   val s=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll)
  println(s)
  
    val outputjsonfile=new File(outputpath)
    val print_Writer = new PrintWriter(outputjsonfile)
    print_Writer.write(s)  
    print_Writer.close() 
   
  
}
}