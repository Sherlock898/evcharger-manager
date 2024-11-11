package com.noder.cargadorws.WebSocket;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class OcppHandler extends TextWebSocketHandler {

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		
        
	}

}
