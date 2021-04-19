package org.dv.urist

import org.assertj.core.api.SoftAssertions
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.MDC

@ExtendWith(SoftAssertionsExtension::class)
class AccessLoggerTest {

  private val serviceId = "urist-service"

  private val accessLogger = AccessLogger(
      uristApplicationProperties = UristApplicationProperties(
          service = serviceId
      )
  )

  @Test
  fun testsDummyValue(softly: SoftAssertions) {
    softly.assertThat(MDC.get(UristFieldNames.SERVICE_ID))
        .isNull()

    accessLogger.before()

    softly.assertThat(MDC.get(UristFieldNames.SERVICE_ID))
        .isEqualTo(serviceId)
  }

}
