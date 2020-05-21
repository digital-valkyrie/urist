package org.dv.urist

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("urist")
data class UristApplicationProperties(
        val service: String
)
