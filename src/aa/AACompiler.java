/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author Krish
 */
public class AACompiler {
    
    FileWriter writer=null;
    BufferedWriter br=null;
    
    void compile(String s) throws IOException{
        
        writer=new FileWriter(new File("outputFile.tx"),true);
        br=new BufferedWriter(writer);
        
        
        StringTokenizer token=new StringTokenizer(s,"\n");
        while(token.hasMoreTokens()){
            statementProcessor(token.nextToken());
        }
        br.close();
    }
    
    void statementProcessor(String s) throws IOException{
        if(s.startsWith("வெளிடுக்க")){
            char ch[]=s.toCharArray();
            int i=0;
            while(ch[i]!='('){
                ++i;
            }
            ++i;
            String res="";
            while(ch[i]!=')'){
                res+=ch[i];
                ++i;
            }
            br.write(res);
            
            System.out.println(res);
        }
    }
    
}
