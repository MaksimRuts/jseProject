package by.gsu.epamlab.beans;

public class Login {
    private int loginId;
    private String login;

    public Login() {
    }

    public Login(int loginId, String login) {
        this.loginId = loginId;
        this.login = login;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return login;
    }
}
