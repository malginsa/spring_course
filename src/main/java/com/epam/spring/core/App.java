package com.epam.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	private Client client;
	private EventLogger eventLogger;
	private static ApplicationContext ctx;

	public App(Client client, EventLogger eventLogger) {
		this.client = client;
		this.eventLogger = eventLogger;
	}
	
	private void logEvent(String msg) {
		Event event = (Event) ctx.getBean("event");
		String message = msg.replaceAll(client.getId(), client.getFullName());
		event.setMsg(message);
		eventLogger.logEvent(event);
	}
	
	public static void main(String[] args) {

//		App app = new App();
//		app.client = new Client("1", "John Smith");
//		app.eventLogger = new ConsoleEventLogger();

		ctx = new ClassPathXmlApplicationContext("spring.xml");
		App app = (App) ctx.getBean("app");
		
		app.logEvent("Some event for user 1");
		Utils.pause(2000);
		app.logEvent("Some event for user 2");
	}
}
