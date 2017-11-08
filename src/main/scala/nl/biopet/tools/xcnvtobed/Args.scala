package nl.biopet.tools.xcnvtobed

import java.io.File

case class Args(inputXcnv: File = null,
                outputBed: File = null,
                sample: String = null)
