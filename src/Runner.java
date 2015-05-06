import by.gsu.epamlab.beans.Login;
import by.gsu.epamlab.beans.Test;
import by.gsu.epamlab.daoImpl.LoginDaoImpl;
import by.gsu.epamlab.daoImpl.TestDaoImpl;

public class Runner {
    public static void main(String[] args) {
        Login login = new Login(1, "qwer");
        LoginDaoImpl loginDao = new LoginDaoImpl();
        loginDao.create(login);
        System.out.println(loginDao.get(login.getName()));

        Test test = new Test(1, "qwer");
        TestDaoImpl testDao = new TestDaoImpl();
        testDao.create(test);
        System.out.println(testDao.get(test.getName()));
    }
}
