package com.strider.posterrbff.listener

import com.strider.posterrbff.listener.model.request.PostRequest
import com.strider.posterrbff.listener.model.response.GreetingResponse
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.util.HtmlUtils


@Controller
class GreetingController {

    @MessageMapping("/hello") //hello queue receives data from client
    @SendTo("/topic/greetings")//send message to greetings topic
    fun greeting(message: PostRequest): GreetingResponse {
        Thread.sleep(1000) // simulated delay
        return GreetingResponse("Hello, " + HtmlUtils.htmlEscape(message.name) + "!")
    }
}