package edu.uptc.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

import edu.uptc.control.Control;

@SuppressWarnings("serial")
public class PanelUserRegistration extends JPanel{
	private JLabel id;
	private JLabel formalities;
	private JTextField idUser;
	private JComboBox<String> jcbFormalities;
	private JButton accept;
	
	public PanelUserRegistration() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		setBackground(Color.white);
		initialize();
		characteristics();
		addElements();
	}

	private void initialize() {
		id = new JLabel("Identificacion");
		formalities = new JLabel("Tramite");
		idUser = new JTextField();
		jcbFormalities = new JComboBox<String>();
		accept = new JButton("Aceptar");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void characteristics() {
		id.setFont(new Font("Arial", Font.BOLD, 15));
		id.setBounds(30, 20, 100, 50);
		formalities.setFont(new Font("Arial", Font.BOLD, 15));
		formalities.setBounds(330, 20, 100, 50);
		idUser.setBounds(140, 30, 150, 30);
		jcbFormalities.setModel(new DefaultComboBoxModel(Formalities.values()));
		jcbFormalities.setBounds(400, 30, 200, 30);
		accept.setBounds(650, 30, 80, 30);
	}

	private void addElements() {
		add(id);
		add(formalities);
		add(idUser);
		add(jcbFormalities);
		add(accept);
	}
	
	public void toAssing(Control control) {
		accept.setActionCommand(Actions.ACCEPT);
		accept.addActionListener(control);
	}
	
	public JTextField getIdUser() {
		return idUser;
	}

	public JComboBox<String> getJcbFormalities() {
		return jcbFormalities;
	}

	public void setIdUser(JTextField idUser) {
		this.idUser = idUser;
	}

	public void setJcbFormalities(JComboBox<String> jcbFormalities) {
		this.jcbFormalities = jcbFormalities;
	}
}