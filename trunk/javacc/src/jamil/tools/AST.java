package jamil.tools;

import jamil.Node;

import java.awt.Color;
import javax.swing.JFrame;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Predicate;
import org.apache.commons.collections15.functors.ConstantTransformer;

import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.VertexLabelAsShapeRenderer;

public class AST {

	// Árvore Sintática Abstrata
	static int edge = 0;
	static DelegateTree<Node, Integer> graph;

	private static void build(Node node) {
		for (int i = 0; i < node.jjtGetNumChildren(); i++) {
			{
				Node n = node.jjtGetChild(i);
				if (n != null) {
					graph.addChild(edge++, node, n);
					build(n);
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void render(Node tree) {
		// Grafo
		Factory<DirectedGraph<Node, Integer>> factory = DirectedOrderedSparseMultigraph
				.<Node, Integer> getFactory();
		graph = new DelegateTree<Node, Integer>(factory);

		// Adiciona os vértices ao grafo
		graph.addVertex(tree);
		build(tree);

		// Layout
		TreeLayout<Node, Integer> layout = new TreeLayout<Node, Integer>(graph,
				75, 50);

		// Visualização
		VisualizationViewer<Node, Integer> vv = new VisualizationViewer<Node, Integer>(
				layout);
		VertexLabelAsShapeRenderer<Node, Integer> vlasr = new VertexLabelAsShapeRenderer<Node, Integer>(
				vv.getRenderContext());

		/* Vértices */
		// Cor da borda
		vv.getRenderContext().setVertexDrawPaintTransformer(
				new ConstantTransformer(Color.black));
		// Cor de fundo
		vv.getRenderContext().setVertexFillPaintTransformer(
				new ConstantTransformer(Color.lightGray));
		// Rótulo como string
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		// Formato retangular
		vv.getRenderContext().setVertexShapeTransformer(vlasr);
		// Posiciona texto dentro
		vv.getRenderer().setVertexLabelRenderer(vlasr);

		/* Arestas */
		// Remove a seta da aresta
		vv.getRenderContext().setEdgeArrowPredicate(
				new Predicate<Context<Graph<Node, Integer>, Integer>>() {
					@Override
					public boolean evaluate(
							Context<Graph<Node, Integer>, Integer> arg0) {
						return false;
					}
				});
		// Formato da linha
		vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());

		// Habilita suporte ao mouse
		final DefaultModalGraphMouse<Node, Integer> graphMouse = new DefaultModalGraphMouse<Node, Integer>();
		vv.setGraphMouse(graphMouse);

		// Cria a janela do JFrame
		JFrame jf = new JFrame();
		jf.getContentPane().add(vv);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setTitle("Abstract Syntax Tree");
		jf.pack();
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setVisible(true);
	}

	public static void write() {
		// TODO: escrever árvore num arquivo
	}

}
