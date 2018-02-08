/*
 * Copyright (c) 2014 Biopet
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package nl.biopet.tools.xcnvtobed

import java.io.PrintWriter

import nl.biopet.utils.tool.ToolCommand

import scala.io.Source

object XcnvToBed extends ToolCommand[Args] {
  def emptyArgs: Args = Args()

  def argsParser = new ArgsParser(this)

  def main(args: Array[String]): Unit = {
    val cmdArgs = cmdArrayToArgs(args)

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

  def descriptionText: String =
    """
      |This tool converts a sample track within an XHMM XCNV file to a BED track.
      |In the output the fourth column indicates
      |the type of CNV for the region: deletion (-1), normal (0) or duplication (1).
    """.stripMargin

  def manualText: String =
    """This tool converts a sample track within an XHMM XCNV file to a BED track.
      |In the output the fourth column indicates the type of CNV for the region:
      |deletion (-1), normal (0) or duplication (1).""".stripMargin

  def exampleText = {

    //Example must be unsafe because input file does not exist.
    unsafeExample("-I", "input.xcnv", "-O", "output.bed", "-S", "sample") +
      """
    |Specify in sample which sample should be extracted to the BED file.
  """.stripMargin
  }
}
