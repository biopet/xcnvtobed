package nl.biopet.tools.xcnvtobed

import nl.biopet.test.BiopetTest
import org.testng.annotations.Test

object XcnvToBedTest extends BiopetTest {
  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      XcnvToBed.main(Array())
    }
  }
}
