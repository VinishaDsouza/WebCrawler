package resources;

import org.jgrapht.graph.DefaultEdge;

import org.jgrapht.graph.Multigraph;

public class Graph<Vertex,E> extends Multigraph<Vertex,E> {
	//Multigraph<Vertex, DefaultEdge> graph =new Multigraph<Vertex, DefaultEdge>(DefaultEdge.class);

public Graph() {
	super((Class<? extends E>) DefaultEdge.class);
}

//byte[] graphExport = Marshaller.serializeObject(graph);
//FileOutputStream stream = new FileOutputStream("files/graph");
//try {
//    stream.write(graphExport);
//} finally {
//    stream.close();
//}
//		
	
	

}
