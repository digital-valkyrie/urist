package org.dv.urist

import org.springframework.boot.context.properties.ConfigurationProperties
import javax.validation.constraints.NotEmpty

@ConfigurationProperties("urist")
data class UristApplicationProperties(
        @NotEmpty
        val service: String
)
