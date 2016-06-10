package com.epam.spring.core;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger{

	private int cacheSize;
	private List<Event> cache;

	public CacheFileEventLogger(String filename, int cacheSize) {
		super(filename);
		this.cacheSize = cacheSize;
		this.cache = new ArrayList<>(cacheSize);
	}

	private void destroy() {
		this.writeEventsFromCache();
	}
	
	public void logEvent(Event event) {
		cache.add(event);
		
		if(cacheSize <= cache.size()) {
			this.writeEventsFromCache();
			cache.clear();
		}
	}

	private void writeEventsFromCache() {
		for (Event event : cache) {
			super.logEvent(event);
		}
	}

}
