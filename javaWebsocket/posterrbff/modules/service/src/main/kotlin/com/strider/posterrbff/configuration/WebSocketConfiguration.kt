package com.strider.posterrbff.configuration

import com.strider.posterrbff.listener.PostEventListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.*

@Configuration
@EnableWebSocket//habilita websocket por meio de um message broker em memória
class WebSocketConfiguration : WebSocketConfigurer {


    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(PostEventListener(), "/websocket")
    }

    @Bean
    fun webSocketHandler(): WebSocketHandler? {
        return PostEventListener()
    }

}