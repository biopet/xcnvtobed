package nl.biopet.tools.xcnvtobed

import java.io.PrintWriter

import nl.biopet.utils.tool.ToolCommand

import scala.io.Source

object XcnvToBed extends ToolCommand {
  def main(args: Array[String]): Unit = {
    val parser = new ArgsParser(toolName)
    val cmdArgs =
      parser.parse(args, Args()).getOrElse(throw new IllegalArgumentException)

    logger.info("Start")

    val writer = new PrintWriter(cmdArgs.outputBed)
    Source
      .fromFile(cmdArgs.inputXcnv)
      .getLines()
      .filter(!_.startsWith("SAMPLE"))
      .map(x => x.split("\t"))
      .map(x => XcnvBedLine(x(0), x(1), x(2)))
      .filter(_.sample == cmdArgs.sample)
      .foreach(x => writer.println(x.toString))

    writer.close()

    logger.info("Done")
  }
}
