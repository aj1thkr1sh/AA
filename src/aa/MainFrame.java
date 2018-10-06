/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aa;

import java.awt.Font;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.StringTokenizer;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
/**
 *
 * @author Krish
 */
public class MainFrame extends javax.swing.JFrame {

    
    public static HashMap<String,String> table=null;
    public static StringTokenizer consoleToken=null;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        Font f=new Font("Arial Unicode MS",Font.BOLD,16);
        writer.setFont(f);
        console.setFont(f);
        table=new HashMap<String,String>();
        
    }
    StringTokenizer token=null;
    void compile(String s) throws IOException{
        console.setText("");
        consoleToken=new StringTokenizer(inputField.getText().toString(),"\n");
        //StringTokenizer token=null;
        token=new StringTokenizer(s,"\n");
        while(token.hasMoreTokens()){
            statementProcessor(token.nextToken());
        }
    }
    
    void statementProcessor(String s) throws IOException{
        if(s.startsWith("வெளியிடுக")){
            //for print
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
            res+="\n";
            console.append(res);
            System.out.println(res);
        }else if(s.endsWith("உள்ளிடுக")){
            //for input
            //String t=consoleToken.nextToken();
            char ch[]=s.toCharArray();
            int j=0;
            String t="";
            while(ch[j]!='='){
                t+=ch[j];
                ++j;
            }
            String sg=consoleToken.nextToken();
            String val=sg;
            table.put(t,val);
            System.out.println("^^^"+table.get(t));
        }else if(s.trim().startsWith("இக்கணம்")){
            
            System.out.println("got");
            StringTokenizer tokenss=new StringTokenizer(s," ");
            tokenss.nextToken();
            String va1,va2,op;
            va1=tokenss.nextToken();
            op=tokenss.nextToken();
            va2=tokenss.nextToken();
            System.out.println("##"+va1+" ##"+va2);
            token.nextToken();
            if(!isNumeric(va1)){
                va1=table.get(va1);
            }
            if(!isNumeric(va2)){
                va2=table.get(va2);
            }
            int value1,value2;
            value1=Integer.parseInt(va1);
            value2=Integer.parseInt(va2);
            String ss="";
            System.out.println("!!!!!!!!!"+value1+" !!!!"+value2);
            if("===".equals(op)){
                if(value1==value2){
                    while(true){
                        ss=token.nextToken();
                        if(ss.equals("முடிவு")){
                            break;
                        }
                        statementProcessor(ss);
                    }
                }
            }else if("~=~".equals(op)){
                if(value1!=value2){
                    while(true){
                        ss=token.nextToken();
                        if(ss.equals("முடிவு")){
                            break;
                        }
                        statementProcessor(ss);
                    }
                }
            }else if(">=".equals(op)){
                if(value1>=value2){
                    while(true){
                        ss=token.nextToken();
                        if(ss.equals("முடிவு")){
                            break;
                        }
                        statementProcessor(ss);
                    }
                }
            }else if("<=".equals(op)){
                if(value1<=value2){
                    while(true){
                       ss=token.nextToken();
                       if(ss.equals("முடிவு")){
                           break;
                       }
                       statementProcessor(ss);
                    }
                }
            }else if(">".equals(op)){
                if(value1>value2){
                    while(true){
                        ss=token.nextToken();
                        if(ss.equals("முடிவு")){
                            break;
                        }
                        statementProcessor(ss);
                    }
                }
            }else if("<".equals(op)){
                if(value1<value2){
                    while(true){
                        ss=token.nextToken();
                        if(ss.equals("முடிவு")){
                            break;
                        }
                        statementProcessor(ss);
                    }
                }
            }else{
                    while(true){
                        ss=token.nextToken();
                        if(ss.equals("முடிவு")){
                            break;
                        }
                    }
                }
        }else if(s.trim().startsWith("சுழற்ச்சி")){
            StringTokenizer tokenss=new StringTokenizer(s," ");
            tokenss.nextToken();
            String va=tokenss.nextToken();
            int value=0;
            System.out.println("******"+va);
            if(!isNumeric(va)){
                va=table.get(va);
            }
            System.out.println("***"+va);
            value=Integer.parseInt(va);
            token.nextToken();
           
            String ss="";
            String sett[]=new String[100];
            int get=0;
            while(true){
                ss=token.nextToken();
                if(ss.trim().equals("முடிவு")){
                    break;
                }
                sett[get++]=ss;
            }
            int ii,jj;
            for(ii=0;ii<value;++ii){
                for(jj=0;jj<get;++jj){
                    statementProcessor(sett[jj]);
                }
            }
        }else if(s.contains("{") && s.contains("}") && s.contains("=")){
            s=s.trim();
            char ch[]=s.toCharArray();
            int i=0;
            String val="";
            while(ch[i]!='='){
                val+=ch[i];
                ++i;
            }
            ++i;
            ++i;
            String val2="";
            while(ch[i]!='}'){
                val2+=ch[i];
                ++i;
            }
            val2=table.get(val2);
            System.out.println(val+"="+val2);
            
        }else if(s.contains("}") && s.contains("{")){
            s=s.trim();
            char[] ch=s.toCharArray();
            int i=0;
            ++i;
            String t="";
            while(ch[i]!='}'){
                t+=ch[i];
                ++i;
            }
            t=table.get(t);
            console.append(t+"\n");
        }else if(s.contains("=") && (s.contains("+") || s.contains("*") || s.contains("/") || s.contains("%"))){
                
            //for operation
            
                s=s.trim();
		char ch[]=s.toCharArray();
		int i=2;
                int j=0;
                String val="";
                while(ch[j]!='='){
                    val+=ch[j];
                    ++j;
                }
		String first="",second="",op="";
		while(!isOperator(ch[i])){
			first+=ch[i];
			++i;
		}
		//System.out.println(first+" index "+i);
		op+=ch[i];
		++i;
		while(i<ch.length && !isLast(ch[i])){
			second+=ch[i];
			++i;
		}
                if(!isNumeric(first)){
                    first=table.get(first);
                }
                if(!isNumeric(second)){
                    second=table.get(second);
                }
		String eval=first+op+second;
                System.out.println("***"+eval);
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("JavaScript");
		try {
			String ans=""+engine.eval(eval);
                        table.put(val,ans);
			System.out.println("ans  : "+ans);
                        System.out.println("table: "+table.get(val));
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
            
        }else if(s.contains("=")){
            StringTokenizer equal=new StringTokenizer(s,"=");
            String st=equal.nextToken();
            table.put(st,equal.nextToken());
            System.out.println("****"+table.get(st));
        }
    }
    
    public static boolean isNumeric(String s){
		return s!=null && s.matches("[-+]?\\d*\\.?\\d+");
	}

    public static boolean isOperator(char ch){
		if(ch=='+'){
			return true;
		}else if(ch=='-'){
			return true;
		}else if(ch=='*'){
			return true;
		}else if(ch=='/'){
			return true;
		}else if(ch=='%'){
			return true;
		}
		return false;
	}
	
	public static boolean isLast(char ch){
		if(ch=='\n'){
			return true;
		}else if(ch=='\0'){
			return true;
		}
		return false;
	}
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        writer = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        inputField = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        check = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        checkButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        writer.setColumns(20);
        writer.setRows(5);
        jScrollPane1.setViewportView(writer);

        console.setColumns(20);
        console.setRows(5);
        jScrollPane2.setViewportView(console);

        inputField.setColumns(20);
        inputField.setRows(5);
        jScrollPane3.setViewportView(inputField);

        check.setColumns(20);
        check.setRows(5);
        jScrollPane4.setViewportView(check);

        jLabel1.setText("Console");

        jLabel2.setText("Input");

        jLabel3.setText("Check");

        checkButton.setText("Check");
        checkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(445, 445, 445)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2)
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(checkButton))
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkButtonActionPerformed
        try {
            // TODO add your handling code here:
            String s=writer.getText().toString();
            check.setText(s);
            
            compile(s);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_checkButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea check;
    private javax.swing.JButton checkButton;
    private javax.swing.JTextArea console;
    private javax.swing.JTextArea inputField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea writer;
    // End of variables declaration//GEN-END:variables
}
