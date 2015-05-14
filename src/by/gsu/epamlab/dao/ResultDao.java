package by.gsu.epamlab.dao;

import by.gsu.epamlab.beans.Result;

import java.util.List;

public interface ResultDao {
    public void create(Result result);

    public List<Result> get();

    public List<Result> getCurrentMonth();
}
