package resources;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

public class CrawlerGraph<Vertex,E> extends Multigraph<Vertex,E> {
	//Multigraph<Vertex, DefaultEdge> graph =new Multigraph<Vertex, DefaultEdge>(DefaultEdge.class);

public CrawlerGraph() {
	super((Class<? extends E>) DefaultEdge.class);
}

public CrawlerGraph(Vertex vertexClass,Class<? extends E> edgeClass) {
	super((Class<? extends E>) DefaultEdge.class);
}
		
	
	
}	
