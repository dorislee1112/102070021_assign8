import java.awt.Container;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;



public class Server{

	private ServerSocket serverSocket;
	private ConnectionThread connection;
	private JFrame window = new JFrame("Result From App");
	private JLabel s = new JLabel();
	private Container contain = this.window.getContentPane();
	
	public Server(){
		
		//設定版面
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(500, 300);  
		window.setResizable(false);
		window.setVisible(true);
		contain.setLayout(null);
		this.s.setBounds(80, 100, 400, 50);
		this.s.setText("The result from App is : " );
		contain.add(s);
		
		try {
			this.serverSocket = new ServerSocket(8000);
			System.out.printf("Server IP: %s\nServer port: 8000.\n", this.getAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	//建立連線
	public void runForever() {
		System.out.println("Server starts waiting for client.");
		while(true){
			try{
				Socket ToClient = this.serverSocket.accept();
				System.out.println("Get connection from client"
										+ ToClient.getInetAddress()+":"
										+ ToClient.getPort());
					
				connection = new ConnectionThread(ToClient);
				connection.start();
			}catch(BindException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	
	// Define an inner class (class name should be ConnectionThread)
	class ConnectionThread extends Thread{
		private PrintWriter writer;
		private BufferedReader reader;
		private Socket socket;
		
		public ConnectionThread(Socket socket){
			this.socket = socket;	
			try{
				this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				this.writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
			}catch(IOException e){
				e.printStackTrace();
			
			}
		}
		
		public void run(){
			while(true){
				try{
					//如果收到解答及印出
					String line = this.reader.readLine();
					System.out.println(line);
					if(line!=null){
						s.setText("The Result From App is : " +line);
						window.repaint();
					}

				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}	
	}
	
	public String getAddress(){
		InetAddress localIp;
	    try{
	    	localIp=InetAddress.getLocalHost();
	    	String ip=localIp.getHostAddress();
	    	return ip;
	    }catch (java.net.UnknownHostException e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = new Server();
		server.runForever();
	}

}
