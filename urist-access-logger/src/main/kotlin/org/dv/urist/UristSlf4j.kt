package org.dv.urist

import org.dv.urist.UristFieldNames.REQUEST_URI
import org.dv.urist.UristFieldNames.SERVICE_ID
import org.dv.urist.UristFieldNames.STATUS
import org.slf4j.MDC

class UristSlf4j(
        private val uristApplicationProperties: UristApplicationProperties
) {
    fun withField(key: String, value: Any) {
        val cleanKey = key.trim()
        val cleanValue = if (uristApplicationProperties.privateFields.contains(cleanKey)) {
            "REDACTED"
        } else {
            value.toString().trim()
        }
        MDC.put(cleanKey, cleanValue)
    }

    fun withRequestUri(requestUri: String) {
        withField(REQUEST_URI, requestUri)
    }

    fun withService(service: String) {
        withField(SERVICE_ID, service)
    }

    fun withStatus(status: Int) {
        withField(STATUS, status)
    }
}
