package accounts;

public class LedgerEntry {
	String debitAccountID;
	String creditAccountID;
	float amount;
	float timestamp;
	
	public LedgerEntry(String debitAccountID, String creditAccountID, float amount, float timestamp) {
		this.debitAccountID = debitAccountID;
		this.creditAccountID = creditAccountID;
		this.amount = amount;
		this.timestamp = timestamp;
	}
}
