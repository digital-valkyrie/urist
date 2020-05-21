package org.dv.urist

import org.slf4j.MDC

object UristSlf4j {
    fun withField(key: String, value: String) {
        MDC.put(key, value)
    }
}
