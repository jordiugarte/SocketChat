package main;

import javax.swing.*;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		MarcoServidor mimarco = new MarcoServidor();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class MarcoServidor extends JFrame implements Runnable {
	public MarcoServidor() {
		setBounds(1200, 300, 280, 350);
		JPanel milamina = new JPanel();
		milamina.setLayout(new BorderLayout());
		areatexto = new JTextArea();
		milamina.add(areatexto, BorderLayout.CENTER);
		add(milamina);
		setVisible(true);
		Thread elhilo = new Thread(this);
		elhilo.start();
	}

	private JTextArea areatexto;

	public void run() {
		try {
			ServerSocket elservidor = new ServerSocket(8888);
			ServerSocket server = new ServerSocket(8889);
			while (true) {
				Socket elsocket = elservidor.accept();
				Socket socket = server.accept();
				DataInputStream data_entrada = new DataInputStream(
						elsocket.getInputStream());
				String elmensaje = data_entrada.readUTF();
				areatexto.append("\n" + elmensaje);
				DataInputStream data_entrada1 = new DataInputStream(
						socket.getInputStream());
				String mensaje = data_entrada1.readUTF();
				areatexto.append("\n" + mensaje);
				Socket socket1 = new Socket("192.168.0.3", 8890);
				DataOutputStream datos_salida = new DataOutputStream(
						socket1.getOutputStream());
				datos_salida.writeUTF(elmensaje);
				elsocket.close();
				socket.close();
				socket1.close();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}