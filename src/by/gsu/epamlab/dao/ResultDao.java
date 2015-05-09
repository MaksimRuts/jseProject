package by.gsu.epamlab.dao;

import by.gsu.epamlab.beans.AbstractResult;

import java.util.List;

public interface ResultDao {
    public void create(AbstractResult result);

    public List<AbstractResult> get();

    public List<AbstractResult> getCurrentMonth();
}
