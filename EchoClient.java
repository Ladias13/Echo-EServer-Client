import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    public void InitializeConnection(String ip,int port) throws UnknownHostException, IOException  {
    
			clientSocket=new Socket(ip,port);
	
			out = new PrintWriter(clientSocket.getOutputStream(), true);
	
     
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    public void stopconnection() throws IOException {
    	in.close();
    	out.close();
    	clientSocket.close();
    	
    }
    public String sendmsg(String message) throws IOException {
    	out.println(message);;
    	String responsefromserver;

	responsefromserver = in.readLine();
	
    	return responsefromserver;
    }
    
    	
    
    public static void main(String args[]) throws UnknownHostException, IOException {
    	EchoClient client=new EchoClient();
    	EchoServer server= new EchoServer();
    	server.StartConnection(8080);
    	client.InitializeConnection("localhost", 8080);
    	System.out.print("Connection with server Initiliazed.Communication has begun\n");
    	System.out.println("Type a message to server "+"\n"+"Press 'stop' to stop communication\n");
    	System.out.println("(Limited to 10 messages if you don't stop)Sorry we got better things to do");
    	int counter=0;
    	while(counter<10) {
 
    		Scanner sc=new Scanner(System.in);
    		String messagetoserver =sc.next();
    		if(messagetoserver!=null) {
    			client.sendmsg(messagetoserver);
    			System.out.println("Server says:"+client.sendmsg(messagetoserver));
    				
    		}
    		else if(messagetoserver=="stop") {
    			break;
    		}
    		counter++;
    		
    	}
    	client.stopconnection();
    	System.out.println("Connection stopped");
    }
    
}
