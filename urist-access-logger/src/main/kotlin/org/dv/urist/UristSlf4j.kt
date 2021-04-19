package org.dv.urist

import org.dv.urist.UristFieldNames.QUERY_PARAM
import org.dv.urist.UristFieldNames.REFERRER
import org.dv.urist.UristFieldNames.REQUEST_URI
import org.dv.urist.UristFieldNames.SERVICE_ID
import org.dv.urist.UristFieldNames.STATUS
import org.dv.urist.UristFieldNames.USER_AGENT
import org.slf4j.LoggerFactory
import org.slf4j.MDC

const val NO_VALUE_GIVEN = "-"

class UristSlf4j(
        private val uristApplicationProperties: UristApplicationProperties
) {
    private val log = LoggerFactory.getLogger(UristSlf4j::class.java)

    fun withField(key: String, value: Any?) {

        val cleanKey = key.trim()
        val cleanValue = if (uristApplicationProperties.privateFields.contains(cleanKey)) {
            "REDACTED"
        } else {
            value?.toString()?.trimToNull()
        }

        MDC.put(cleanKey, cleanValue ?: NO_VALUE_GIVEN)
    }

    fun withQueryParam(queryParam: String?) {
        withField(QUERY_PARAM, queryParam)
    }

    fun withReferrer(referrer: String?) {
        withField(REFERRER, referrer)
    }

    fun withRequestUri(requestUri: String?) {
        withField(REQUEST_URI, requestUri)
    }

    fun withService(service: String?) {
        withField(SERVICE_ID, service)
    }

    fun withStatus(status: Int?) {
        withField(STATUS, status)
    }

    fun withUserAgent(userAgent: String?) {
        withField(USER_AGENT, userAgent)
    }

    private fun String.trimToNull(): String? {
        return if (isBlank()) null else trim()
    }
}
