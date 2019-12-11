package athena.socket.websocket.application.service;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/WebSocketServer")
public class ServerEndPoint {
	
	@OnOpen
	public void start(Session session) {
		System.out.println("连接成功！ " +session.getId());
	}
	
	@OnMessage
	public void reMessage(Session session, String string) {
		try {
			session.getBasicRemote().sendText(string + " who are you");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@OnError
	public void error(Session session, Throwable t) {
		t.printStackTrace();
	}
	
	@OnClose
	public void close(){}
	

}
