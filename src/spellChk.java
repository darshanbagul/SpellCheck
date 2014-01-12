import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.event.SpellChecker;



public class spellChk {
	static String bigram="C:/Users/Darshan/workspace1/SpellCheck/w2_.txt";
	static String unigram="C:/Users/Darshan/workspace1/SpellCheck/w1_.txt"; 
	static SpellChecker spellChecker = null;
	public static void main(String args[]) throws Exception{
		
		SpellDictionaryHashMap dictionary = null;
		
		//FileInputStream fstream1 = new FileInputStream(unigram);
		DataInputStream in1;
		BufferedReader br1;
		
		
		//FileInputStream fstream2 = new FileInputStream(bigram);
		DataInputStream in2;
		BufferedReader br2;
		
		try {
			dictionary = new SpellDictionaryHashMap(new File("C:/Users/Darshan/workspace1/SpellCheck/dictionary/english.0"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		spellChecker = new SpellChecker(dictionary);
		
		String str1="Hullo";
		spellChk obj1=new spellChk();
		
		

		String sentence = "Give my a glas off milky";
		String sentence1="Thris irs a suntence";
		String sentence2="How art yu";
		String sentence3="He us all rigt";
		String[] words = sentence2.split(" ");  
		String prevWord=null;
		String tempWord=null;
		List<String> suggestions=new ArrayList<String>();
		ArrayList<String> temp=new ArrayList<String>();
		int i=0;
		
		for (String word : words)  
		{  
			int k=0;
			
			
			suggestions.addAll(obj1.getSuggestions(word, 5));
			//System.out.println(suggestions);
			if(!suggestions.isEmpty()){
				//System.out.println(suggestions.toArray()[0]);
				temp.add(suggestions.toArray()[0].toString());
			}
			
			i++;
			if(i==1){
				prevWord=suggestions.toArray()[0].toString();
				System.out.print(prevWord +" ");
			}
			if(i!=1){
				suggestions.add(word);
				String bestMatch="";
				long max=0;
				long prob;
				long cnt1=0;
				for(int j=0;j<suggestions.size();j++){
					
					FileInputStream fstream2 = new FileInputStream(bigram);
					FileInputStream fstream1 = new FileInputStream(unigram);
					
					in1 = new DataInputStream(fstream1);
					br1 = new BufferedReader(new InputStreamReader(in1));
					in2 = new DataInputStream(fstream2);
					br2 = new BufferedReader(new InputStreamReader(in2));
					
					
					
					tempWord=suggestions.toArray()[j].toString();
					String strLine1;
					String strLine2;
					
					long cnt2=0;
					if(k==0){
					while((strLine1=br1.readLine())!=null){
						if(strLine1.endsWith("\t"+prevWord)){
							//System.out.println(strLine1);
							cnt1=Long.parseLong(strLine1.split("\t")[0]);
						}
					}
					}
					k++;
					while((strLine2=br2.readLine())!=null){
						if(strLine2.endsWith("\t"+prevWord+"\t"+tempWord)){
							//System.out.println(strLine2);
							cnt2=Long.parseLong(strLine2.split("\t")[0]);
						}
					}
					if(cnt2>max){
						max=cnt2;
						bestMatch=tempWord;
						//System.out.println((double)cnt2/cnt1);
					}
					in2.close();
					br2.close();
					in1.close();
					br1.close();
					
				}	
				prevWord=bestMatch;
				System.out.print(bestMatch+" ");
			}
			suggestions.clear();
			
		}  
		
	}
	
	public static List<String> getSuggestions(String word,int threshold) {

	        return spellChecker.getSuggestions(word, threshold);
	    }
}

