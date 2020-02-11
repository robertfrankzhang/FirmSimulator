package accounts;

import java.util.ArrayList;

public class BaseAccount extends NodeAccount {
	
	ArrayList<AccountObject> accountObjects = new ArrayList<AccountObject>();

	public BaseAccount(String nodeAccountID, AccountType type,String accountDescription,NodeAccount parentNodeAccount) {
		super(nodeAccountID, type,accountDescription,parentNodeAccount);
	}
	
	@Override
	public float getAccountValue() {
		float totalValue = 0;
		for (AccountObject account:this.accountObjects) {
			totalValue+=account.value;
		}
		return totalValue;
	}

	@Override
	public void addAccount(String nodeAccountID,String accountDescription,boolean isBaseAccount) {
		System.out.println("Account child can't be added to BaseAccount subtype");
	}
}
