
# Spring WebSocket

## Run

1. Start websocket server
	```shell
	cd websocket-server/
	mvnw clean spring-boot:run
	```

2. Open browser and visit `http://localhost:8080/`

---

## Endpoints

* spring-based
	* /echo
	* /multi
	* /broadcast
* javax-based
	* /reverse
	* /chat
* stomp-based
	* /greeting

---

## Features

- [x] text message
- [x] binary message
- [x] broadcasting
- [x] stomp message broker (server)
- [x] stomp-based client
- [x] javax-based client
- [ ] testing

---

## Links

### Guides
* https://spring.io/guides/gs/messaging-stomp-websocket/
#### Spring Session WebSocket
* https://docs.spring.io/spring-session/docs/current/reference/html5/guides/boot-websocket.html

### Tutorials
#### Spring based
* https://www.baeldung.com/rest-vs-websockets
#### Javax based
* https://www.baeldung.com/java-websockets
#### Text + Binary
* https://www.nexmo.com/blog/2018/10/08/create-websocket-server-spring-boot-dr/
#### Without STOMP
* https://www.devglan.com/spring-boot/spring-websocket-integration-example-without-stomp
#### With STOMP message broker
* https://www.devglan.com/spring-boot/spring-boot-websocket-integration-example
* https://www.devglan.com/spring-boot/spring-session-stomp-websocket
* https://www.baeldung.com/websockets-spring
* https://www.baeldung.com/websockets-api-java-spring-client
* https://stackoverflow.com/questions/30413380/websocketstompclient-wont-connect-to-sockjs-endpoint

#### @SendToUser
* https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#websocket-stomp-user-destination

### Samples
* https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-websocket-jetty

---
### 안드로이드 <-> 소켓서버와 통신 오류 문제
* wss로 구축하려니 복잡해서 ws 통신하기 위해 안드로이드 매니페스트 파일에 셋팅 추가
   android:usesCleartextTraffic="true"
* 상기 적용 이후, 안드로이드에서 Expected HTTP 101 response but was '200 OK' 오류 수신
   웹소켓 서버쪽 소스에서 sockJS 코드 부분 주석 처리 (https://stackoverflow.com/questions/29829597/i-get-a-status-200-when-connecting-to-the-websocket-but-it-is-an-error 참고함)
   (서버 소스 : https://github.com/bygui86/spring-websocket)

   		registry
				.addHandler(echoTextWebSocketHandler(), "/echo")
				.setAllowedOrigins("*");
				.withSockJS() -> 이 한 줄 주석처리!

   위 주석 처리 후 다시 빌드 : mvn clean install -DskipTests=true
   aws에 빌드한 jar 업로드 후 java -jar 로 실행
