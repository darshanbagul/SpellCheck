
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;

import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.event.SpellChecker;

import java.util.ArrayList;
import java.util.List;
public class TypeText extends JPanel implements ActionListener {

    public JTextField textField;
    public JTextArea textArea;
    public JButton Label1;
    public JButton Label2;
    public JButton Label3;
    public JLabel Label4;
    public JLabel Label5;
    public JPanel Panel1;
    
    private final static String newline = "\n";
    private final static String separator = ": ";
    static SpellChecker spellCheck = null;
    static List<String> suggestions=new ArrayList<String>();
    public TypeText() {
    		
    	SpellDictionaryHashMap dictionary = null;
    	try {
			dictionary = new SpellDictionaryHashMap(new File("C:/Users/Darshan/workspace1/SpellCheck/dictionary/english.0"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		spellCheck = new SpellChecker(dictionary);
		
		
    	 Panel1 = new JPanel();
		 Panel1.setSize(450, 250);
		 Panel1.setFocusable(true);
		 
		JFrame.setDefaultLookAndFeelDecorated(true);
	    JFrame frame = new JFrame("WeDontChat 1.0");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 450);
	    
        //create a place to enter text
        textField = new JTextField(30);
        
        textField.addActionListener(this);
       

        //create a place to output responses
        textArea = new JTextArea(20, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        //textArea.append(chatBot.getBotName() + newline);
        textField.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent arg0) {
        		// TODO Auto-generated method stub
        		
        		if(arg0.getKeyChar()=='\n'){
        			 String text = textField.getText();
        		        textArea.append("You" + separator + text + newline);
        		        textField.selectAll();

        		        //Make sure the new text is visible, even if there
        		        //was a selection in the text area.
        		        textArea.setCaretPosition(textArea.getDocument().getLength());
        		        super.keyTyped(arg0);
        		}
        	}
		});
        Label1=new JButton("Suggestions");
        Label2=new JButton("Suggestions");
        Label3=new JButton("Suggestions");
        Label1.setBorderPainted(false);
        Label2.setBorderPainted(false);
        Label3.setBorderPainted(false);
        Label1.addActionListener(this);
        Label2.addActionListener(this);
        Label3.addActionListener(this);
        //Label4=new JLabel("Suggestions");
        
        //Label3=new JLabel("Suggestions");
        Label1.setVisible(true);
        Label2.setVisible(true);
        Label3.setVisible(true);
       // Label4.setVisible(true);
        //Label5.setVisible(true);
        
        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        Panel1.add(textField);
        c.fill=GridBagConstraints.VERTICAL;
        Panel1.add(Label1,c);
        Panel1.add(Label2,c);
        Panel1.add(Label3,c);
        //Panel1.add(Label4,c);
        //Panel1.add(Label5,c);
        c.fill = GridBagConstraints.BOTH;
        //c.weightx = 100;
        //c.weighty = 100;
        Panel1.add(scrollPane,c);
        frame.add(Panel1);
        //frame.setResizable(false);
        frame.setVisible(true);
        //Panel1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        Panel1.setVisible(true);
        
       // System.out.println(getSuggestions("Hullo",5));
        textField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				//textArea.setText(textField.getText());
				String text=textField.getText();
				if(text.contains(" ")){
					String[] bits = text.split(" ");
					text=bits[bits.length-1];
				}
				//System.out.println(getSuggestions(text, 5));
				if(!text.equals("")){
					if(getSuggestions(text, 5).size()>=3){	
						Label1.setText(getSuggestions(text, 5).toArray()[0].toString());
						Label2.setText(getSuggestions(text, 5).toArray()[1].toString());
						Label3.setText(getSuggestions(text, 5).toArray()[2].toString());
					
					}
				}
				else{
					textField.setText(null);
				}
				textField.setCaretPosition(textField.getText().length());
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				//textArea.setText(textField.getText());
				String text=textField.getText();
				if(text.contains(" ")){
					String[] bits = text.split(" ");
					text=bits[bits.length-1];
				}
				//System.out.println(getSuggestions(text, 5));
				if(!text.equals("")){	
				if(getSuggestions(text, 5).size()>=3){	
					Label1.setText(getSuggestions(text, 5).toArray()[0].toString());
			
					Label2.setText(getSuggestions(text, 5).toArray()[1].toString());		
					
					Label3.setText(getSuggestions(text, 5).toArray()[2].toString());
			
					
				}
				//Label4.setText(getSuggestions(text, 5).toArray()[3].toString());
				//Label5.setText(getSuggestions(text, 5).toArray()[4].toString());
				}else{
					textField.setText(null);
				}
				textField.setCaretPosition(textField.getText().length());
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
		
			}
		});
        
    }

    public void actionPerformed(ActionEvent evt) {

    	Object b0=evt.getSource();
    	if(b0==Label1){
    		String temp=Label1.getText();
    		String text=textField.getText();
			if(!text.contains(" ")){
				textField.setText(temp);
			}
			if(text.contains(" ")){
				String[] bits = text.split(" ");
				text=bits[bits.length-1];
				bits[bits.length-1]=temp;
				String text1="";
				for(int i=0;i<bits.length;i++){
					text1=text1.concat(bits[i]);
					text1=text1.concat(" ");
				
				}
				textField.setText(text1);
				//System.out.println(text1);
			}	
    	}
    	if(b0==Label2){
    		String temp=Label2.getText();
    		String text=textField.getText();
			if(!text.contains(" ")){
				textField.setText(temp);
			}
			if(text.contains(" ")){
				String[] bits = text.split(" ");
				text=bits[bits.length-1];
				bits[bits.length-1]=temp;
				String text1="";
				for(int i=0;i<bits.length;i++){
					text1=text1.concat(bits[i]);
					text1=text1.concat(" ");
				
				}
				textField.setText(text1);
				//System.out.println(text1);
			}	
    	}
    	if(b0==Label3){
    		String temp=Label3.getText();
    		String text=textField.getText();
			if(!text.contains(" ")){
				textField.setText(temp);
			}
			if(text.contains(" ")){
				String[] bits = text.split(" ");
				text=bits[bits.length-1];
				bits[bits.length-1]=temp;
				String text1="";
				for(int i=0;i<bits.length;i++){
					text1=text1.concat(bits[i]);
					text1=text1.concat(" ");
				
				}
				textField.setText(text1);
				//System.out.println(text1);
			}	
    	}
       
    }
    public static void main(String args[]){
    	TypeText obj1=new TypeText();
    }

    public List<String> getSuggestions(String word,int threshold) {

        return spellCheck.getSuggestions(word, threshold);
    }
    
}
