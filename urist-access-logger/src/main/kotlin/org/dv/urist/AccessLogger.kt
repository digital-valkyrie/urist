package org.dv.urist

import org.dv.urist.UristFieldNames.TEST

class AccessLogger {

    fun before() {
        UristSlf4j.withField(TEST, "foo")
    }
}

