package accounts;
import java.util.HashMap;
import java.util.Map;

/*
 * AccountTabulator keeps track of all of the firm's accounting, keeping an updated ledger, as well
 * as categorical trees of all accounts and their numeric values
 */

public class AccountTabulator {
	HashMap<String, AccountNode> accountsMap = new HashMap<String,AccountNode>();
	Ledger ledger = new Ledger();
	
	public AccountTabulator() {
		this.setupDefaultAccounts();
	}
	
	private void setupDefaultAccounts() {
		this.addAccount("Assets", AccountType.ASSET);
		this.addAccount("Liabilities", AccountType.LIABILITY);
		this.addAccount("Equities", AccountType.EQUITY);
		
		this.addAccount("Cash", "Assets", true);
		this.addAccount("A/R", "Assets", "Accounts Receivable", true);
		this.addAccount("PP&E","Assets","Fixed Assets",false);
		this.addAccount("Property","PP&E",true);
		this.addAccount("Plant","PP&E","Buildings",true);
		this.addAccount("Equipment","PP&E","Machinery and other equipment",true);
		this.addAccount("PPExpenses","Assets","Prepaid Expenses",true);
		this.addAccount("Inventory","Assets","All Firm Intermediate or Finished Goods",true);
		
		this.addAccount("U/R", "Liabilities", "Unearned Revenue", true);
		this.addAccount("W/P", "Liabilities", "Wages Payable", true);
		this.addAccount("N/P", "Liabilities", "Notes Payable", true);
		this.addAccount("I/P", "Liabilities", "Interest Payable", true);
		this.addAccount("A/P", "Liabilities", "Accounts Payable", true);
		
		this.addAccount("Common Stock", "Equities", true);
		this.addAccount("Preferred Stock", "Equities", true);
		this.addAccount("APIC", "Equities", "Additional Paid In Capital", true);
		this.addAccount("Treasury Stock", "Equities", true);
		this.addAccount("Retained Earnings", "Equities", true);
		this.addAccount("Revenue", "Equities", true);
		this.addAccount("Expenses", "Equities", false);
		this.addAccount("Operating Expenses", "Expenses", true);
		this.addAccount("COGS", "Expenses", "Cost of Goods Sold",true);
	}
	
	/*
	 * The below four addAccount methods are public, as it allows the user to overwrite default accounts
	 * and also add their own. Should account for replacing a node with a base (only works when all accounts
	 * are still 0), where all children of old base are removed.
	 */
	public void addAccount(String accountID, String parentAccountID, String accountDescription, boolean isBase) {
		this.accountForNodeToBase(accountID, isBase);
		AccountType type = accountsMap.get(accountID).type;
		if (isBase) {
			this.accountsMap.put(accountID, new AccountObject(accountID,parentAccountID,type,accountDescription));
		}else {
			this.accountsMap.put(accountID, new AccountNode(accountID,parentAccountID,type,accountDescription));
		}
	}
	
	public void addAccount(String accountID, String accountDescription, AccountType type) {
		this.accountsMap.put(accountID, new AccountNode(accountID,null,type,accountDescription));
	}
	
	public void addAccount(String accountID, String parentAccountID, boolean isBase) {
		this.accountForNodeToBase(accountID, isBase);
		AccountType type = accountsMap.get(accountID).type;
		if (isBase) {
			this.accountsMap.put(accountID, new AccountObject(accountID,parentAccountID,type,accountID));
		}else {
			this.accountsMap.put(accountID, new AccountNode(accountID,parentAccountID,type,accountID));
		}
	}
	
	public void addAccount(String accountID, AccountType type) {
		this.accountsMap.put(accountID, new AccountNode(accountID,null,type,accountID));
	}
	
	private void accountForNodeToBase(String accountID,boolean isBase) {
		boolean turnNodeToBase = false;
		if (this.accountsMap.containsKey(accountID)) {
			try {
				AccountObject o = (AccountObject)this.accountsMap.get(accountID);
			}catch(Exception e){
				//Is AccountNode
				if (isBase) {
					turnNodeToBase = true;
				}
			}
		}
		if (turnNodeToBase) {
			this.generalRemovalOfChilden(accountID);
		}
	}
	
	private void generalRemovalOfChilden(String accountID) {
		for (Map.Entry<String, AccountNode> entry: this.accountsMap.entrySet()) {
			AccountObject entryNew;
			try {
				entryNew = (AccountObject)entry.getValue();
				if (isAncestorAccount(entry.getKey(),accountID)) {
					this.accountsMap.remove(entry.getKey());
				}
			}catch(Exception e){
				continue;
			}
			
		}
	}
	
	/*
	 * Numeric Methods
	 */
	
	public float getAccountValue(String accountID) {
		float totalValue = 0;
		for (Map.Entry<String, AccountNode> entry: this.accountsMap.entrySet()) {
			AccountObject entryNew;
			try {
				entryNew = (AccountObject)entry.getValue();
				if (isAncestorAccount(entry.getKey(),accountID)) {
					totalValue += entryNew.value;
				}
			}catch(Exception e){
				continue;
			}
			
		}
		return totalValue;
	}
	
	private boolean isAncestorAccount(String accountID, String potentialAncestorID) {
		if (potentialAncestorID.equals(accountID)) {
			return true;
		}
		
		String parentID = null;
		do {//Move up tree
			parentID = this.accountsMap.get(accountID).parentID;
			if (potentialAncestorID.equals(parentID)) {
				return true;
			}
			accountID = parentID;
		}while (parentID != null);
		return false;
	}
	
	/*
	 * Ledger Methods
	 */
	
	public void addLedgerEntry(String debitAccountID, String creditAccountID, float amount, float timestamp) {
		
		//Update Account Values
		AccountNode debitAccount = this.accountsMap.get(debitAccountID);
		AccountNode creditAccount = this.accountsMap.get(creditAccountID);
		AccountObject debitObject;
		AccountObject creditObject;
		try {
			debitObject = (AccountObject)debitAccount;
			creditObject = (AccountObject)creditAccount;
			if (debitObject.type.equals(AccountType.ASSET)) {
				debitObject.value += amount;
			}else {
				debitObject.value -= amount;
			}
			
			if (creditObject.type.equals(AccountType.ASSET)) {
				creditObject.value -= amount;
			}else {
				creditObject.value += amount;
			}
			
			this.accountsMap.put(debitAccountID, debitObject);
			this.accountsMap.put(creditAccountID, creditObject);
		}catch(Exception e) {
			System.out.println("Accounts must be base accounts to be ledgered");
			return;
		}
		
		//Update Ledger
		this.ledger.addEntry(debitAccountID, creditAccountID, amount, timestamp);
	}
	
}
