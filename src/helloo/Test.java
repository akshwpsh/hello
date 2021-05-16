package helloo;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.FileDialog;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;


public class Test {

	private JFrame frmJava;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frmJava.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	Cmd cmd = new Cmd();
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJava = new JFrame();
		frmJava.setTitle("Java \uD3B8\uC9D1\uAE30");
		frmJava.setBounds(100, 100, 613, 491);
		frmJava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJava.getContentPane().setLayout(new CardLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		splitPane.setResizeWeight(0.8);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		frmJava.getContentPane().add(splitPane, "name_1653289724749300");
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		JMenuBar menuBar = new JMenuBar();
		frmJava.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JButton btnNewButton = new JButton("New ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTextArea().setText("");
			}
		});
		mnNewMenu.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("OPEN");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog dialog = new FileDialog(frmJava,"열기",FileDialog.LOAD);
				dialog.setDirectory(".");
				dialog.setVisible(true);
				if(dialog.getFile() == null) return;
				String dfName = dialog.getDirectory() + dialog.getFile();
				try {
					BufferedReader reader = new BufferedReader(new FileReader(dfName));
					textArea.setText("");
					String line;
					while((line = reader.readLine()) !=null) {
						textArea.append(line +"\n");
					}
					reader.close();
					
					frmJava.setTitle(dialog.getFile());
					
					
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(frmJava,"열기 오류");
				}
				
			}
		});
		mnNewMenu.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("SAVE\r\n");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				FileDialog dialog = new FileDialog(frmJava,"저장",FileDialog.SAVE );
		        dialog.setDirectory(".");
		        dialog.setVisible(true);
		        if(dialog.getFile() == null) return;
		        String dfName = dialog.getDirectory() + dialog.getFile()+".java";
		        try {
		        	BufferedWriter writer = new BufferedWriter(new FileWriter(dfName));
		        	writer.write(textArea.getText());
		        	writer.close();
		        	
		        	frmJava.setTitle(dialog.getFile() +"-메모장");
		        }catch(Exception e2) {
		        	JOptionPane.showMessageDialog(frmJava,"저장 오류");
		        }    
			}
			
			
			});
		mnNewMenu.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Exit  ");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(btnNewButton_3);
		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);
		
		JButton btnNewButton_4 = new JButton("Copy ");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
				StringSelection contents = new StringSelection(textArea.getText());
				cb.setContents(contents, null);
				
			}
		});
		mnNewMenu_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Paste");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable contents = cb.getContents(cb);
				if(contents != null) {
					try {
					 String pasteString = (String)(contents.getTransferData(DataFlavor.stringFlavor));
			          textArea.insert(pasteString, textArea.getCaretPosition());
					}catch (Exception k) {}
				}
			}
		});
		mnNewMenu_1.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Cut    ");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
				StringSelection contents = new StringSelection(textArea.getText());
				cb.setContents(contents, null);
				textArea.setText(null);
			}
		});
		mnNewMenu_1.add(btnNewButton_6);
		
		JMenu mnNewMenu_2 = new JMenu("Compile");
		menuBar.add(mnNewMenu_2);
		
		JButton btnNewButton_7 = new JButton("Compile");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] findname = textArea.getText().split(" ");
				String name = null;
				for (int i = 0; i<findname.length; i++) {
					if(findname[i].equals("class") == true) {
						name = findname[i+1];
						if(name.indexOf("{") != -1) {
							name.replace("{", "");
						}
						cmd.FILENAME = name+".java";
					}
				}
				String[] code = textArea.getText().split("\n");
				StringBuffer buffer = new StringBuffer();
				for (int i = 0; i<code.length; i++) {
					buffer.append(code[i]);
				}
				String command = cmd.inputSource(buffer.toString());
				String result = cmd.execCommand(command);
				
				textArea_1.append(result);
			}
		});
		mnNewMenu_2.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Run      ");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = cmd.Run();
				textArea_1.append(result);
			}
		});
		mnNewMenu_2.add(btnNewButton_8);
	}

	public JTextArea getTextArea() {
		return textArea;
	}
}
