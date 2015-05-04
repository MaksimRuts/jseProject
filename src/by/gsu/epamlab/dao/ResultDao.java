package by.gsu.epamlab.dao;

import by.gsu.epamlab.beans.Result;

public interface ResultDao {
    public int create(Result result);
    public Result get(int id);
}
