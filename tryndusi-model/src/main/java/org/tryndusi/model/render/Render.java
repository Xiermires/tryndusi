package org.tryndusi.model.render;

public interface Render<T> {

	public byte[] draw(T drawable);
}
