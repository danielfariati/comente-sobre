package com.danielfariati.comente_sobre.functional;

import java.net.ServerSocket;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.Selenium;

public class SeleniumHelper {

	public static SeleniumServer server;

	private SeleniumHelper() {
	}

	public static void startSeleniumServer() throws Exception {
		ServerSocket serverSocket = new ServerSocket(RemoteControlConfiguration.DEFAULT_PORT);
		serverSocket.close();

		RemoteControlConfiguration rcc = new RemoteControlConfiguration();
		rcc.setPort(RemoteControlConfiguration.DEFAULT_PORT);

		server = new SeleniumServer(false, rcc);
		server.start();
	}

	public static void stopSeleniumServer(Selenium selenium) {
		selenium.stop();

		if (server != null) {
			selenium.shutDownSeleniumServer();
			server.stop();

			server = null;
		}
	}

}
