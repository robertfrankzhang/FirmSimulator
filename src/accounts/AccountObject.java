package accounts;

/* The  class represents a single object, not an account within a firm. For accounts where objects inside
are indistinguishable, like cash, an Account will only have a single object. For accounts where
objects are distinguishable, like warehouses, an account can have multiple objects. Objects can be of the type ASSET,
LIABILITY, or EQUITY
 */

public class AccountObject {
	String accountID;
	float value;
	AccountType type;
	
	public AccountObject(String accountID, float value, AccountType type) {
		this.accountID = accountID;
		this.value = value;
		this.type = type;
	}
}
