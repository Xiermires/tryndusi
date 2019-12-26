package org.tryndusi.descriptor.yaml;

import java.util.List;

public class Descriptor {

	private String foo = null;
	private String bar = null;
	private String baz = null;
	private List<Item> items = null;

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	public String getBaz() {
		return baz;
	}

	public void setBaz(String baz) {
		this.baz = baz;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bar == null) ? 0 : bar.hashCode());
		result = prime * result + ((baz == null) ? 0 : baz.hashCode());
		result = prime * result + ((foo == null) ? 0 : foo.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Descriptor other = (Descriptor) obj;
		if (bar == null) {
			if (other.bar != null)
				return false;
		} else if (!bar.equals(other.bar))
			return false;
		if (baz == null) {
			if (other.baz != null)
				return false;
		} else if (!baz.equals(other.baz))
			return false;
		if (foo == null) {
			if (other.foo != null)
				return false;
		} else if (!foo.equals(other.foo))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}

	public static class Item {

		private String qux = null;
		private String quux = null;

		public static Item of(String qux, String quux) {
			final Item item = new Item();
			item.setQux(qux);
			item.setQuux(quux);
			return item;
		}

		public String getQux() {
			return qux;
		}

		public void setQux(String qux) {
			this.qux = qux;
		}

		public String getQuux() {
			return quux;
		}

		public void setQuux(String quux) {
			this.quux = quux;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((quux == null) ? 0 : quux.hashCode());
			result = prime * result + ((qux == null) ? 0 : qux.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Item other = (Item) obj;
			if (quux == null) {
				if (other.quux != null)
					return false;
			} else if (!quux.equals(other.quux))
				return false;
			if (qux == null) {
				if (other.qux != null)
					return false;
			} else if (!qux.equals(other.qux))
				return false;
			return true;
		}
	}
}
