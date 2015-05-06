package by.gsu.epamlab.beans;

public class Login {
    private int loginId;
    private String name;

    public Login() {
    }

    public Login(int loginId, String name) {
        this.loginId = loginId;
        this.name = name;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
