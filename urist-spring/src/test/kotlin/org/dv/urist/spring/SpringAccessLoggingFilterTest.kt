package org.dv.urist.spring

import assertk.assertThat
import assertk.assertions.isFailure
import org.dv.urist.AccessLogger
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Answers.RETURNS_SMART_NULLS
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import javax.servlet.FilterChain

@ExtendWith(MockitoExtension::class)
internal class SpringAccessLoggingFilterTest {

    @Mock(answer = RETURNS_SMART_NULLS)
    lateinit var accessLogger: AccessLogger

    @Mock(answer = RETURNS_SMART_NULLS)
    lateinit var filterChain: FilterChain

    @InjectMocks
    lateinit var springAccessLoggingFilter: SpringAccessLoggingFilter

    @Test
    fun `Given normal case, then follow prepare the logger, and log after the filterChain`() {
        val request = MockHttpServletRequest()
        val response = MockHttpServletResponse()

        springAccessLoggingFilter.doFilter(request, response, filterChain)

        verify(accessLogger).before()
        verify(filterChain).doFilter(request, response)
        verify(accessLogger).after(request, response)
    }

    @Test
    fun `Given error in filterChain, then continue to log the request`() {
        val request = MockHttpServletRequest()
        val response = MockHttpServletResponse()
        given(filterChain.doFilter(request, response))
                .willThrow(RuntimeException::class.java)

        assertThat { springAccessLoggingFilter.doFilter(request, response, filterChain) }
                .isFailure()

        verify(accessLogger).before()
        verify(filterChain).doFilter(request, response)
        verify(accessLogger).after(request, response)
    }
}
