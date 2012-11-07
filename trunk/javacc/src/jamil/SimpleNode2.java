package jamil;

public class SimpleNode2 extends SimpleNode {

	public SimpleNode2(int i) {
		super(i);
	}

	public SimpleNode2(LMP p, int i) {
		super(p, i);
	}

	@Override
	public void dump(String prefix) {
		System.out.print(toString(prefix));
		if(value != null)
		{
			System.out.print(" (" + value + ")");
		}
		System.out.println();
		
		// TODO: melhorar esse acesso
		//LMP.graph.addVertex(this);
		
		if (children != null) {
			for (int i = 0; i < children.length; ++i) {
				SimpleNode n = (SimpleNode) children[i];
				if (n != null) {
					n.dump(prefix + " ");
				}
			}
		}
	}

}
