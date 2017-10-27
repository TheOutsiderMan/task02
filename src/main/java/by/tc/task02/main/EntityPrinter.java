package by.tc.task02.main;

import java.util.List;
import java.util.Map;
import java.util.Set;

import by.tc.task02.entity.Entity;

public class EntityPrinter {

	public static void printEntities(List<Entity> entities) {
		Entity root = entities.get(0);
		printEntity(root, getIndentation(0));
		int countTabs = 1;
		List<Entity> childs = root.getChilds();
		if(childs != null) {
			printTree(childs, countTabs);
		}
	}
	
	private static void printTree(List<Entity> entities, int countTabs) {
		String indentation = getIndentation(countTabs);
		for (Entity entity : entities) {
			printEntity(entity, indentation);
			List<Entity> childs = entity.getChilds();
			if (childs != null) {
				printTree(childs, countTabs + 1);
			}
		}
		
	}

	public static void printEntity(Entity entity, String indentation) {
		StringBuilder builder = new StringBuilder(indentation);
		builder.append(entity.getName());
		builder.append(" ");
		Map<String, String> attributes = entity.getAttributes();
		if (attributes != null) {
			Set<String> keys = attributes.keySet();
			for (String string : keys) {
				builder.append(string);
				builder.append(" - ");
				builder.append(attributes.get(string));
			}
		}
		System.out.println(builder);
		String content = entity.getContent();
		if (content != null) {
			content = content.replaceAll("[\\n]+", "\n" + indentation + "\t");
			System.out.println(indentation + "\t" + content);
		}
	}
	
	private static String getIndentation(int countTabs) {
		if (countTabs > 0) {
			StringBuilder builder = new StringBuilder();
			for(int i = 1; i <= countTabs; i++) {
				builder.append("\t");
			}
			return builder.toString();
		} else {
			return "";
		}
	}
}
