package to.networld.scrawler.foaf;

/**
 * @author Alex Oberhauser
 *
 */
public class Account {
	private String accountServiceHomepage = null;
	private String accountProfilePage = null;
	private String accountName = null;

	public synchronized void setAccountServiceHomepage(String _accountServiceHomepage) {
		this.accountServiceHomepage = _accountServiceHomepage;
	}

	public synchronized void setAccountProfilePage(String _accountProfilePage) {
		this.accountProfilePage = _accountProfilePage;
	}

	public synchronized void setAccountName(String _accountName) {
		this.accountName = _accountName;
	}
	
	public synchronized String getAccountServiceHomepage() { return this.accountServiceHomepage; }
	
	public synchronized String getAccountProfilePage() { return this.accountProfilePage; }
	
	public synchronized String getAccountName() { return this.accountName; }
}