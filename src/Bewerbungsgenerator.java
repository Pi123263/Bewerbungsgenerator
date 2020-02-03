import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.event.ActionEvent;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Desktop;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Bewerbungsgenerator extends JFrame {

	/*
	 Name: Bewerbungsgenerator
	 Author: Pi123263
	 Date: 03.02.2020
	 */
	
	private JPanel contentPane;
	private JTextArea taAdresse;
	private JTextField tfAnsprache;
	private JTextField tfDatum;
	private JTextField tfStartdatum;
	private JTextField tfBerufsname;
	private JTextArea textArea;
	private JRadioButton rBFisi;
	private JRadioButton rBFIAE;
	private JButton btnAktualisieren;
	private JRadioButton rdbtnFrau;
	private JRadioButton rdbtnHerr;
	private JButton btnGithub;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bewerbungsgenerator frame = new Bewerbungsgenerator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Bewerbungsgenerator() {
		setTitle("Bewerbungsgenerator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAnsprache = new JLabel("Ansprache");
		lblAnsprache.setBounds(10, 157, 80, 14);
		contentPane.add(lblAnsprache);
		
		tfAnsprache = new JTextField();
		tfAnsprache.setBounds(10, 182, 256, 20);
		contentPane.add(tfAnsprache);
		tfAnsprache.setColumns(10);
		
		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setBounds(180, 11, 46, 14);
		contentPane.add(lblDatum);
		
		tfDatum = new JTextField();
		tfDatum.setBounds(180, 36, 86, 20);
		contentPane.add(tfDatum);
		tfDatum.setColumns(10);
		LocalDate localDate = LocalDate.now();
		tfDatum.setText(DateTimeFormatter.ofPattern("dd.MM.yyyy").format(localDate));
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(10, 11, 80, 14);
		contentPane.add(lblAdresse);
		
		taAdresse = new JTextArea();
		taAdresse.setBounds(10, 34, 160, 112);
		contentPane.add(taAdresse);
		
		rdbtnHerr = new JRadioButton("Herr");
		rdbtnHerr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					tfAnsprache.setText("Sehr geehrter Herr");
					rdbtnFrau.setSelected(false);
			}
		});
		rdbtnHerr.setBounds(99, 153, 56, 23);
		contentPane.add(rdbtnHerr);
		
		rdbtnFrau = new JRadioButton("Frau");
		rdbtnFrau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					tfAnsprache.setText("Sehr geehrte Frau");
					rdbtnHerr.setSelected(false);
			}
		});
		rdbtnFrau.setBounds(162, 153, 56, 23);
		contentPane.add(rdbtnFrau);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(275, 0, 2, 580);
		contentPane.add(separator);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 9));
		textArea.setBounds(287, 6, 370, 570);
		contentPane.add(textArea);
		
		btnAktualisieren = new JButton("Text aktualisieren");
		btnAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Generieren();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAktualisieren.setBounds(10, 359, 256, 23);
		contentPane.add(btnAktualisieren);
		
		JLabel lblStartdatum = new JLabel("Startdatum");
		lblStartdatum.setBounds(10, 213, 145, 14);
		contentPane.add(lblStartdatum);
		
		tfStartdatum = new JTextField();
		tfStartdatum.setText("01.09.2020");
		tfStartdatum.setBounds(10, 238, 145, 20);
		contentPane.add(tfStartdatum);
		tfStartdatum.setColumns(10);
		
		JLabel lblBerufsname = new JLabel("Berufsname");
		lblBerufsname.setBounds(10, 269, 80, 14);
		contentPane.add(lblBerufsname);
		
		tfBerufsname = new JTextField();
		tfBerufsname.setColumns(10);
		tfBerufsname.setBounds(10, 294, 256, 20);
		contentPane.add(tfBerufsname);
		
		rBFisi = new JRadioButton("FISI");
		rBFisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfBerufsname.setText("Fachinformatiker Systemintegration");
				rBFIAE.setSelected(false);
			}
		});
		rBFisi.setBounds(99, 265, 56, 23);
		contentPane.add(rBFisi);
		
		rBFIAE = new JRadioButton("FIAE");
		rBFIAE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfBerufsname.setText("Fachinformatiker Anwendungsentwicklung");
				rBFisi.setSelected(false);
			}
		});
		rBFIAE.setBounds(162, 265, 56, 23);
		contentPane.add(rBFIAE);
		
		JButton btnInitialisieren = new JButton("Initialisieren");
		btnInitialisieren.setEnabled(false);
		btnInitialisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*	try {
					Init();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		});
		btnInitialisieren.setBounds(10, 325, 256, 23);
		contentPane.add(btnInitialisieren);
		
		btnGithub = new JButton("GitHub");
		btnGithub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().browse(new URL("https://github.com/Pi123263/Bewerbungsgenerator").toURI());
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			}
		});
		btnGithub.setBounds(10, 526, 89, 23);
		contentPane.add(btnGithub);
		
		JLabel lblBewerbungsgeneratorMadeBy = new JLabel("  Bewerbungsgenerator made by Pi123263");
		lblBewerbungsgeneratorMadeBy.setBounds(10, 546, 256, 23);
		contentPane.add(lblBewerbungsgeneratorMadeBy);
	}
	public void Generieren() throws IOException {
		FileReader SourceDatei = new FileReader("SourceDatei.txt");
		@SuppressWarnings("resource")
		BufferedReader buff = new BufferedReader(SourceDatei);
		String Text="",line;
		int i=0;
		boolean Check = false;
		
		textArea.setText("");
		
		try {
			while ((line = buff.readLine()) != null) {
				if(line.contains("--Anschreiben =--")) {
					line = buff.readLine();
					while(!(line.contains("--Anschreiben Ende--"))) {
						Text = Text + line+"\r\n";
						line = line + "\r\n";
						
						if(line.contains("**DATUM**")) {
							line = line.replace("**DATUM**", tfDatum.getText());
							textArea.setText(textArea.getText()+line);
							Check = true;
						} else if(line.contains("**FIRMEDNADRESSE**")) {
							line = line.replace("**FIRMEDNADRESSE**", taAdresse.getText());
							textArea.setText(textArea.getText()+line);
							Check = true;
						} else if(line.contains("**ANSPRACHE**")) {
							line = line.replace("**ANSPRACHE**", tfAnsprache.getText());
							textArea.setText(textArea.getText()+line);
							Check = true;
						} else if(line.contains("**STARTDATUM**")) {
							line = line.replace("**STARTDATUM**", tfStartdatum.getText());
							textArea.setText(textArea.getText()+line);
							Check = true;
						} else if(line.contains("**BERUFSNAME**")) {
							line = line.replace("**BERUFSNAME**", tfBerufsname.getText());
							textArea.setText(textArea.getText()+line);
							Check = true;
						} else if(!Check){ textArea.setText(textArea.getText()+line);
						} else 
						System.out.println("MIDTEXT "+line);
						line = buff.readLine();
						Check = false;
					} 
				//Kontrolle des eingelesenen Textes
				System.out.print("Text "+Text);
				}
				//Kontrolle des eingelesenen Textes
				System.out.println(i+++line); 
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Source Datei nicht gefunden");
		}
	}
}
