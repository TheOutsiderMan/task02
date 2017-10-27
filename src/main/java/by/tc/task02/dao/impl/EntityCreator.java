package by.tc.task02.dao.impl;

import java.util.Map;

import by.tc.task02.entity.Entity;

public final class EntityCreator {
	
	public Entity createEntityWithContent(String name, Entity parent, String content) {
		Entity entity = this.createEntity(name, parent);
		entity.setContent(content);
		return entity;
	}

	public Entity createEntityWithAttrsAndContent(String name, Entity parent, Map<String, String> attributes, String content) {
		Entity entity = this.createEntityWithAttr(name, parent, attributes);
		entity.setContent(content);
		return entity;
	}

	public Entity createEntityWithAttr(String name, Entity parent, Map<String, String> attributes) {
		Entity entity = this.createEntity(name, parent);
		entity.setAttributes(attributes);
		return entity;
	}

	public Entity createEntity(String name, Entity parent) {
		Entity entity = new Entity();
		entity.setName(name);
		if (parent != null) {
			entity.setParent(parent);
			parent.addChild(entity);
		}
		return entity;
	}
	
}
