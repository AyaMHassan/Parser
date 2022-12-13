package com.company;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Hashtable;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxStylesheet;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;
import java.io.*;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Stack; 

class Token {
    String token_value;
    String token_type;
    public Token(){}
    public Token(String v, String t)
    {
        token_value = v;
        token_type = t;
    }
}

class Node {
    String name;
    String value;
    ArrayList<Node> children=new ArrayList<Node>();
    Node sibling;
    String type;
    public Node(){}
    public Node(String n, String v, ArrayList<Node> c, Node s, String t)
    {
        name = n;
        value = v;
        children = c;
        sibling = s;
        type = t;
    }
}

class window1 implements ActionListener{
    JButton jbtn1, jbtn2;
    JFrame frame;
    window1(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Parser");
        frame.setSize(250,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        panel.setBorder(new EmptyBorder(new Insets(100, 150, 100, 150)));
        jbtn1 = new JButton("Enter the source code");
        jbtn1.addActionListener(this);
        jbtn2 = new JButton("Enter the tokens list");
        jbtn2.addActionListener(this);
        panel.add(jbtn1);
        panel.add(jbtn2);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jbtn1){
            frame.dispose();
            window2 win2 = new window2();
        }
        else if(e.getSource() == jbtn2){
            frame.dispose();
            window2_token win2 = new window2_token();
        }
    }
}
class window2 implements ActionListener{
    JFrame frame;
    JButton jbtn1, jbtn2;
    JTextField text;
    JTextArea display;
    JScrollPane scroll;
    String code;
    window2(){
        frame = new JFrame("Parser");
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        panel.setPreferredSize(new Dimension(600,600));
        jbtn1 = new JButton("Scan and Parse");
        jbtn1.addActionListener(this);
        display = new JTextArea(600, 300);
        display.setEditable(true); // set textArea non-editable
        scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scroll);
        jbtn2 = new JButton("new");
        jbtn2.addActionListener(this);
        panel.add(jbtn1);
        panel.add(jbtn2);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        code = display.getText();
        if(e.getSource() == jbtn1){
            frame.dispose();
            window3 win3 = new window3(code,0);
        }
        else if(e.getSource() == jbtn2){
            frame.dispose();
            window1 win1 = new window1();
        }
    }
}
class window2_token implements ActionListener{
    JFrame frame;
    JButton jbtn1, jbtn2;
    JTextArea display;
    JScrollPane scroll;
    String token;
    window2_token(){
        frame = new JFrame("token parser");
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        panel.setPreferredSize(new Dimension(600,600));
        jbtn1 = new JButton("Scan and Parse");
        jbtn1.addActionListener(this);
        display = new JTextArea(600, 300);
        display.setEditable(true); // set textArea non-editable
        scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scroll);
        jbtn2 = new JButton("new");
        jbtn2.addActionListener(this);
        panel.add(jbtn1);
        panel.add(jbtn2);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        token = display.getText();
        if(e.getSource() == jbtn1){
            frame.dispose();
            window3 win3 = new window3(token,1);
        }
        else if(e.getSource() == jbtn2){
            frame.dispose();
            window1 win1 = new window1();
        }
    }
}
class window3 implements ActionListener{
    JButton jbtn1;
    JFrame frame;
    JTextArea display;
    JScrollPane scroll1;
    JScrollPane scroll2;
    String code_token;
    int flag;
    window3(String code_token, int flag){
        this.code_token = code_token;
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Parser");
        frame.setSize(600,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        panel.setSize(600,1000);
        jbtn1 = new JButton("new");
        jbtn1.addActionListener(this);
        display = new JTextArea();
        display.setEditable(false); // set textArea non-editable
        this.flag = flag;
        if(flag == 0){
//             display.setText();
        }
        else if (flag == 1){
           display.setText(code_token);
        }
//        mxGraph graph = new mxGraph();
//        Object parent = graph.getDefaultParent();
//        graph.getModel().beginUpdate();
//        try {
//            Object v1 = graph.insertVertex(parent, null, "Hello",  20, 20, 80, 30 , "shape=ellipse");
//            Object v2 = graph.insertVertex(parent, null, "World!", 240, 150,
//                    80, 30);
//            Object v3 = graph.insertVertex(parent, null, "World!", 210, 250,
//                    50, 30);
//            Object v4 = graph.insertVertex(parent, null, "World!", 270, 250,
//                    50, 30, "shape=ellipse");
//            graph.insertEdge(parent, null, "", v1, v2);
//            graph.insertEdge(parent, null, "", v2, v3);
//            graph.insertEdge(parent, null, "", v2, v4);
//        } finally {
//            graph.getModel().endUpdate();
//        }
//        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        scroll2 = new JScrollPane(graphComponent);
        scroll2.setMinimumSize(new Dimension(600, 600));
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scroll2);
        scroll1 = new JScrollPane(display);
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll2.setMaximumSize(new Dimension(600, 200));
        panel.add(scroll1);
        panel.add(jbtn1);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jbtn1){
            window1 win1 = new window1();
        }
    }
}
public class Main{
    static String code;
    static String token;
    static Stack<Token> scanner_output = new Stack<>();  
    public static void main(String[] args) {
        window1 win1 = new window1();

    }
    public static ArrayList<String> scanner(String s) {
        ArrayList<String> arr = new ArrayList<String>();
        String program=s;
        String substate = "";
        int len = program.length();
        String state = "START";
        String token = "";
        String type = "";
        for (int i = 0; i < len; i++) {
            if (state == "START") {
                 if(program.charAt(i) == 'i'){
                    state = "INRESERVEDWORDS";
                    substate = "IF";
                    i = i-1;
                }
                else if(program.charAt(i) == 't'){
                    state = "INRESERVEDWORDS";
                    substate = "THEN";
                    i = i-1;
                }
                else if(program.charAt(i) == 'e') {
                    if (i + 1 < len) {
                        if (program.charAt(i + 1) == 'l') {
                            state = "INRESERVEDWORDS";
                            substate = "ELSE";
                            i = i - 1;
                        } else if (program.charAt(i + 1) == 'n') {
                            state = "INRESERVEDWORDS";
                            substate = "END";
                            i = i - 1;
                        } else {
                            state = "INID";
                            i = i - 1;
                        }
                    }
                    else {
                        state = "INID";
                        i = i - 1;
                    }

                }
                else if(program.charAt(i) == 'r'){
                    if(i+2 < len){
                        if(program.charAt(i+1) == 'e' ){
                            if(program.charAt(i+2) == 'p'){
                                state = "INRESERVEDWORDS";
                                substate = "REPEAT";
                                i = i-1;
                            }
                            else if(program.charAt(i+2) == 'a'){
                                state = "INRESERVEDWORDS";
                                substate = "READ";
                                i = i-1;
                            }
                            else{
                                state = "INID";
                                i = i-1;
                            }
                        }
                        else{
                            state = "INID";
                            i = i-1;
                        }

                        }
                    else{
                        state = "INID";
                        i = i-1;
                    }
                    }



                else if(program.charAt(i) == 'u'){
                    state = "INRESERVEDWORDS";
                    substate = "UNTIL";
                    i = i-1;
                }
                else if(program.charAt(i) == 'w'){
                    state = "INRESERVEDWORDS";
                    substate = "WRITE";
                    i = i-1;
                }
                
                else if ((program.charAt(i) == ';') || (program.charAt(i) == '<') || (program.charAt(i) == '=') || (program.charAt(i) == '+') || (program.charAt(i) == '-') || (program.charAt(i) == '*') || (program.charAt(i) == '/') || (program.charAt(i) == '(') || (program.charAt(i) == ')')) {
                    state = "INSYMBOL";
                    i = i - 1;
                } else if (program.charAt(i) == '{') {
                    state = "INCOMMENT";
                    i = i - 1;
                }else if (program.charAt(i) == ':') {
                    state = "INASSIGN";
                    i = i - 1;
                }else if(((program.charAt(i)>='a')&&(program.charAt(i)<='z'))||((program.charAt(i)>='A')&&(program.charAt(i)<='Z'))){
                    state="INID";
                    i=i-1;
                }
                else if((program.charAt(i)>='0')&&(program.charAt(i)<='9'))
                {
                    state="INNUM";
                    i=i-1;
                }

            }
                           else if (state == "INRESERVEDWORDS"){
                if(substate == "IF"){
                    if(i + 2 <= len) {
                        if (program.substring(i, i + 2).equals("if")) {
                                token += "if";
                                if (program.charAt(i + 2) == '\n' || program.charAt(i + 2) == ' ' || program.charAt(i + 2) == '\t' ) {
                                    state = "DONE";
                                    type = substate;
                                    i = i + 2;
                                }
                                else if(program.charAt(i + 2) == '(' || program.charAt(i + 2) == '{'){
                                    state = "DONE";
                                    type = substate;
                                    i = i + 1;
                                }
                                else {
                                    state = "INID";
                                    i = i + 1;
                                }
                        }
                        else{
                            i = i - 1;
                            state = "INID";
                        }
                    }
                    else{
                        i = i - 1;
                        state = "INID";
                    }
                }
                else if (substate == "THEN"){
                    if(i+4 <= len) {
                        if (program.substring(i, i + 4).equals("then")) {
                            token += "then";
                            if (program.charAt(i + 4) == '\n' || program.charAt(i + 4) == ' ' || program.charAt(i + 4) == '\t') {
                                state = "DONE";
                                type = substate;
                                i = i + 4;
                            }
                            else if(program.charAt(i + 4) == '{'){
                                state = "DONE";
                                type = substate;
                                i = i + 3;
                            }
                            else {
                                state = "INID";
                                i = i + 3;
                            }
                        }
                        else{
                            i = i - 1;
                            state = "INID";
                        }
                    }
                    else{
                        i = i - 1;
                        state = "INID";
                    }
                }
                else if (substate == "END"){
                    if(i+3 <= len){
                        if(program.substring(i,i+3).equals("end")) {
                            token += "end";
                            if(i+3 == len){
                                state = "DONE";
                                type = "END";
                                i = i + 1;
                            }
                            else if (program.charAt(i + 3) == '\n' || program.charAt(i + 3) == ' ' || program.charAt(i + 3) == '\t') {
                                state = "DONE";
                                type = "END";
                                if(i + 4 < len){
                                    i = i + 3;
                                }
                                else {
                                    i = i + 2;
                                }
                            }
                            else if(program.charAt(i + 3) == '{'){
                                state = "DONE";
                                type = "END";
                                i = i + 2;
                            }
                            else {
                                state = "INID";
                                i = i + 2;
                            }
                            continue;
                        }
                        else{
                            i = i - 1;
                            state = "INID";
                            continue;
                        }
                    }
                    else{
                        i = i - 1;
                        state = "INID";
                        continue;
                    }
             
                }
                else if (substate == "ELSE"){
                    if(i+4 < len){
                        if(program.substring(i,i+4).equals("else")){
                            token+="else";
                            if(program.charAt(i+4) == '\n' || program.charAt(i+4) == ' ' || program.charAt(i+4) == '\t'){
                                state = "DONE";
                                type = "ELSE";
                                i = i + 4;
                            }
                            else if(program.charAt(i + 4) == '{'){
                                state = "DONE";
                                type = "ELSE";
                                i = i + 3;
                            }
                            else{
                                state = "INID";
                                i = i + 3;
                            }
                            continue;
                        }
                        else{
                            i = i - 1;
                            state = "INID";
                            continue;
                        }
                    }
                    else{
                        i = i - 1;
                        state = "INID";
                        continue;
                    }
                }
                else if (substate == "REPEAT"){
                    if(i+6 < len){
                        if(program.substring(i,i+6).equals("repeat")){
                            token+="repeat";
                            if(program.charAt(i+6) == '\n' || program.charAt(i+6) == ' ' || program.charAt(i+6) == '\t'){
                                state = "DONE";
                                type = "REPEAT";
                                i = i + 6;
                            }
                            else if(program.charAt(i + 6) == '{'){
                                state = "DONE";
                                type = "REPEAT";
                                i = i + 5;
                            }
                            else{
                                state = "INID";
                                i = i + 5;
                            }
                            continue;
                        }
                        else{
                            i = i - 1;
                            state = "INID";
                            continue;
                        }
                    }
                    else{
                        i = i - 1;
                        state = "INID";
                        continue;
                    }

                }
                else if (substate == "READ"){
                     if(i+4 < len){
                        if(program.substring(i,i+4).equals("read")){
                            token+="read";
                            if(program.charAt(i+4) == '\n' || program.charAt(i+4) == ' ' || program.charAt(i+4) == '\t'){
                                state = "DONE";
                                type = "READ";
                                i = i + 4;
                            }
                            else if(program.charAt(i + 4) == '{'){
                                state = "DONE";
                                type = "READ";
                                i = i + 3;
                            }
                            else{
                                state = "INID";
                                i = i + 3;
                            }
                            continue;
                        }
                        else{
                            i = i - 1;
                            state = "INID";
                            continue;
                        }
                    }
                     else{
                         i = i - 1;
                         state = "INID";
                         continue;
                     }
                }
                

                else if(substate == "UNTIL"){
                    if(i+5 < len){
                        if(program.substring(i,i+5).equals("until")){
                            token+="until";
                            if(program.charAt(i+5) == '\n' || program.charAt(i+5) == ' ' || program.charAt(i+5) == '\t'){
                                state = "DONE";
                                type = substate;
                                i = i + 5;
                            }
                            else if(program.charAt(i + 5) == '(' || program.charAt(i + 5) == '{'){
                                state = "DONE";
                                type = substate;
                                i = i + 4;
                            }
                            else{
                                state = "INID";
                                i = i + 4;
                            }
                        }
                        else{
                            i = i - 1;
                            state = "INID";
                        }
                    }
                    else{
                        i = i - 1;
                        state = "INID";
                    }
                }
                else if(substate == "WRITE"){
                    if(i+5 < len){
                        if(program.substring(i,i+5).equals("write")){
                            token+="write";
                            if(program.charAt(i+5) == '\n' || program.charAt(i+5) == ' ' || program.charAt(i+5) == '\t' ){
                                state = "DONE";
                                type = substate;
                                i = i + 5;
                            }
                            else if(program.charAt(i + 5) == '(' || program.charAt(i + 5) == '{'){
                                state = "DONE";
                                type = substate;
                                i = i + 4;
                            }
                            else{
                                state = "INID";
                                i = i + 4;
                            }
                        }
                        else{
                            i = i - 1;
                            state = "INID";
                        }
                    }
                    else{
                        i = i - 1;
                        state = "INID";
                    }
                }
            }
             else if (state == "INNUM") {
                if((program.charAt(i)>='0')&&(program.charAt(i)<='9')){
                    token = token + program.charAt(i);
                    type="NUMBER";
                }
                else {
                    i = i - 1;
                    state="DONE";
                }
            } else if (state == "INID") {
                  if(((program.charAt(i)>='a')&&(program.charAt(i)<='z'))||((program.charAt(i)>='A')&&(program.charAt(i)<='Z'))){
                    token+=program.charAt(i);
                    type="IDENTIFIER";
                }
                else {
                    i=i-1;
                    state="DONE";
                }

            } else if (state == "INASSIGN") {
                
                if((program.charAt(i)==':') &&(program.charAt(i+1)=='=')){
                    i=i+1;
                    token+=":=";
                    type="ASSIGN";
                    state="DONE";
                }

            } else if (state == "INSYMBOL") {
                
                if(program.charAt(i)==';') {
                    token+=program.charAt(i);
                    type="SEMICOLON";
                    state="DONE";
                }
                else if(program.charAt(i)=='<'){
                    token+=program.charAt(i);
                    type="LESSTHAN";
                    state="DONE";
                }
                else if(program.charAt(i)=='=') {
                    token+=program.charAt(i);
                    type="EQUAL";
                    state="DONE";
                }
                else if(program.charAt(i)=='+') {
                    token+=program.charAt(i);
                    type="PLUS";
                    state="DONE";
                }
                else if(program.charAt(i)=='-') {
                    token+=program.charAt(i);
                    type="MINUS";
                    state="DONE";
                }
                else if(program.charAt(i)=='*') {
                    token+=program.charAt(i);
                    type="MULT";
                    state="DONE";
                }else if(program.charAt(i)=='/') {
                    token+=program.charAt(i);
                    type="DIV";
                    state="DONE";
                }else if(program.charAt(i)=='(') {
                    token+=program.charAt(i);
                    type="OPENBRACKET";
                    state="DONE";
                }else if(program.charAt(i)==')') {
                    token+=program.charAt(i);
                    type="CLOSEDBRACKET";
                    state="DONE";
                }

            } else if (state == "DONE") {
                String temp=token+" , "+type+"\n";
                arr.add(temp);
                token="";
                type="";
                state="START";
                i=i-1;

            } else if (state == "INCOMMENT") {
                int j;
                for( j=i;j<len;j++){
                    if(program.charAt(j)=='}') break;
                }
                i=j;
                state="START";
            }
        }
        return arr;
    }
      public static String read(String path) throws FileNotFoundException, IOException {
        String program = "";
        // File path is passed as parameter
        File file = new File(path);
        // Creating an object of BufferedReader class
        BufferedReader br
            = new BufferedReader(new FileReader(file));
 
        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null)
            program = program + st + '\n';
//        System.out.println(program);
        br.close();
        return program;
  }
   public static Node parser(ArrayList<String> input)
   {
       for (int i = input.size() - 1; i >= 0  ; i--)
       {
           String[] tv = input.get(i).split(" , "); //[value, type]
           Token t = new Token(tv[0], tv[1].substring(0, tv[1].length() - 1));
           scanner_output.push(t);
       }
       Node m=stmt_sequence();
      return m; 
   }
   public static Node match(Token expected_token)
   {
       Node n = new Node();
       if((!scanner_output.empty()) &&(scanner_output.peek().token_type.equals(expected_token.token_type)))
       {
           scanner_output.pop();
           n.name = "accept";
       }
       else
       {
           n.name = "error";
       }
       return n;
   }
    public static Node stmt_sequence(){
        Node n=new Node();
        
        n = statement();
        Node temp=n;
       if (n.name.equals("error")) {
           return new Node("error", null, null, null, null);
       }
              

       while ((!scanner_output.empty()) &&(scanner_output.peek().token_type.equals("SEMICOLON"))) {
           if (match(new Token(";", "SEMICOLON")).name.equals("error")) {
               return new Node("error", null, null, null, null);
           }
           Node s=statement();
           if ( s.name.equals("error")) {
           return new Node("error", null, null, null, null);
           }
           
           temp.sibling=s;
           temp=s;

       }
           
       return n;
    }
   public static Node statement(){
        Node n=new Node();
        if((!scanner_output.empty())&& (scanner_output.peek().token_type.equals("IF"))){
            n=if_stmt();
            if ( n.name.equals("error")) {
           return new Node("error", null, null, null, null);
           }            
        }
        else if((!scanner_output.empty())&& (scanner_output.peek().token_type.equals("REPEAT"))){
            n=repeat_stmt();
            if ( n.name.equals("error")) {
           return new Node("error", null, null, null, null);
           }            
        }
        else if((!scanner_output.empty())&& (scanner_output.peek().token_type.equals("IDENTIFIER"))){
            n=assign_stmt();
            if ( n.name.equals("error")) {
           return new Node("error", null, null, null, null);
           }            
        }
        else if((!scanner_output.empty())&& (scanner_output.peek().token_type.equals("READ"))){
            n=read_stmt();
            if ( n.name.equals("error")) {
           return new Node("error", null, null, null, null);
           }            
        }
        else if((!scanner_output.empty())&& (scanner_output.peek().token_type.equals("WRITE"))){
            n=write_stmt();
            if ( n.name.equals("error")) {
           return new Node("error", null, null, null, null);
           }            
        }
        else{
            return new Node("error", null, null, null, null);            
        }
        

        return n;
    }
   public static Node if_stmt(){
        Node n=new Node();
        if (match(new Token("if", "IF")).name.equals("error")) {
               return new Node("error", null, null, null, null);
           }
        n.name="if";
        Node e=exp();
        if ( e.name.equals("error")) {
           return new Node("error", null, null, null, null);
           } 
        n.children.add(e);
        if (match(new Token("then", "THEN")).name.equals("error")) {
               return new Node("error", null, null, null, null);
           }
        Node s=stmt_sequence();
        if ( s.name.equals("error")) {
           return new Node("error", null, null, null, null);
           } 
        n.children.add(s);
        if((!scanner_output.empty())&& (scanner_output.peek().token_type.equals("ELSE"))){
           if (match(new Token("else", "ELSE")).name.equals("error")) {
               return new Node("error", null, null, null, null);
           }
           Node el=stmt_sequence();
            if (el.name.equals("error")) {
                return new Node("error", null, null, null, null);
            }
            n.children.add(el);
           
        }
        if (match(new Token("end", "END")).name.equals("error")) {
               return new Node("error", null, null, null, null);
           }
        n.type="stmt";

        return n;
    }
   
   public static Node repeat_stmt(){
        Node n=new Node();
        if (match(new Token("repeat", "REPEAT")).name.equals("error")) {
               return new Node("error", null, null, null, null);
           }
        n.name="repeat";
        Node s=stmt_sequence();
        if ( s.name.equals("error")) {
           return new Node("error", null, null, null, null);
           } 
        n.children.add(s);
        if (match(new Token("until", "UNTIL")).name.equals("error")) {
               return new Node("error", null, null, null, null);
           }
         Node e=exp();
        if ( e.name.equals("error")) {
           return new Node("error", null, null, null, null);
           }
        n.children.add(e);
        n.type="stmt";

        return n;
    }
   public static Node assign_stmt(){
        Node n=new Node();
        Token t=new Token();
        if(!scanner_output.empty())
            t=scanner_output.peek();
        if (match(new Token(null, "IDENTIFIER")).name.equals("error")) {
               return new Node("error", null, null, null, null);
           }
        n.name="assign";
        n.value=t.token_value;
        if (match(new Token(":=", "ASSIGN")).name.equals("error")) {
               return new Node("error", null, null, null, null);
           }
        Node e=exp();
        if ( e.name.equals("error")) {
           return new Node("error", null, null, null, null);
           } 
        n.children.add(e);
        n.type="stmt";
        return n;
    }
    public static Node exp()
   {
       Node left = simple_exp();
       if((left.name).equals("error"))
        {
           left.name = "error";
            return left;
        }
       Node root = left;
       while((!scanner_output.empty()) && ((scanner_output.peek().token_value.equals("<")) || (scanner_output.peek().token_value.equals("="))))
       {
           Node nroot = new Node();
           String v = scanner_output.peek().token_value;
           nroot = match(scanner_output.peek());
           if((nroot.name).equals("error"))
            {
               nroot.name = "error";
                return nroot;
            }
           nroot = new Node("op", v,  new ArrayList<Node>(),null, "exp");
           Node right = simple_exp();
           if((right.name).equals("error"))
            {
               right.name = "error";
                return right;
            }
           nroot.children.add(root);
           nroot.children.add(right);
           root = nroot;
       }
       return root;
   }
   public static Node simple_exp()
   {
       Node left = term();
       if((left.name).equals("error"))
        {
           left.name = "error";
            return left;
        }
       Node root = left;
       while((!scanner_output.empty()) && ((scanner_output.peek().token_value.equals("+")) || (scanner_output.peek().token_value.equals("-"))))
       {
           Node nroot = new Node();
           String v = scanner_output.peek().token_value;
           //System.out.println("here 2");
           nroot = match(scanner_output.peek());
           //System.out.println("after: " +scanner_output.peek().token_value);
           if((nroot.name).equals("error"))
            {
               nroot.name = "error";
                return nroot;
            }
           nroot = new Node("op", v, new ArrayList<Node>(),null, "exp");
           Node right = term();
           if((right.name).equals("error"))
            {
               right.name = "error";
                return right;
            }
           nroot.children.add(root);
           nroot.children.add(right);
           root = nroot;
       }
       return root;
   }
   public static Node term()
   {
       Node left = factor();
       System.out.println(left.name);
       if((left.name).equals("error"))
        {
           left.name = "error";
            return left;
        }
       Node root = left;
       while((!scanner_output.empty()) && ((scanner_output.peek().token_value.equals("*")) || (scanner_output.peek().token_value.equals("/"))))
       {
           Node nroot;
           String v = scanner_output.peek().token_value;
           nroot = match(scanner_output.peek());
           if((nroot.name).equals("error"))
            {
               nroot.name = "error";
                return nroot;
            }
           nroot = new Node("op", v, new ArrayList<Node>(),null, "exp");
           Node right = factor();
           if((right.name).equals("error"))
            {
               right.name = "error";
                return right;
            }
           nroot.children.add(root);
           nroot.children.add(right);
           root = nroot;
       }
       return root;
   }
   public static Node factor()
   {
       Node root = new Node();
       root.name = "error";
       if(!scanner_output.empty())
       {
        if(scanner_output.peek().token_value.equals("("))
        {
            match(scanner_output.peek());
            root = exp();
            if((root.name).equals("error"))
             {
                root.name = "error";
                 return root;
             }
            Node n = match(new Token(")","CLOSEDBRACKET"));
            if((n.name).equals("error"))
             {
                n.name = "error";
                 return n;
             }
        }
        else if(scanner_output.peek().token_type.equals("NUMBER"))
        {
            //System.out.println("here 1");
            String v = scanner_output.peek().token_value;
            match(scanner_output.peek());
            //System.out.println("after 1 :"+ scanner_output.peek().token_value);
            root.name = "const";
            root.value = v;
            root.children = null;
            root.sibling = null;
            root.type = "exp";
        }
        else if(scanner_output.peek().token_type.equals("IDENTIFIER"))
        {
            String v = scanner_output.peek().token_value;
            match(scanner_output.peek());
            root.name = "id";
            root.value = v;
            root.children = null;
            root.sibling = null;
            root.type = "exp";
        }
        else {
            root.name = "error";
        }
   }
       return root;
   }
   public static Node read_stmt()
   {
        Node n  = new Node();
        n.name = "error";
        n = match(new Token("read","READ"));
        if((n.name).equals("error"))
         {
            n.name = "error";
             return n;
         }
        String v = "";
        if(!scanner_output.empty())
        {
            System.out.println(scanner_output.peek().token_value);
            v = scanner_output.peek().token_value;
        }
        n = match(new Token("","IDENTIFIER"));
        if((n.name).equals("error"))
         {
            n.name = "error";
             return n;
         }
        n = new Node("read", v, null, null, "stmt");
       return n;
   }
      public static Node write_stmt()
   {
        Node root  = new Node();
        root.name = "error";
        root = match(new Token("write","WRITE"));
        if((root.name).equals("error"))
         {
            root.name = "error";
             return root;
         }
        Node n = exp();
        if((n.name).equals("error"))
         {
            n.name = "error";
             return n;
         }
        root.name = "write";
        root.value = null;
        root.children = new ArrayList<Node>();
        root.children.add(n);
        root.sibling = null;
        root.type = "stmt";
       return root;
   }

}


