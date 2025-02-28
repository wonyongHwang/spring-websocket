package com.rabbit.samples.springwebsocketserver.springbased.configs;

import com.rabbit.samples.springwebsocketserver.springbased.handlers.EchoBroadcastWebSocketHandler;
import com.rabbit.samples.springwebsocketserver.springbased.handlers.EchoTextBinaryWebSocketHandler;
import com.rabbit.samples.springwebsocketserver.springbased.handlers.EchoTextWebSocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 11 Apr 2019
 */
@Slf4j
@Configuration
@EnableWebSocket
public class WebSocketHandlerConfig implements WebSocketConfigurer {

	/**
	 * The advantage of using sockJS here is whenever the websocket connection is disconnected or the websocket connection can not be established,
	 * then the connection will be downgraded to HTTP and the communication between client and server can still continue.
	 */
	@Override
	public void registerWebSocketHandlers(final WebSocketHandlerRegistry registry) {

		log.debug("Register WebSocket handlers");

		registry
				.addHandler(echoTextWebSocketHandler(), "/echo")
				.setAllowedOrigins("*")
				// .withSockJS()
				// .setSupressCors(true)
		;
		registry
				.addHandler(echoTextBinaryWebSocketHandler(), "/multi")
				.setAllowedOrigins("*")
				.withSockJS()
		;
		registry
				.addHandler(echoBroadcastWebSocketHandler(), "/broadcast")
				.setAllowedOrigins("*")
				.withSockJS()
		;
	}

	@Bean
	public WebSocketHandler echoTextWebSocketHandler() {

		log.debug("Create EchoTextWebSocketHandler");

		return new EchoTextWebSocketHandler();
	}

	@Bean
	public WebSocketHandler echoTextBinaryWebSocketHandler() {

		log.debug("Create EchoTextBinaryWebSocketHandler");

		return new EchoTextBinaryWebSocketHandler();
	}

	@Bean
	public WebSocketHandler echoBroadcastWebSocketHandler() {

		log.debug("Create EchoBroadcastWebSocketHandler");

		return new EchoBroadcastWebSocketHandler();
	}

	/**
	 * Since you will be dealing with binary messages in addition to text messages, it is a good idea to set the max binary message size.
	 */
	@Bean
	public ServletServerContainerFactoryBean createWebSocketContainer() {

		ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
		container.setMaxBinaryMessageBufferSize(1024000);
		return container;
	}

}
