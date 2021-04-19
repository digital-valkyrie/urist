package org.dv.urist

import org.slf4j.LoggerFactory
import javax.servlet.http.HttpServletResponse

class AccessLogger(
        private val uristApplicationProperties: UristApplicationProperties
) {
    private val log = LoggerFactory.getLogger(AccessLogger::class.java)

    fun before() {
        UristSlf4j.withService(uristApplicationProperties.service)
    }

    fun after(response: HttpServletResponse) {
        UristSlf4j.withStatus(response.status)

        if (uristApplicationProperties.accessLoggingEnabled) {
            log.info(uristApplicationProperties.accessLogMessage)
        }
    }
}

