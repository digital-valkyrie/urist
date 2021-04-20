package org.dv.urist.spring

import org.dv.urist.AccessLogger
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SpringAccessLoggingFilter(private val accessLogger: AccessLogger) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  filterChain: FilterChain) {
        accessLogger.before()

        filterChain.doFilter(request, response)

        accessLogger.after(request, response)
    }
}
