package org.dv.urist

import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders.REFERER
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AccessLogger(
        private val uristApplicationProperties: UristApplicationProperties,
        private val uristSlf4j: UristSlf4j
) {
    private val log = LoggerFactory.getLogger(AccessLogger::class.java)

    fun before() {
        uristSlf4j.withService(uristApplicationProperties.service)
    }

    fun after(request: HttpServletRequest, response: HttpServletResponse) {
        uristSlf4j.withStatus(response.status)

        uristSlf4j.withRequestUri(request.requestURI)
        uristSlf4j.withQueryParam(request.queryString)
        uristSlf4j.withReferrer(request.getHeader(REFERER))

        if (uristApplicationProperties.accessLoggingEnabled) {
            log.info(uristApplicationProperties.accessLogMessage)
        }
    }
}

