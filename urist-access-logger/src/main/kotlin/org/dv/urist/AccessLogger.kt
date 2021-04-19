package org.dv.urist

class AccessLogger {

    fun before() {
        UristSlf4j.withService("a-microservice")
    }
}

