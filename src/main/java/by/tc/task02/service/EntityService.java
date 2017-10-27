package by.tc.task02.service;

import java.util.List;

import by.tc.task02.entity.Entity;

public interface EntityService {
	
	public List<Entity> read() throws ServiceException;
}
