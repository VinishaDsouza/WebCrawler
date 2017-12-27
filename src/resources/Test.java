package resources;

import org.jgrapht.graph.*;

public class Test {

	


public static void main(String[] args) throws Exception {
	
	Multigraph<Vertex,DefaultEdge> t =new Multigraph<Vertex,DefaultEdge>(DefaultEdge.class);
	CrawlerGraph<Vertex,DefaultEdge> t2 =new CrawlerGraph<Vertex,DefaultEdge>();
	
	Vertex v1=new Vertex(1,"u1");
	Vertex v2=new Vertex(2,"u2");
	Vertex v3=new Vertex(3,"u3");
	
	t2.addVertex(v1);
	t2.addVertex(v2);
	t2.addVertex(v3);
	//t.addEdge("v1","v2");t.addEdge("v1","v3");
	t2.addEdge(v1,v2);
	t2.addEdge(v1,v3);
	
	System.out.println("---"+t2.getEdge(v1, v2));
	System.out.println("---"+t2.edgeSet().toString());
}


}
