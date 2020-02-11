package firm;

import accounts.*;

public class Firm {
	NodeAccount assets = new NodeAccount("Assets",AccountType.ASSET,"All assets of firm");
	NodeAccount liabilities = new NodeAccount("Liabilities",AccountType.LIABILITY,"All liabilities of firm");
	NodeAccount equities = new NodeAccount("Equities",AccountType.EQUITY,"All equities of firm");
	
	String firmID;
	
	public Firm(String firmID) {
		this.firmID = firmID;
	}
	
	public void setUpDefaultAccounts() {
		this.assets.addAccount("Cash", "Total Cash", true);
		this.assets.addAccount("A/R", "Total Accounts Receivable", true);
		this.assets.addAccount("Prepaid Expenses", "Total Prepaid Expenses", true);
		this.assets.addAccount("PP&E", "Fixed Assets", false);
		this.assets.addAccount("Inventory", "Intermediate and Finished Goods of Firm", false);
		this.assets.addAccount("Supplies", "Immediately Expensed Firm Capital", false);
		this.assets.addAccount("Intangible Assets", "Intangible Assets", false);
		
		
	}
	
	public void runTimestep() {
		
	}
}
