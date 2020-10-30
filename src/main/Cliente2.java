package main;

import javax.swing.*;

import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Cliente2 {
	public static void main(String[] args) {
		MarcoCliente1 mimarco1 = new MarcoCliente1();
		mimarco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class MarcoCliente1 extends JFrame implements Runnable {
	public MarcoCliente1() {
		setBounds(800, 450, 500, 450);
		LaminaMarcoCliente1 milamina1 = new LaminaMarcoCliente1();
		add(milamina1);
		setVisible(true);
		Thread elhilo = new Thread(this);
		elhilo.start();
	}

	public void run() {
		try {
			ServerSocket server = new ServerSocket(8890);
			while (true) {
				Socket socket = server.accept();
				DataInputStream data_entrada = new DataInputStream(
						socket.getInputStream());
				String elmensaje = data_entrada.readUTF();
				System.out.println(elmensaje + "2");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class LaminaMarcoCliente1 extends JPanel {
	public LaminaMarcoCliente1() {
		JLabel texto = new JLabel("Mensaje2:");
		add(texto);
		campo1 = new JTextField(25);
		add(campo1);
		miboton = new JButton("Enviar2");
		SendText event0 = new SendText();
		miboton.addActionListener(event0);
		add(miboton);
		
	}

	private class SendText implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(campo1.getText());
			try {
				Socket elsocket = new Socket("192.168.0.3", 8889);
				DataOutputStream datos_salida = new DataOutputStream(
						elsocket.getOutputStream());
				datos_salida.writeUTF(campo1.getText());
				datos_salida.close();
			} catch (UnknownHostException e0) {
				e0.printStackTrace();
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}
		}
	}

	private JTextField campo1;
	private JButton miboton;
}
