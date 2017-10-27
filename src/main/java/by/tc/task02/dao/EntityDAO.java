package by.tc.task02.dao;

import java.util.List;

import by.tc.task02.entity.Entity;

public interface EntityDAO {

	public List<Entity> read() throws DAOException;
}
