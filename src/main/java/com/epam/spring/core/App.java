package com.epam.spring.core;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	private Client client;
	private EventLogger eventLogger;
	private static ConfigurableApplicationContext ctx;

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

		ctx = new ClassPathXmlApplicationContext("spring.xml");
		App app = (App) ctx.getBean("app");

		app.logEvent("Some event for user 1");
		Utils.pause(1000);
		app.logEvent("Some event for user 2");
		Utils.pause(1000);
		app.logEvent("Some event for user 3");
		Utils.pause(1000);
		app.logEvent("Some event for user 4");
		Utils.pause(1000);
		app.logEvent("Some event for user 5");
		Utils.pause(1000);
		app.logEvent("Some event for user 6");
		Utils.pause(1000);
		app.logEvent("Some event for user 7");
		
		ctx.close();
	}
}
