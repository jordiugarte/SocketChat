package main;

//Nombre completo: Ignacio del Rio Zenteno
//Codigo: 46036

import javax.swing.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	public static void main(String[] args) {
		MarcoCliente mimarco = new MarcoCliente();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class MarcoCliente extends JFrame {
	public MarcoCliente() {
		setBounds(800, 450, 500, 450);
		LaminaMarcoCliente milamina = new LaminaMarcoCliente();
		add(milamina);
		setVisible(true);
	}
}

class LaminaMarcoCliente extends JPanel {
	public LaminaMarcoCliente() {
		JLabel texto = new JLabel("Mensaje:");
		add(texto);
		campo1 = new JTextField(25);
		add(campo1);
		miboton = new JButton("Enviar");
		SendText event0 = new SendText();
		miboton.addActionListener(event0);
		add(miboton);
	}
	
	private class SendText implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(campo1.getText());
			try {
				Socket elsocket = new Socket("192.168.0.3", 8888);
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