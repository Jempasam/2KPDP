package engine.events;

import java.util.List;

public interface EventListener<T extends Event> {
	void handle(T event);
	default void handleAll(T ... events) {
		for(T e : events) {
			handle(e);
		}
	}
	default void handleList(List<T> events) {
		for(T e : events) {
			handle(e);
		}
	}
}
