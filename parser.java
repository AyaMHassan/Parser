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
    public static void main(String[] args) {
        window1 win1 = new window1();

    }

}


