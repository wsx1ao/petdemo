package service;
import domain.Account;
import persistence.AccountDao;
import persistence.impl.AccountDaoImpl;
public class AccountService {
    private AccountDao accountDao;
    public  AccountService(){
        accountDao=new AccountDaoImpl();
    }
    public Account getAccountByUsername(String username) {
        return accountDao.getAccountByUsername(username);
    }
    public Account getAccountByUsernameAndPassword(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountDao.getAccountByUsernameAndPassword(account);
    }
    public void insertAccount(Account account) {
        accountDao.insertAccount(account);
        accountDao.insertProfile(account);
        accountDao.insertSignon(account);
    }
     public void updateAccount(Account account) {
        accountDao.updateAccount(account);
        accountDao.updateProfile(account);
        if (account.getPassword() != null && account.getPassword().length() > 0) {
            accountDao.updateSignon(account);
        }
    }
    public Account getAccount(String username) {
        return accountDao.getAccountByUsername(username);
    }
    public  boolean usernameIsExist(String username){
        return accountDao.usernameIsExist(username);
    }
}
