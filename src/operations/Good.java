package operations;

public class Good {
	float bookValue;
	String accountID;
	String goodID;
	String processID; //Can mean the process the good is currently in, or the process it needs to go to (if shouldSwitchProcess is true)
	boolean shouldSwitchProcess;
	float timeLeftUntilSwitchProcess;
	
	/*
	 * initialBookValue: Initial Book Value of Good
	 * accountID: account the good belongs to
	 * goodID: goodID
	 * processID: process the good is initialized in
	 * countDown: units of time left until good finishes process
	 */
	public Good(float initialBookValue, String accountID, String goodID, String processID, float countDown) {
		this.bookValue = initialBookValue;
		this.accountID = accountID;
		this.goodID = goodID;
		this.processID = processID;
		this.timeLeftUntilSwitchProcess = countDown;
		this.shouldSwitchProcess = false;
	}
}
