package org.dv.urist

class AccessLogger(
        private val uristApplicationProperties: UristApplicationProperties
) {

    fun before() {
        UristSlf4j.withService(uristApplicationProperties.service)
    }
}

