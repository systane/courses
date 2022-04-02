package com.strider.posterrbff.listener

import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler


class PostEventListener : TextWebSocketHandler() {

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        println("MENSAGEM RECEBIDA: ${message.payload}")

        session.sendMessage(TextMessage("olaaaaaaaaaa"))
    }

}