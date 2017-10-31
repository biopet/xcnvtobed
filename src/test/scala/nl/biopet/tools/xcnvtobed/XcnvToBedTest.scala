package nl.biopet.tools.xcnvtobed

import nl.biopet.utils.test.tools.ToolTest
import org.testng.annotations.Test

class XcnvToBedTest extends ToolTest[Args] {
  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      XcnvToBed.main(Array())
    }
  }
}
