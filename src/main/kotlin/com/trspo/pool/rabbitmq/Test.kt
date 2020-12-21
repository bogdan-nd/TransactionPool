package com.trspo.pool.rabbitmq

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("start")
class Test {
    @Autowired
    lateinit var messageProducer: MessageProducer

    @GetMapping
    fun acb(){
        messageProducer.sendStartMessage()
    }
}