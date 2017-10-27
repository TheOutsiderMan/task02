package by.tc.task02.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Entity {
	
	private String name;
	private Map<String, String> attributes;
	private String content;
	private Entity parent;
	private List<Entity> childs;

	public Entity() {

	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	public Entity getParent() {
		return parent;
	}
	public void setParent(Entity parent) {
		this.parent = parent;
	}
	public List<Entity> getChilds() {
		return childs;
	}
	public void setChilds(List<Entity> childs) {
		this.childs = childs;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void addChild(Entity child) {
		List<Entity> currentParentChilds = this.getChilds();
		if (currentParentChilds != null) {
			currentParentChilds.add(child);
		} else {
			currentParentChilds =  new ArrayList<Entity>();
			currentParentChilds.add(child);
		}
		this.setChilds(currentParentChilds);
	}
	
}
