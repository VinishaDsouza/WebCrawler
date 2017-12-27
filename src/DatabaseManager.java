
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import resources.Vertex;
import resources.Graph;


public class DatabaseManager {

	// DB getter
	public DB getDatabase() {
		return db;
	}

	// DB setter
	public void setDatabase(DB db) {
		this.db = db;
	}

	// Singleton setter
	public static void setInstance(DatabaseManager instance) {
		DatabaseManager.instance = instance;
	}

	private DB db;
	private MongoClient mongoClient;
	private static DatabaseManager instance;
	//private final String DOCUMENTS = "documents";
	private final String GRAPH = "graph";

	// Constructor (only called once)
	@SuppressWarnings("deprecation")
	public DatabaseManager() {


			this.mongoClient = new MongoClient( "localhost" );
			setDatabase(this.mongoClient.getDB( "GraphData" ));
	

	}
	
	//Adds a new graph to the mongo database
	public synchronized boolean addNewGraph(String name, Byte graph) {
		try {
			DBCollection col = db.getCollection(GRAPH);
			BasicDBObject obj = new BasicDBObject();
			obj.put("name", name);
			obj.put("bytes", graph);
			col.insert(obj);
		} catch (MongoException e) {
			System.out.println("MongoException: " + e.getLocalizedMessage());
			return false;
		}
		
		return true;
	}
	
	public boolean insert(String name, byte[] graph){
		DBCollection col = db.getCollection(GRAPH);
		BasicDBObject doc = new BasicDBObject();
		doc.put("name", name);
		doc.put("graph", graph);
		col.insert(doc);
		return true;	
	}
	
	//Gets a graph from the mongo database
	public synchronized byte[] getGraphData(String name) {

		try {
			BasicDBObject query = new BasicDBObject("name", name);
			DBCollection col = db.getCollection(GRAPH);
			DBObject result = col.findOne(query);

			if(result != null) {
				Map<?, ?> graphMap = result.toMap();
				byte[] bytes = (byte[]) graphMap.get("bytes");				
				return bytes;
			}

			return null;
		} catch (MongoException e) {
			System.out.println("MongoException: " + e.getLocalizedMessage());
			return null;
		}
			
	}
	public static synchronized DatabaseManager getInstance() throws UnknownHostException{
		if(instance == null){
			instance= new DatabaseManager();
		}
		return instance;
	}
	
	public boolean open(Integer id,  String name) throws IOException{	
		BasicDBObject d = new BasicDBObject();
		//Map<Object, Object> d = new ConcurrentHashMap<>();
		d.put("id", id);
		d.put("name", name);
		
		DBCollection col = db.getCollection(GRAPH);

		col.insert(d);
		//d.put("links", links);
		//Document doc = new Document(d);	
//		if(containsKey(doc)){
//			BasicDBObject query = new BasicDBObject("id", id);
//			coll.update(query, doc.toDBObject());
//		}else{
//			System.out.println("Adding document..");
//			coll.insert(doc.toDBObject());
//		}
		return true;
}
}