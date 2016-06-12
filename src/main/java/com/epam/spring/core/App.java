package com.epam.spring.core;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	private static ConfigurableApplicationContext ctx;

	@Autowired	// by type Client 
	private Client client;

	private Map<EventType, EventLogger> loggers;

	@Autowired	// by bean's name
	@Qualifier("defaultEventLogger")
	private EventLogger defaultEventLogger;

	public App(
//			Client client, 
//			EventLogger defaultEventLogger,
			Map<EventType, EventLogger> loggers) {
//		this.client = client;
//		this.defaultEventLogger = defaultEventLogger;
		this.loggers = loggers;
	}

	private void logEvent(EventType type, String msg) {

		Event event = (Event) ctx.getBean("event");
		String message = msg.replaceAll(client.getId(), client.getFullName());
		event.setMsg(message);

		EventLogger logger = loggers.get(type);
		if (null == logger) {
			logger = defaultEventLogger;
		}

		logger.logEvent(event);
	}

	public static void main(String[] args) {

		ctx = new ClassPathXmlApplicationContext("spring.xml");
//		ctx = new ClassPathXmlApplicationContext("spring.xml", parent_ctx);
		App app = (App) ctx.getBean("app");

		app.logEvent(EventType.ERROR, "ERROR-severity event for user 1");
		app.logEvent(EventType.INFO, "INFO-severity event for user 2");
		app.logEvent(null, "NULL-severity event for user 3");

		ctx.close();
	}
}
