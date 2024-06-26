package net.argus.web.http;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import net.argus.web.http.pack.APIPackage;
import net.argus.web.http.pack.PackagePrefab;


public abstract class CardinalHandler implements HttpHandler {
	
	private CardinalFileNameMap fileNameMap = new CardinalFileNameMap();
	
	private String name;
	private boolean restrictive;
	
	private int numberOfUse;
	private int numberOfUseGET;
	private int numberOfUsePOST;
	
	public CardinalHandler(String name, boolean restrictive) {
		if(!name.startsWith("/"))
			name = "/" + name;
		
		this.name = name;
		
		this.restrictive = restrictive;
	}
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		exchange.getResponseHeaders().add("Server", APIServer.NAME);
		exchange.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");

		numberOfUse++;
		if(restrictive && exchange.getRequestURI().getPath().toLowerCase().equals(getName().toLowerCase()))
			start(exchange);
		else if(!restrictive)
			start(exchange);
		else
			send404(exchange);
	}
	
	private void start(HttpExchange exchange) throws IOException {
		switch(exchange.getRequestMethod()) {
			case "GET":
				numberOfUseGET++;
				doGet(exchange);
				break;
			case "POST":
				numberOfUsePOST++;
				doPost(exchange);
				break;
		}
	}
	
	public abstract void doGet(HttpExchange exchange) throws IOException;
	public abstract void doPost(HttpExchange exchange) throws IOException;
	
	public void send(HttpExchange exchange, APIPackage pack) throws IOException {
		exchange.sendResponseHeaders(pack.getHttpCode(), pack.length());
		exchange.getResponseBody().write(pack.getByte());
		exchange.close();
	}
	
	public void send404(HttpExchange exchange) throws IOException {
		send(exchange, PackagePrefab.get404Package());
	}
	
	public void send405(HttpExchange exchange) throws IOException {
		send(exchange, PackagePrefab.get405Package());
	}
	
	public void send500(HttpExchange exchange) throws IOException {
		send(exchange, PackagePrefab.get500Package());
	}
		
	public String getContentType(String fileName) {
		return fileNameMap.getContentTypeFor(fileName);
	}
	
	public CardinalFileNameMap getFileNameMap() {
		return fileNameMap;
	}
	
	public String getName() {
		return name;
	}

	public int getNumberOfUse() {
		return numberOfUse;
	}
	
	public int getNumberOfUseGET() {
		return numberOfUseGET;
	}
	
	public int getNumberOfUsePOST() {
		return numberOfUsePOST;
	}

}
