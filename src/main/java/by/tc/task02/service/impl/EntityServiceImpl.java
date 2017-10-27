package by.tc.task02.service.impl;

import java.util.List;

import by.tc.task02.dao.DAOException;
import by.tc.task02.dao.DAOFactory;
import by.tc.task02.dao.EntityDAO;
import by.tc.task02.entity.Entity;
import by.tc.task02.service.EntityService;
import by.tc.task02.service.ServiceException;

public class EntityServiceImpl  implements EntityService{

	@Override
	public List<Entity> read() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		EntityDAO entityDAO = daoFactory.getEntityDAO();
		
		List<Entity> entities;
		try {
			entities = entityDAO.read();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return entities;
	}
	
}
