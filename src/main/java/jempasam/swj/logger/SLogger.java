package jempasam.swj.logger;

public interface SLogger {
	void log(String message);
	void enter(String name);
	void exit();
}
