package org.dv.urist.spring

import org.dv.urist.AccessLogger
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Answers.RETURNS_SMART_NULLS
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.mock.web.MockFilterChain
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse

@ExtendWith(MockitoExtension::class)
internal class SpringAccessLoggingFilterTest {

    @Mock(answer = RETURNS_SMART_NULLS)
    lateinit var accessLogger: AccessLogger

    @InjectMocks
    lateinit var springAccessLoggingFilter: SpringAccessLoggingFilter

    @Test
    fun `test`() {
        springAccessLoggingFilter.doFilter(MockHttpServletRequest(), MockHttpServletResponse(), MockFilterChain())
    }
}
