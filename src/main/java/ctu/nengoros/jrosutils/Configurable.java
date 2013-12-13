package ctu.nengoros.jrosutils;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.ros.concurrent.CancellableLoop;
import org.ros.message.MessageListener;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.parameter.ParameterTree;
import org.ros.node.topic.Publisher;
import org.ros.node.topic.Subscriber;

import ctu.nengoros.rosparam.ParameterTreeCrawler;

import std_msgs.Bool;

/**
 * 
 * This class will represent a ROS node with configurable numbers of 
 * inputs connections, config. connections and output connections.
 * 
 * @author Jaroslav Vitku vitkujar@fel.cvut.cz
 * 
 */
public class Configurable extends AbstractNodeMain implements ConfigurableInt{

	int aa;
	// node setup
	private final boolean SEND = false;
	private final int sleepTime = 1000;

	// ROS stuff
	Subscriber<std_msgs.Bool> subscriberA, subscriberB;
	Publisher<std_msgs.Bool> publisher;
	Log log;
	
	public final String aT = "logic:gates:ina";
	public final String yT = "logic:gates:outa";

	private boolean a = false, y=false;
	private volatile boolean inited = false;
	
	
	private void send(){
		if(!inited)
			return;
		
		// TODO
	}
	
	
	public void addInputSubscriber(){
		
	}
	
	public void addConfigSubscriber(){
		
	}
	
	public void addOutputPublisher(){
		
	}
	
	
	
	@Override
	public void onStart(final ConnectedNode connectedNode) {
		
		Map<GraphName,GraphName> remaps = connectedNode.getResolver().getRemappings();
		
		System.out.println("my namespace is: "+connectedNode.getResolver().getNamespace());
		
		if(remaps.containsKey("noIns")){
			System.out.println("noooooo iiiiiiins "+remaps.get("noIns"));
		}
		ParameterTree pt = connectedNode.getParameterTree();
		ParameterTreeCrawler ptc = new ParameterTreeCrawler(pt);
		ptc.printAll();
		if(pt.has("noIns")){
			System.out.println("fffffffffff "+pt.getInteger("noIns"));
		}
		System.out.println(ptc.getAllRemapps(remaps));
		System.out.println("reeeeeee " +GraphName.of("ccc").isRelative());
		//System.out.println("tried to get param from my NS: "+pt.getString(GraphName.of("ccc").toGlobal()));
		
		
		
		System.out.println("--a is: "+GraphName.of("b"));
		System.out.println("--a is: "+GraphName.of("~b"));
		//System.out.println("--a is: "+GraphName.of("_b"));
		//System.out.println("--a is: "+GraphName.of("__b"));
		//connectedNode.getResolver().getNamespace()
		log = connectedNode.getLog();
		/*
		// register subscribers
		subscriberA = connectedNode.newSubscriber(aT, std_msgs.Bool._TYPE);

		subscriberA.addMessageListener(new MessageListener<std_msgs.Bool>() {
			@Override
			public void onNewMessage(Bool message) {
				a = message.getData();
				//y = copute(a);
				send();
			}
		});// dynamic

		// register publisher
		publisher = connectedNode.newPublisher(yT, std_msgs.Bool._TYPE);		
		inited = true;

		
		// infinite loop
		connectedNode.executeCancellableLoop(new CancellableLoop() {
			@Override
			protected void setup() {	
			}

			@Override
			protected void loop() throws InterruptedException {

				if(SEND){
					std_msgs.Bool out = publisher.newMessage();
					out.setData(y);
					publisher.publish(out);
					log.info("Publishing this: \"" + out.getData() + " !! on topic: "+yT);
				}
				Thread.sleep(sleepTime);
			}
		});*/
	}


	@Override
	public GraphName getDefaultNodeName() {
		return GraphName.of("cooonf");
	}
}
