package nl.biopet.tools.xcnvtobed

case class XcnvBedLine(sample: String, cnvType: String, location: String) {
  override def toString: String = {
    val cnv = if (cnvType == "DEL") {
      -1
    } else if (cnvType == "DUP") {
      1
    } else 0

    val locs = location.split(":")
    val chr = locs(0)
    val start = locs(1).split("-")(0)
    val stop = locs(1).split("-")(1)
    s"$chr\t$start\t$stop\t$cnv"
  }
}
