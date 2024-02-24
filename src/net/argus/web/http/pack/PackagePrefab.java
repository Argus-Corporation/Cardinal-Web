package net.argus.web.http.pack;

import java.net.HttpURLConnection;

import net.argus.cjson.value.CJSONObject;
import net.argus.cjson.value.CJSONString;

public class PackagePrefab {
	
	public static APIPackage get404Package() {
		return PackageBuilder.getErrorPackage("not found", "404 Not Found", HttpURLConnection.HTTP_NOT_FOUND);
	}
	
	public static APIPackage get405Package() {
		return PackageBuilder.getErrorPackage("Method Not Allowed", "405 method not allowed", HttpURLConnection.HTTP_BAD_METHOD);
	}
	
	public static APIPackage get418Package() {
		return PackageBuilder.getErrorPackage("I'm a teapot", "418 I'm a teapot", 418);
	}
	
	public static APIPackage get451Package() {
		return PackageBuilder.getErrorPackage("Unavailable For Legal Reasons", "451 Unavailable For Legal Reasons", 451);
	}
	
	public static APIPackage get500Package() {
		return PackageBuilder.getErrorPackage("Internal Server Error", "500 internal server error", HttpURLConnection.HTTP_INTERNAL_ERROR);
	}
	
	public static APIPackage getEmptyPackage() {
		return PackageBuilder.getSucessPackage(new CJSONObject());
	}
	
	public static APIPackage getVersionPackage(String version) {
		CJSONObject obj = new CJSONObject();
		
		obj.addItem("version", new CJSONString(version));
		return PackageBuilder.getSucessPackage(obj);
	}

}
