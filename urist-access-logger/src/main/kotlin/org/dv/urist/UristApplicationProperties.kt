package org.dv.urist

import org.springframework.boot.context.properties.ConfigurationProperties
import javax.validation.constraints.NotEmpty

const val DEFAULT_ACCESS_LOG_MESSAGE = "ACCESS_LOG"

@ConfigurationProperties("urist")
data class UristApplicationProperties(
        val accessLoggingEnabled: Boolean = true,
        @NotEmpty
        val service: String,
        val accessLogMessage: String = DEFAULT_ACCESS_LOG_MESSAGE
)
