package firm;

import accounts.*;

public class Firm {
	
	String firmID;
	AccountTabulator chartOfAccounts = new AccountTabulator();
	
	public Firm(String firmID) {
		this.firmID = firmID;
	}
	
	public void runTimestep() {
		
	}
}
