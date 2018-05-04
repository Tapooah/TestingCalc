package telran.net;
import java.io.*;
import java.net.*;
public class TcpServer {
public TcpServer(int port,Protocol protocol) throws IOException{
	ServerSocket serverSocket=new ServerSocket(port);
	System.out.println("server is listening on port: "+port);
	while(true){
		Socket socket=serverSocket.accept();
		TcpServerClient client=new TcpServerClient(socket, protocol);
		client.run();
	}
}
}
