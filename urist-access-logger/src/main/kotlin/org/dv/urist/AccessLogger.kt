package org.dv.urist

import org.slf4j.MDC

class AccessLogger {

    fun before() {
        UristSlf4j.withField("test", "foo")
    }
}

object UristSlf4j {
    fun withField(key: String, value: String) {
        MDC.put(key, value)
    }
}
