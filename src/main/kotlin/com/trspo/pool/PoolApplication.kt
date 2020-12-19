package com.trspo.pool

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class PoolApplication

fun main(args: Array<String>) {
    runApplication<PoolApplication>(*args)
}
