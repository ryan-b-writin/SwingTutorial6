package tutorial;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class SwingFC implements ActionListener{
	
	JTextField jtfFirst, jtfSecond;
	
	JButton jbtnComp;
	
	JLabel jlabFirst, jlabSecond;
	JLabel jlabResult;
	
	SwingFC(){
		
		JFrame jfrm = new JFrame("Compare files");
		
		jfrm.setLayout(new FlowLayout());
		
		jfrm.setSize(200,190);
		
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jtfFirst = new JTextField(14);
		jtfSecond = new JTextField(14);
		
		jtfFirst.setActionCommand("fileA");
		jtfSecond.setActionCommand("fileB");
		
		JButton jbtnComp = new JButton("Compare");
		
		jbtnComp.addActionListener(this);
		
		jlabFirst = new JLabel("First file: ");
		jlabSecond = new JLabel("Second file: ");
		jlabResult = new JLabel("");
		
		jfrm.add(jlabFirst);
		jfrm.add(jtfFirst);
		jfrm.add(jlabSecond);
		jfrm.add(jtfSecond);
		jfrm.add(jbtnComp);
		jfrm.add(jlabResult);
		
		jfrm.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		int i=0, j=0;
		
		if(jtfFirst.getText().equals("")) {
			jlabResult.setText("First Filename not input");
			return;
		}
		if(jtfSecond.getText().equals("")) {
			jlabResult.setText("Second Filename not input");
			return;
		}
		
		try(FileInputStream f1 = new FileInputStream(jtfFirst.getText());
			FileInputStream f2 = new FileInputStream(jtfSecond.getText()))
		{
			
			do {
				i = f1.read();
				j = f2.read();
				
				if (i!=j) break;
			} while ( i != -1 && j != -1);
			
			if (i != j)
				jlabResult.setText("File contents are different.");
			else
				jlabResult.setText("File contents Identical.");
		} catch (IOException e) {
			jlabResult.setText("File Error");
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SwingFC();
			}
		});
	}

}
