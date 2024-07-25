package main.java.dtdu.util;

import java.io.IOException;

public class EndOfStreamException extends IOException {
	private static final long serialVersionUID = -6163893736364950624L;
	public EndOfStreamException() {
		super("End of Stream reached");
	}
}