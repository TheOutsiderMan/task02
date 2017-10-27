package by.tc.task02.dao.impl;

import java.util.HashMap;
import java.util.Map;

import by.tc.task02.entity.Entity;

public class LineParser {
	private Entity currentParent;
	
	private final static String NAME_ATTRIBUTES = "^<(.+)( +(.+)=[\"'](.+)[\"'])+>$";
	private final static String NAME_ATTRIBUTES_CONTENT_CLOSED = "^<(.+)( +(.+)=[\"'](.+)[\"'])+>.+</.+>$";
	private final static String NAME_CONTENT_CLOSED = "^(<.+>)(.+)(</.+>)$";
	private final static String CLOSED_TAG = "^</(.+)>";
	private final static String NAME = "^<.+>$";
	private final static String CONTENT = "^[^<]";
	
	public Entity parseLine(String line, Entity currentParent) {
		Entity entity = null;
		if (line.matches(NAME_ATTRIBUTES)) {
			entity = parseEntityWithAttrs(line, currentParent);
			currentParent = entity;
		} else if (line.matches(NAME_ATTRIBUTES_CONTENT_CLOSED)) {
			entity = parseEntityWithAttrsAndContent(line, currentParent);
		} else if (line.matches(NAME_CONTENT_CLOSED)) {
			entity = parseEntityWithContent(line, currentParent);
		} else if (line.matches(CLOSED_TAG)) {
			currentParent = currentParent.getParent();
		} else if (line.matches(NAME)) {
			entity = parseEntity(line, currentParent);
			currentParent = entity;
		} else if (line.matches(CONTENT)) {
			String currentParentContent = currentParent.getContent();
			currentParent.setContent(currentParentContent + " " + line);
		}
		this.currentParent = currentParent;
		return entity;
	}

	public Entity getCurrentParent() {
		return currentParent;
	}

	public void setCurrentParent(Entity currentParent) {
		this.currentParent = currentParent;
	}
	
	private static Entity parseEntityWithAttrs(String line, Entity parent) {
		EntityCreator creator = new EntityCreator();
		line = line.substring(1, line.length() - 1);
		String[] splittedLine = line.split("[ =\"\']+");
		Map<String, String> attributes = new HashMap<String, String>();
		for (int i = 1; i < splittedLine.length; i += 2) {
			attributes.put(splittedLine[i], splittedLine[i + 1]);
		}
		return creator.createEntityWithAttr(splittedLine[0], parent, attributes);
	}
	
	private static Entity parseEntityWithAttrsAndContent(String line, Entity parent) {
		EntityCreator creator = new EntityCreator();
		line = line.substring(1, line.length() - 1);
		int beginContent = line.indexOf(">") + 1;
		int endContent = line.indexOf("<");
		String content = line.substring(beginContent, endContent);
		String nameAndAttrs = line.substring(0, beginContent - 1);
		String[] splittedLine = nameAndAttrs.split("[ =\"\']+");
		Map<String, String> attributes = new HashMap<String, String>();
		for (int i = 1; i < splittedLine.length; i += 2) {
			attributes.put(splittedLine[i], splittedLine[i + 1]);
		}
		return creator.createEntityWithAttrsAndContent(splittedLine[0], parent, attributes, content);
	}
	
	private static Entity parseEntityWithContent(String line, Entity parent) {
		EntityCreator creator = new EntityCreator();
		line = line.substring(1, line.length() - 1);
		int beginContent = line.indexOf(">") + 1;
		int endContent = line.indexOf("<");
		String content = line.substring(beginContent, endContent);
		String name = line.substring(0, beginContent - 1);
		return creator.createEntityWithContent(name, parent, content);		
	}
	
	private static Entity parseEntity(String line, Entity parent) {
		EntityCreator creator = new EntityCreator();
		String name = line.substring(1, line.length() - 1);
		return creator.createEntity(name, parent);
	}
}
