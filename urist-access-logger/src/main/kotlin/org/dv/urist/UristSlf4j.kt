package org.dv.urist

import org.dv.urist.UristFieldNames.SERVICE_ID
import org.dv.urist.UristFieldNames.STATUS
import org.slf4j.MDC

object UristSlf4j {
    fun withField(key: String, value: Any) {
        MDC.put(key, value.toString())
    }

    fun withService(service: String) {
        withField(SERVICE_ID, service)
    }

    fun withStatus(status: Int) {
        withField(STATUS, status)
    }
}
