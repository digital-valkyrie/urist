package org.dv.urist

import javax.servlet.http.HttpServletResponse

class AccessLogger(
        private val uristApplicationProperties: UristApplicationProperties
) {

    fun before() {
        UristSlf4j.withService(uristApplicationProperties.service)
    }

    fun after(response: HttpServletResponse) {
        UristSlf4j.withStatus(response.status)
    }
}

