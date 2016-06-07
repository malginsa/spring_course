package com.epam.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	private Client client;
	private EventLogger eventLogger;

	public App(Client client, EventLogger eventLogger) {
		this.client = client;
		this.eventLogger = eventLogger;
	}
	
	private void logEvent(String msg) {
		String message = msg.replaceAll(client.getId(), client.getFullName());
		eventLogger.logEvent(message);
	}
	
	public static void main(String[] args) {

//		App app = new App();
//		app.client = new Client("1", "John Smith");
//		app.eventLogger = new ConsoleEventLogger();

		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		App app = (App) ctx.getBean("app");
		
		app.logEvent("Some event for user 1");
		app.logEvent("Some event for user 2");
	}
}
