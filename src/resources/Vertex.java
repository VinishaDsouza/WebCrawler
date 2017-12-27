package resources;


public class Vertex {
	int id;
	String url;
	
	
	public Vertex( int id, String url) {
		this.id = id;
		this.url= url;	
	}
	
	public int getId(){
		return id;
	}
	
	public String getUrl(){
		return url;
	}
	
	public String toString()
	{
		return "url: "+url+"id"+id;
		
	}
}