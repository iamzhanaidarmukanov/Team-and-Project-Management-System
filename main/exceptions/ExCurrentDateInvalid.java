package main.exceptions;

public class ExCurrentDateInvalid extends Exception {
    private static final long serialVersionUID = 1L;

public ExCurrentDateInvalid() {
    super("The earliest start day is tomorrow.");
}
public ExCurrentDateInvalid(String message) { super(message); }
}

