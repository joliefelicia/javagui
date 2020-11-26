package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class tabbedpane extends JFrame implements Runnable{
int price;
    JTabbedPane tabbedPane;
   JPanel flPanel, glPanel, blPanel;
    JComboBox cBox;
    JLabel l2;
    JTable table;
    Thread t=null;
    int hours = 0, minutes = 0, seconds = 0;
    String timeString = "";
    JButton settime,order,selectcolor ;
    JSlider slider;
    String time;


    tabbedpane() {



        flPanel = new JPanel();
        flPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        String flavor[] = {"Vanilla", "Strawberry", "Mocca", "Bublle Gum", "Green Tea"};
        cBox = new JComboBox(flavor);
        flPanel.add(cBox);





        glPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] columnNames = {"size", "price","volume"};
        String[][] ukuran = {{"small", "15.000","200ml"}, {"medium", "20.000","350ml"},{"large","35.000","500ml"}};
        table = new JTable(ukuran, columnNames);
        JScrollPane scrollTable = new JScrollPane(table);
        glPanel.add(scrollTable);


        blPanel = new JPanel();
        blPanel.setLayout(null);
        settime=new JButton();
        settime.setBounds(250,10,100,50);
        blPanel.add(settime);
        t = new Thread(this);
        t.start();
        l2=new JLabel("set volume:");
        l2.setBounds(30,80,100,50);
        blPanel.add(l2);
        slider=new JSlider(200,500);
        slider.setMajorTickSpacing(150);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBounds(100,80,100,50);
        blPanel.add(slider);
        selectcolor=new JButton("set background color");
        selectcolor.addActionListener(new buttonselectcolor());
        selectcolor.setVisible(true);
        selectcolor.setBounds(350,180,200,30);
        blPanel.add(selectcolor);
        order=new JButton("order");
        order.setBounds(450,120,100,30);
        order.addActionListener(new buttonsubmit());
        blPanel.add(order);





        // Membuat tabbed pane dan meletakkan ketiga obyek panel
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("flavor", null, flPanel);
        tabbedPane.addTab("price", null, glPanel);
        tabbedPane.addTab("transaction", null, blPanel);

        // Meletakkan obyek tabbed pane di obyek JFrame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(tabbedPane);


    }


    public static void main(String[] args) {
        // write your code here
        tabbedpane frame = new tabbedpane();
        frame.setTitle("milkshake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);




    }



    @Override
    public void run() {
        try {
            while (true) {

                Calendar cal = Calendar.getInstance();
                hours = cal.get( Calendar.HOUR_OF_DAY );
                if ( hours > 12 ) hours -= 12;
                minutes = cal.get( Calendar.MINUTE );
                seconds = cal.get( Calendar.SECOND );

                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                Date date = cal.getTime();
                timeString = formatter.format( date );

                printTime();


            }
        }
        catch (Exception e) { }
    }
    public void printTime(){
         String time= settime.getText();
        settime.setText(timeString);

    }



    class buttonsubmit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            String[] options = {"yes", "cancel"};
            int jawab = JOptionPane.showOptionDialog(blPanel, "you order at "+time+"\n volume: "+slider.getValue()+" ml\n"+ cBox.getSelectedItem() +" flavor \nAre you confirm your order? ","confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if(jawab==JOptionPane.YES_OPTION){
                if(slider.getValue()==200 ||slider.getValue()==350||slider.getValue()==500) {
                    JOptionPane.showMessageDialog(blPanel, "price is "+price() + " thanks for order, please insert your card for payment");
                }
                else {
                   JOptionPane.showMessageDialog(blPanel,"not available volume ");

                }
            }
            else if(jawab==JOptionPane.NO_OPTION){
               JOptionPane.showMessageDialog(blPanel,"your order is cancel");
            }



        }
    }

    public class buttonselectcolor extends Component implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color initialcolor=Color.RED;
            Color color=JColorChooser.showDialog(this,"Select a color",initialcolor);
            blPanel.setBackground(color);
        }

    }



public int price(){

        if(slider.getValue()==200){
            price = 15000;

        }
        else if(slider.getValue()==350){
           price= 20000;

        }
        else if(slider.getValue()==500){
            price=35000;

        }



        return price;
    }







    }

