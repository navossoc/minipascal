package jamil;

public class SimpleNode2 extends SimpleNode {

	public SimpleNode2(String value) {
		super(0);
		this.value = value;
	}

	public SimpleNode2(int i) {
		super(i);
	}

	public SimpleNode2(LMP p, int i) {
		super(p, i);
	}

	@Override
	public void dump(String prefix) {
		System.out.println(toString(prefix));

		if (children != null) {
			for (int i = 0; i < children.length; ++i) {
				SimpleNode n = (SimpleNode) children[i];
				if (n != null) {
					n.dump(prefix + " ");
				}
			}
		}
	}

	@Override
	public String toString() {
		if (value == null) {
			return super.toString();
		} else {
			return value.toString();
		}
	}

	@Override
	public String toString(String prefix) {
		return prefix + this.toString();
	}
}
