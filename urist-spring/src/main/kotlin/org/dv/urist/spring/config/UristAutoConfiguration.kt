package org.dv.urist.spring.config

import org.dv.urist.AccessLogger
import org.dv.urist.UristApplicationProperties
import org.dv.urist.UristSlf4j
import org.dv.urist.spring.SpringAccessLoggingFilter
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(UristApplicationProperties::class)
class UristAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun accessLogger(uristApplicationProperties: UristApplicationProperties): AccessLogger =
            AccessLogger(
                    uristApplicationProperties,
                    UristSlf4j(uristApplicationProperties)
            )

    @Bean
    @ConditionalOnMissingBean
    fun springAccessLoggingFilter(accessLogger: AccessLogger): SpringAccessLoggingFilter {
        return SpringAccessLoggingFilter(accessLogger)
    }

    @Bean
    @ConditionalOnMissingBean
    fun uristAccessLoggingFilter(springAccessLoggingFilter: SpringAccessLoggingFilter): FilterRegistrationBean<SpringAccessLoggingFilter> =
            FilterRegistrationBean(springAccessLoggingFilter)
                    .apply {
                        setName("urist-access-logger")
                        order = 1
                        urlPatterns = listOf("/*")
                    }
}
