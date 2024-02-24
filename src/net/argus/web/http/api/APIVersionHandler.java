package net.argus.web.http.api;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import net.argus.system.UserSystem;
import net.argus.web.http.APIHandler;
import net.argus.web.http.MainAPI;
import net.argus.web.http.pack.PackagePrefab;


public class APIVersionHandler extends APIHandler {

	public APIVersionHandler() {
		super("version");
	}

	@Override
	public void doGet(HttpExchange exchange) throws IOException {
		String version = MainAPI.VERSION.toString();
		if(UserSystem.getManifest() != null)
			version = UserSystem.getCurrentVersion().toString() + "." + UserSystem.getCurrentDebug().toString();
		
		send(exchange, PackagePrefab.getVersionPackage(version));

	}

	@Override
	public void doPost(HttpExchange exchange) throws IOException {
		doGet(exchange);
	}
	
}
