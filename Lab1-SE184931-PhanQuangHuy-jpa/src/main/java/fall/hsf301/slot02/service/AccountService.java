package fall.hsf301.slot02.service;

import fall.hsf301.slot02.pojo.Account;
import fall.hsf301.slot02.repository.AccountRepository;
import fall.hsf301.slot02.repository.IAccountRepository;

public class AccountService implements IAccountService {
	private IAccountRepository iAccountRepo =null;
	public AccountService(String fileName) {
		iAccountRepo = new AccountRepository(fileName);
	}

	@Override
	public Account findByUserName(String userName) {
		// TODO Auto-generated method stub
		return iAccountRepo.findByUserName(userName);
	}

}
