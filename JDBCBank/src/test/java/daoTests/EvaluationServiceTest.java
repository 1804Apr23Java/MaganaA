package daoTests;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dao.BankAccountDaoImpl;
import dao.UserInfoDaoImpl;

public class EvaluationServiceTest {
    
    private static final UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
    private static final BankAccountDaoImpl bankInfoDao = new BankAccountDaoImpl();
    //private static final Console evaluationService = new Console();
    @Test
    public void userInputEqualsNull() {
        assertFalse(userInfoDao.checkIfUserExists(""));
    }
    @Test
    public void userInputIsNotFound() {
        assertFalse(userInfoDao.checkIfUserExists("dave"));
    }
    @Test
    public void userInputIsFound() {
        assertTrue(userInfoDao.checkIfUserExists("AND"));
    }
    @Test
    public void addedBlankUsername() {
        assertFalse(userInfoDao.loginUser("", "pass123"));
    }
    @Test
    public void addedBlankPassword() {
        assertFalse(userInfoDao.loginUser("user123", ""));
    }
    @Test
    public void addedBadLogin() {
        assertFalse(userInfoDao.loginUser("Dave", "Brewer"));
    }
    @Test
    public void addedGoodLogin() {
        assertTrue(userInfoDao.loginUser("AND", "AND123"));
    }
    @Test
    public void addedEmpty() {
    	assertFalse(bankInfoDao.checkEmpty(1));
    }
}
