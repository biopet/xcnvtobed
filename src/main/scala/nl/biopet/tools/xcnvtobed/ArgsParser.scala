/*
 * Copyright (c) 2014 Sequencing Analysis Support Core - Leiden University Medical Center
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

import java.io.File

import nl.biopet.utils.tool.{AbstractOptParser, ToolCommand}

class ArgsParser(toolCommand: ToolCommand[Args])
    extends AbstractOptParser[Args](toolCommand) {
  head(
    "Convert a sample track within an XHMM XCNV file to a BED track. Fourt column indicates deletion (-1), normal (0) or duplication (1) of region")

  opt[File]('I', "Input") required () valueName "<xcnv>" action { (x, c) =>
    c.copy(inputXcnv = x)
  } validate { x =>
    if (x.exists) success else failure("Input XCNV not found")
  } text {
    "Input XCNV file"
  }
  opt[File]('O', "Output") required () valueName "<bed>" action { (x, c) =>
    c.copy(outputBed = x)
  } text {
    "Output BED file"
  }
  opt[String]('S', "Sample") required () action { (x, c) =>
    c.copy(sample = x)
  } text {
    "The sample which to select"
  }
}
