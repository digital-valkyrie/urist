package org.dv.urist

import org.dv.urist.UristFieldNames.SERVICE_ID
import org.slf4j.MDC

object UristSlf4j {
    fun withField(key: String, value: String) {
        MDC.put(key, value)
    }

    fun withService(service: String) {
        withField(SERVICE_ID, service)
    }
}
