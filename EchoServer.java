import java.net.*;
import java.io.*;
public class EchoServer {

private ServerSocket serverSocket;
private Socket clientSocket;
private PrintWriter out;
private BufferedReader in;
public void StartConnection(int port) throws IOException{
	serverSocket =new ServerSocket(port);
	
	clientSocket=serverSocket.accept();
	System.out.println("Connection established");
	out = new PrintWriter(clientSocket.getOutputStream(), true);
    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    String messagefromclient=in.readLine();
    while(messagefromclient!=null) {
    	    messagefromclient=in.readLine();
    	    System.out.println("Client says "+ messagefromclient);
    	if("stop".equals(messagefromclient)) {
    		out.println("Thanks for the communication !");
    		break;
    	}
    	out.print(messagefromclient);
    }
    serverSocket.close();
    
}
}
