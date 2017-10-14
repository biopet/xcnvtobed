package nl.biopet.tools.xcnvtobed

import java.io.File

import nl.biopet.utils.tool.AbstractOptParser

class ArgsParser(cmdName: String) extends AbstractOptParser[Args](cmdName) {
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
