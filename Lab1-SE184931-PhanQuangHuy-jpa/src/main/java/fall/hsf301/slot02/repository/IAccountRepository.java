package fall.hsf301.slot02.repository;

import fall.hsf301.slot02.pojo.Account;

public interface IAccountRepository {
	public Account findByUserName(String userName);

}
