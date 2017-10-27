package by.tc.task02.main;

import java.util.List;

import by.tc.task02.entity.Entity;
import by.tc.task02.service.EntityService;
import by.tc.task02.service.ServiceException;
import by.tc.task02.service.ServiceFactory;

public class Main {

	public static void main(String[] args) {
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		EntityService entityService = serviceFactory.getEntityService();
		
		try {
			List<Entity> entities = entityService.read();
			EntityPrinter.printEntities(entities);
		} catch (ServiceException e) {
			System.err.println(e);;
		}
	}

}
