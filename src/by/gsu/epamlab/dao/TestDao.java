package by.gsu.epamlab.dao;

import by.gsu.epamlab.beans.Test;

public interface TestDao {
    public int create(Test test);
    public Test get(int id);
}
