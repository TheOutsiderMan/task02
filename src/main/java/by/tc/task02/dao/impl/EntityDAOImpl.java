package by.tc.task02.dao.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.tc.task02.dao.DAOException;
import by.tc.task02.dao.EntityDAO;
import by.tc.task02.entity.Entity;

public class EntityDAOImpl implements EntityDAO{

	private static final String FILE_NAME = "src\\main\\resources\\task02.xml";

	@Override
	public List<Entity> read() throws DAOException {

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		Entity currentParent = null;
		List <Entity> entities = new ArrayList<Entity>();
		try {
			fileReader = new FileReader(FILE_NAME);
			bufferedReader = new BufferedReader(fileReader);
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				Entity entity = null;
				if (isAptLine(line)) {
					LineParser parser = new LineParser();
					entity = parser.parseLine(line, currentParent);
					currentParent = parser.getCurrentParent();
				} else {
					continue;
				}
				if (entity != null) {
					entities.add(entity);
				}
			}
		} catch (FileNotFoundException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					throw new DAOException(e);
				}
			}
		}
		return entities;
	}

	private static boolean isAptLine(String line) {
		if (line.matches("^<[\\?!].+")) {
			return false;
		} else {
			return true;
		}
	}
}
