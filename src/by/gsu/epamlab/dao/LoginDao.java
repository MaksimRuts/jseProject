package by.gsu.epamlab.dao;

import by.gsu.epamlab.beans.Login;

public interface LoginDao {
    public int create(Login login);

    public Login get(String name);
}
