package net.argus.web.http.api;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import net.argus.system.UserSystem;
import net.argus.web.http.APIHandler;

public class APIExitHandler extends APIHandler {

	public APIExitHandler() {
		super("exit");
	}

	@Override
	public void doGet(HttpExchange exchange) throws IOException {
		sendEmptyPackage(exchange);
		UserSystem.exit(0);
	}

	@Override
	public void doPost(HttpExchange exchange) throws IOException {
		doGet(exchange);
	}

}
