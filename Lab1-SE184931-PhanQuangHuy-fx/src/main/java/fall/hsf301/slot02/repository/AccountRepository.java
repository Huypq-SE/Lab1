package fall.hsf301.slot02.repository;

import fall.hsf301.slot02.dao.AccountDAO;
import fall.hsf301.slot02.pojo.Account;

public class AccountRepository implements IAccountRepository {
	private AccountDAO accountDAO = null;
	public AccountRepository(String fileConfig) {
		accountDAO = new AccountDAO(fileConfig);
	}
	@Override
	public Account findByUserName(String userName) {
		// TODO Auto-generated method stub
		return accountDAO.findByUserName(userName);
	}

}
