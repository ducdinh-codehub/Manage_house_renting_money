/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quanlytiendien;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Duc
 */

public class Interface implements KeyListener{
    int ElectricStandardCount = 3500;
    String ReceiverName = "Nguyen Van A";
    String ReceiverBankAccount = "01234567891011";
    public void setInterface(){
        JFrame MainFrame = new JFrame("Main frame");
        JPanel MainPanel = new JPanel();
        
        JMenuBar MainMenu = new JMenuBar();
        JMenu Menu = new JMenu();
        Menu.setText("Tools");
        JMenuItem ItemMenu1 = new JMenuItem();
        JMenuItem ItemMenu2 = new JMenuItem();
        JMenuItem ItemMenu3 = new JMenuItem();
        
        ItemMenu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setReceiverInformationInterface();
            }
        });
        
        ItemMenu3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setStandardElectricCountInterface(); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        ItemMenu1.setText("List all");
        ItemMenu2.setText("Editting receiver's information");
        ItemMenu3.setText("Configure standard electric count");
        
        Menu.add(ItemMenu1);
        Menu.add(ItemMenu2);
        Menu.add(ItemMenu3);
        MainMenu.add(Menu);
        
        JTextField Date = new JTextField(20);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Date.setText(dtf.format(now));
        Date.setEditable(false);
        JTextField Start_num_elec = new JTextField(20);
        Start_num_elec.addKeyListener(this);
        JTextField End_num_elec = new JTextField(20);
        End_num_elec.addKeyListener(this);
        JTextField Electric = new JTextField(20);
        Electric.addKeyListener(this);
        Electric.setEditable(false);
        Electric.setText("......");
        JTextField Water = new JTextField(20);
        Water.addKeyListener(this);
        Water.setText("80000");
        JTextField Internet = new JTextField(20);
        Internet.addKeyListener(this);
        Internet.setText("80000");
        JTextField Gabarge = new JTextField(20);
        Gabarge.addKeyListener(this);
        Gabarge.setText("10000");
        GridBagLayout layout = new GridBagLayout();
        MainPanel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JButton submitBtb = new JButton();
        submitBtb.setText("Save");
        submitBtb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int startNum = Integer.parseInt(Start_num_elec.getText());
                int endNum = Integer.parseInt(End_num_elec.getText());
                int totalNum = endNum - startNum;
                int totalElectricMoney = ElectricStandardCount * totalNum;
                int totalMoney = totalElectricMoney + Integer.parseInt(Water.getText()) + Integer.parseInt(Internet.getText()) + Integer.parseInt(Gabarge.getText());
                Electric.setText(String.valueOf(totalElectricMoney));
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int rst = JOptionPane.showConfirmDialog(null, "All information will be saved, are you sure ?","Warning",dialogButton);
                if(rst == JOptionPane.YES_OPTION){
                    BufferedWriter bw = null;
                    try {
                        JOptionPane.showMessageDialog(null, "Saving success");
                        File f = new File("source.htm");
                        bw = new BufferedWriter(new FileWriter(f));
                        bw.write("<html><body><h1>Renting House Bill</h1>");
                        bw.write("<br>Date: "+Date.getText());
                        bw.write("<br>Internet money: "+Internet.getText());
                        bw.write("<br>Internet money: "+Water.getText());
                        bw.write("<h3>Electric bill ("+String.valueOf(ElectricStandardCount)+" vnd per number):</h3>");
                        bw.write("<br><ul>");
                        bw.write("<li>Start count number: "+Start_num_elec.getText()+"</li>");
                        bw.write("<li>End count number: "+End_num_elec.getText()+"</li>");
                        bw.write("</ul>");
                        bw.write("<br>Total count number: "+String.valueOf(totalNum));
                        bw.write("<br>Total electric money: "+String.valueOf(totalElectricMoney));
                        bw.write("<br>Gabarge money: "+Gabarge.getText());
                        bw.write("<h3>Total money: "+String.valueOf(totalMoney)+"</h3>");
                        bw.write("<br>Receiver name: "+ReceiverName);
                        bw.write("<br>Receiver bank account: "+ReceiverBankAccount);
                        bw.write("<br><img src='./Test_signature.jpg'>");
                        bw.write("</body></html>");
                        bw.close();
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            bw.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
            }
        });
        
        JLabel Start_num_elec_label = new JLabel();
        Start_num_elec_label.setText("Start count number");
        JLabel End_num_elec_label = new JLabel();
        End_num_elec_label.setText("End count number");
        JLabel Date_label = new JLabel();
        Date_label.setText("Date");
        JLabel Electric_label = new JLabel();
        Electric_label.setText("Electric money");
        JLabel Water_label = new JLabel();
        Water_label.setText("Water money");
        JLabel Internet_label = new JLabel();
        Internet_label.setText("Internet money");
        JLabel Gabarge_label = new JLabel();
        Gabarge_label.setText("Gabarge money");
        
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        MainPanel.add(Date_label, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        MainPanel.add(Date, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        MainPanel.add(Internet_label, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        MainPanel.add(Internet, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        MainPanel.add(Water_label, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        MainPanel.add(Water, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        MainPanel.add(Start_num_elec_label, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        MainPanel.add(Start_num_elec, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        MainPanel.add(End_num_elec_label,gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        MainPanel.add(End_num_elec,gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        MainPanel.add(Electric_label, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        MainPanel.add(Electric, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        MainPanel.add(Gabarge_label, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        MainPanel.add(Gabarge, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        MainPanel.add(submitBtb, gbc);
        
        MainFrame.setJMenuBar(MainMenu);
        MainFrame.add(MainPanel);
        MainFrame.setSize(350, 400);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setVisible(true);
        
        
    }
    
    public void setReceiverInformationInterface(){
        JFrame SubFrame2 = new JFrame("");
        JPanel SubPanel2 = new JPanel();
        JLabel label_name_receiver = new JLabel();
        label_name_receiver.setText("Receiver's name");
        JTextField name_receiver = new JTextField(15);
        name_receiver.setEditable(false);
        JLabel label_receiver_bank_account = new JLabel();
        label_receiver_bank_account.setText("Receiver's banck acc");
        JTextField receiver_bank_Account = new JTextField(15);
        receiver_bank_Account.setEditable(false);
        
        JLabel label_new_name_receiver = new JLabel();
        label_new_name_receiver.setText("Receiver's name");
        JTextField new_name_receiver = new JTextField(15);
        JLabel label_new_receiver_bank_account = new JLabel();
        label_new_receiver_bank_account.setText("Receiver's banck acc");
        JTextField new_receiver_bank_Account = new JTextField(15);
       
        new_receiver_bank_Account.addKeyListener(this);
        
        GridBagLayout SubLayout2 = new GridBagLayout();
        SubPanel2.setLayout(SubLayout2);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        name_receiver.setText(ReceiverName);
        receiver_bank_Account.setText(ReceiverBankAccount);
        
        JButton subSubmitBtn2 = new JButton();
        subSubmitBtn2.setText("Save");
        subSubmitBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int rst = JOptionPane.showConfirmDialog(null, "All information will be saved, are you sure ?", "Warning", dialogButton);
                if(rst == JOptionPane.YES_OPTION){
                    name_receiver.setText(new_name_receiver.getText());
                    receiver_bank_Account.setText(new_receiver_bank_Account.getText());
                    ReceiverName = new_name_receiver.getText();
                    ReceiverBankAccount = new_receiver_bank_Account.getText();
                    new_name_receiver.setText("");
                    new_receiver_bank_Account.setText("");
                }
            }
        });
        
        
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        SubPanel2.add(label_name_receiver, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        SubPanel2.add(name_receiver, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        SubPanel2.add(label_receiver_bank_account, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        SubPanel2.add(receiver_bank_Account,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        SubPanel2.add(label_new_name_receiver,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        SubPanel2.add(new_name_receiver, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        SubPanel2.add(label_new_receiver_bank_account,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        SubPanel2.add(new_receiver_bank_Account, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        SubPanel2.add(subSubmitBtn2, gbc);
        
        SubFrame2.add(SubPanel2);
        SubFrame2.setSize(350, 400);
        SubFrame2.setVisible(true);
    }
    
    public void setStandardElectricCountInterface(){
            JFrame SubFrame = new JFrame("Sub frame");
            JPanel SubPanel = new JPanel();
            JLabel label_std_elec_count = new JLabel();
            label_std_elec_count.setText("Typing new standard count");
            JTextField std_elec_count = new JTextField(15);
            std_elec_count.addKeyListener(this);
            JLabel label_std_count = new JLabel();
            label_std_count.setText("Standard count");
            JTextField std_count = new JTextField(15);
            std_count.setText(String.valueOf(ElectricStandardCount));
            std_count.setEditable(false);
            
            JButton subSubmitBtn = new JButton();
            subSubmitBtn.setText("Save");
            GridBagLayout Sublayout = new GridBagLayout();
            SubPanel.setLayout(Sublayout);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            
            gbc.gridx = 0;
            gbc.gridy = 0;            
            SubPanel.add(label_std_count, gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;            
            SubPanel.add(std_count, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;            
            SubPanel.add(label_std_elec_count, gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            SubPanel.add(std_elec_count, gbc);
            gbc.gridx = 1;
            gbc.gridy = 2;
            SubPanel.add(subSubmitBtn, gbc);
            subSubmitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int rst = JOptionPane.showConfirmDialog(null,"The information will be saved, are you sure ? ","Warning", dialogButton);
                    if(rst == JOptionPane.YES_OPTION){
                        ElectricStandardCount = Integer.parseInt(std_elec_count.getText());
                        std_count.setText(String.valueOf(ElectricStandardCount));
                        std_elec_count.setText("");
                    }
                }
            });
            SubFrame.add(SubPanel);
            SubFrame.setSize(350, 400);
            SubFrame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)){
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
