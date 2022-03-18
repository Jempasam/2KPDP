package engine.events;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
* @generated
*/
public class EventRegistry<T extends Event> {
    private boolean listen=true;
    private List<EventListener<T>> listeners;      
    
    public EventRegistry() {
		listeners=new  ArrayList<>();
	}
    
    public void handle(T event) {
        for(EventListener<T> l : listeners) {
        	l.handle(event);
        }
    }
    
    public void handleUntilNotNull(T event, Function<T,?> func) {
        for(EventListener<T> l : listeners) {
        	l.handle(event);
        	if( func.apply(event) != null )break;
        }
    }
    
    public void handleAll(T ... events) {
    	for(EventListener<T> l : listeners) {
        	l.handleAll(events);
        }
	}
    public void handleList(List<T> events) {
    	for(EventListener<T> l : listeners) {
        	l.handleList(events);
        }
	}
    
    public void register(EventListener<T> listener) {
        listeners.add(listener);
    }
    public void listen() {
    	listen=true;
    }
    public void ignore() {
    	listen=false;
    }
    public boolean listening() {
    	return listen;
    }
    
}
