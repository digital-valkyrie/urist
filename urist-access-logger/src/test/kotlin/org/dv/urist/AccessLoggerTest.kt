package org.dv.urist

import org.assertj.core.api.SoftAssertions
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.MDC
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.web.util.UriComponentsBuilder

@ExtendWith(SoftAssertionsExtension::class)
class AccessLoggerTest {

  private val serviceId = "urist-service"

  private val uristApplicationProperties = UristApplicationProperties(
      service = serviceId,
      privateFields = setOf()
  )

  private val accessLogger = AccessLogger(
      uristSlf4j = UristSlf4j(uristApplicationProperties),
      uristApplicationProperties = uristApplicationProperties
  )

  @AfterEach
  fun tearDown() {
    MDC.clear()
  }

  @Test
  fun `When before request, then Urist should add useful parameters to the MDC`(softly: SoftAssertions) {
    softly.assertThat(MDC.getCopyOfContextMap())
        .isNull()

    accessLogger.before()

    softly.assertThat(MDC.get(UristFieldNames.SERVICE_ID))
        .isEqualTo(serviceId)
  }

  @Test
  fun `When after request, then Urist should read request parameters and add them to the MDC`(softly: SoftAssertions) {
    softly.assertThat(MDC.getCopyOfContextMap())
        .isNull()
    val request = MockHttpServletRequest().also {
      val uri = UriComponentsBuilder.newInstance()
          .path("/api/orders/3e2a36b7-57cf-40d7-b28e-c942ee0d03c3")
          .queryParam("expand", "email")
          .build()
      it.requestURI = uri.path
      it.queryString = uri.query
    }
    val response = MockHttpServletResponse().also {
      it.status = 200
    }

    accessLogger.after(request, response)

    softly.assertThat(MDC.get(UristFieldNames.STATUS))
        .isEqualTo(response.status.toString())
    softly.assertThat(MDC.get(UristFieldNames.REQUEST_URI))
        .isEqualTo(request.requestURI)
    softly.assertThat(MDC.get(UristFieldNames.QUERY_PARAM))
        .isEqualTo("expand=email")
  }

}
