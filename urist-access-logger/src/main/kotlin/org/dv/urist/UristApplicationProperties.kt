package org.dv.urist

import org.dv.urist.UristFieldNames.QUERY_PARAM
import org.dv.urist.UristFieldNames.REFERRER
import org.dv.urist.UristFieldNames.REQUEST_URI
import org.dv.urist.UristFieldNames.USER_AGENT
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import javax.validation.constraints.NotEmpty

const val DEFAULT_ACCESS_LOG_MESSAGE = "ACCESS_LOG"

@ConstructorBinding
@ConfigurationProperties("urist")
data class UristApplicationProperties(
        val accessLoggingEnabled: Boolean = true,
        @NotEmpty
        val service: String,
        val accessLogMessage: String = DEFAULT_ACCESS_LOG_MESSAGE,
        val privateFields: Set<String> = setOf(
                REQUEST_URI,
                QUERY_PARAM,
                REFERRER,
                USER_AGENT
        )
)
