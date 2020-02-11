package firm;

/*
 * Transaction represents a single transaction on the accounting ledger + some metadata
 */
public class Transaction {
	String debitAccountID;
	float debitAmount;
	String creditAccountID;
	float creditAmount;
	
	public Transaction(String debitAccountID, float debitAmount, String creditAccountID, float creditAmount) {
		this.debitAccountID = debitAccountID;
		this.debitAmount = debitAmount;
		this.creditAccountID = creditAccountID;
		this.creditAmount = creditAmount;
	}
}
