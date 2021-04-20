package org.dv.urist.examples

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/example")
@RestController
class ExampleController {

    private val log = LoggerFactory.getLogger(ExampleController::class.java)

    @GetMapping
    fun example(): String {
        log.info("EXAMPLE")
        return "OK"
    }
}
