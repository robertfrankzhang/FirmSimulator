package accounts;

public class AccountNode {
	String accountID;
	String parentID;
	AccountType type;
	String accountDescription;
	
	public AccountNode(String accountID,String parentID,AccountType type, String accountDescription) {
		this.accountID = accountID;
		this.parentID = parentID;
		this.type = type;
		this.accountDescription = accountDescription;
	}
	
	public AccountNode(String accountID, AccountType type, String accountDescription) {
		this.accountID = accountID;
		this.parentID = null;
		this.type = type;
		this.accountDescription = accountDescription;
	}
	
	public AccountNode(String accountID,String parentID,AccountType type) {
		this.accountID = accountID;
		this.parentID = parentID;
		this.type = type;
		this.accountDescription = accountID;
	}
	
	public AccountNode(String accountID, AccountType type) {
		this.accountID = accountID;
		this.parentID = null;
		this.type = type;
		this.accountDescription = accountID;
	}

}
