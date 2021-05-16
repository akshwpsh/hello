package Hello2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.ScrollPane;
import java.awt.FileDialog;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.awt.Frame;
import java.io.InputStream;
import java.io.InputStreamReader;

public class gd extends JFrame {

	private JPanel contentPane;
	private JSplitPane splitPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gd frame = new gd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
		});	
	}


String copyText;
	public gd() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 408);
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		splitPane = new JSplitPane();
		splitPane.setDividerSize(10);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		TextArea textArea = new TextArea();
		scrollPane.setColumnHeaderView(textArea);	
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);
		
		TextArea textArea_1 = new TextArea();
		scrollPane_1.setColumnHeaderView(textArea_1);
		splitPane.setDividerLocation(165);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Open");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("C:\\"));
				fs.setDialogTitle("Open a file");
				int result = fs.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					try {
						File fi = fs.getSelectedFile();
						BufferedReader br = new BufferedReader(new FileReader(fi.getPath()));
						String line = "";
						String s = "";
						while((s = br.readLine()) != null) {     
							line += s;    
						}
						textArea.setText(line);
						if(br != null)
							br.close();
	
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}		
			}
		});
	
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Save");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("C:\\"));
				fs.setDialogTitle("Save a file");
				int result = fs.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					String content = textArea.getText();
					File fi = fs.getSelectedFile();
					try {
						FileWriter fw = new FileWriter(fi.getPath() + ".java");
						fw.write(content);
						fw.flush();
						fw.close();
						
					} catch(Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}	
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mnNewMenu.add(mntmNewMenuItem);	
		mntmNewMenuItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);
		
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Copy");
		mnNewMenu_1.add(mntmNewMenuItem_5);
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == mntmNewMenuItem_5) {
					copyText = textArea.getSelectedText();
				}
			}
		});
		
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Paste");
		mnNewMenu_1.add(mntmNewMenuItem_6);
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == mntmNewMenuItem_6) {
					int position = textArea.getCaretPosition();
					textArea.insert(copyText, position);
				}
						
			}
		});
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Cut");
		mnNewMenu_1.add(mntmNewMenuItem_4);
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == mntmNewMenuItem_4) {
					copyText = textArea.getSelectedText();
					int start = textArea.getSelectionStart();
					int end = textArea.getSelectionEnd();
					textArea.replaceRange("", start, end);
				}
			}
		});

		JMenu mnNewMenu_2 = new JMenu("Compile");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Compile");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cmd cmd = new Cmd();
				//StringBuffer buffer = new StringBuffer();
				//buffer.append("public class Test");
				//buffer.append("{public static void main(String[] args)");
				//buffer.append("{ System.out.print(\"hi\");} }");
				
				String command = cmd.inputSource("cd C:\\Users\\±è¼ºÀº\\Desktop");
				String command2 = cmd.inputSource("javac hello.java");
				String result = cmd.execCommand(command);
				
				System.out.println(result);
			
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Run");
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
	}
	
}

		
		
	

