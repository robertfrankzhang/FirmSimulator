package accounts;

public class AccountObject extends AccountNode{
	float value;
	
	public AccountObject(String accountID, String parentID, AccountType type, String accountDescription) {
		super(accountID,parentID,type,accountDescription);
		this.value = 0;
	}
	
	public AccountObject(String accountID, String parentID, AccountType type) {
		super(accountID,parentID,type,accountID);
		this.value = 0;
	}
}
