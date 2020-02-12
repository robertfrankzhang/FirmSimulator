package accounts;

import java.util.ArrayList;

public class Ledger {
	
	ArrayList<LedgerEntry> entries = new ArrayList<LedgerEntry>();

	public Ledger() {
		
	}
	
	public void addEntry(String debitAccountID, String creditAccountID, float amount, float timestamp) {
		LedgerEntry entry = new LedgerEntry(debitAccountID, creditAccountID, amount, timestamp);
		entries.add(entry);
	}
}
