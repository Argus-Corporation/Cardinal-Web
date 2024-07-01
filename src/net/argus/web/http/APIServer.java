package net.argus.web.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.HttpServer;

import net.argus.Cardinal;

public class APIServer {
	
	public static final int DEFAULT_PORT = 8000;
	public static final int DEFAULT_BACKLOG = 100;
	public static final String NAME = "Cardinal Server (" + Cardinal.VERSION + ")";
	
	private List<CardinalHandler> handlers = new ArrayList<CardinalHandler>();
	
	private HttpServer server;
	
	public APIServer(int port, int backLog) throws IOException {
		server = HttpServer.create(new InetSocketAddress(port), backLog);
	}
	
	public APIServer(int port) throws IOException {
		this(port, DEFAULT_BACKLOG);
	}
	
	public APIServer() throws IOException {
		this(DEFAULT_PORT, DEFAULT_BACKLOG);
	}
	
	public void addHandle(CardinalHandler handler) {
		handlers.add(handler);
		server.createContext(handler.getName(), handler);
	}
	
	public void start() {
		server.start();
	}
	
	public List<CardinalHandler> getHandlers() {
		return handlers;
	}

}
