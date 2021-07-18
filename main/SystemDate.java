package main;

public class SystemDate extends Day {

	public static SystemDate instance;

	private SystemDate(String sDay) {
		super(sDay);
	}

	public static SystemDate getInstance() {
		return instance;
	}

	public static void createTheInstance(String sDay) {
		if (instance == null)
			instance = new SystemDate(sDay);
		else
			System.out.println("Cannot create one more system date instance.");
	}

	// to avoid creating one more object of the SystemDate class
	@Override
	public Day clone() {
		Day d = new Day(this.toString());
		return d;
	}
}
