package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread
{

	public static void main (String args[])
	{
		ServerSocket sfd = null;
		try
		{
			sfd = new ServerSocket(7000);
		}
		catch (IOException ioe)
		{
			System.out.println("Comunicación rechazada."+ioe);
			System.exit(1);
		}

		while (true)
		{
			try
			{
				Socket nsfd = sfd.accept();
				System.out.println("Conexion aceptada de: "+nsfd.getInetAddress());
				DataInputStream oos = new DataInputStream(nsfd.getInputStream());
				DataOutputStream escritor = new DataOutputStream(new FileOutputStream(new File("SerieNacionalCompleto_respaldo.dat")));
				int unByte;
				try {
					while ((unByte = oos.read()) != -1)
						escritor.write(unByte);
					oos.close();
					escritor.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			} 
			catch(IOException ioe)
			{
				System.out.println("Error: "+ioe);
			}
		}
	}
}