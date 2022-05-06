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
import java.nio.charset.StandardCharsets;
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
    String ReceiverBankName = "MB";
    String RentingMoney = "1700000";
    public void setInterface(){
       
        JFrame MainFrame = new JFrame("Main frame");
        JPanel MainPanel = new JPanel();
            
        JTextField Date = new JTextField(20);
        Date.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Date.setText(dtf.format(now));
        Date.setEditable(false);
        
        JLabel Label_renting_home_money = new JLabel();
        Label_renting_home_money.setText("Renting money");
        JTextField Renting_home_money = new JTextField(20);
        JTextField Hidden_renting_home_money = new JTextField(20);
        
        Hidden_renting_home_money.setEditable(false);
        Hidden_renting_home_money.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Renting_home_money.addKeyListener(this);
        Renting_home_money.setText(RentingMoney);
        Renting_home_money.setEditable(false);
        
        JTextField Start_num_elec = new JTextField(20);
        Start_num_elec.addKeyListener(this);
        JTextField End_num_elec = new JTextField(20);
        End_num_elec.addKeyListener(this);
        
        JTextField Electric = new JTextField(20);
        Electric.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        JTextField Electric_hidden_field = new JTextField(20);
        Electric_hidden_field.setEditable(false);
        Electric_hidden_field.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Electric.addKeyListener(this);
        Electric.setEditable(false);
        
        JTextField Water = new JTextField(20);
        JTextField Water_hidden_field = new JTextField(20);
        Water_hidden_field.setEditable(false);
        Water_hidden_field.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Water.addKeyListener(this);
        Water.setText("80000");
        
        JTextField Internet = new JTextField(20);
        JTextField Internet_hidden_field = new JTextField(20);
        Internet_hidden_field.setEditable(false);
        Internet_hidden_field.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Internet.addKeyListener(this);
        Internet.setText("80000");
        
        JTextField Gabarge = new JTextField(20);
        JTextField Gabarge_hidden_field = new JTextField(20);
        Gabarge_hidden_field.setEditable(false);
        Gabarge_hidden_field.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Gabarge.addKeyListener(this);
        Gabarge.setText("10000");
        
        JLabel Total_money_label = new JLabel();
        Total_money_label.setText("Total money");
        JTextField Total_money = new JTextField(20);
        Total_money.setEditable(false);
        Total_money.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        
        JMenuBar MainMenu = new JMenuBar();
        JMenu Menu = new JMenu();
        Menu.setText("Tools");
        JMenuItem ItemMenu1 = new JMenuItem();
        JMenuItem ItemMenu2 = new JMenuItem();
        JMenuItem ItemMenu3 = new JMenuItem();
        JMenuItem ItemMenu4 = new JMenuItem();
        
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
        
        ItemMenu4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEdittingRentingHomeMoneyInterface(Renting_home_money);
                //if(rst == 1){
                //    Renting_home_money.setText(RentingMoney);
                //}
            }
        });
        
        ItemMenu1.setText("List all");
        ItemMenu2.setText("Editting receiver's information");
        ItemMenu3.setText("Configure standard electric count");
        ItemMenu4.setText("Editting renting home money");
        
        Menu.add(ItemMenu1);
        Menu.add(ItemMenu4);
        Menu.add(ItemMenu2);
        Menu.add(ItemMenu3);
        MainMenu.add(Menu);
        
        GridBagLayout layout = new GridBagLayout();
        MainPanel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JButton submitBtb = new JButton();
        submitBtb.setText("Save");
        submitBtb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Start_num_elec.getText().length() != 0 && End_num_elec.getText().length() != 0
                && Water.getText().length() != 0 && Gabarge.getText().length() != 0 && Internet.getText().length() != 0){
                    int startNum = Integer.parseInt(Start_num_elec.getText());
                    int endNum = Integer.parseInt(End_num_elec.getText());
                    
                    if(endNum > startNum){
                        int totalNum = endNum - startNum;
                        int totalElectricMoney = ElectricStandardCount * totalNum;
                        Electric.setText(String.valueOf(totalElectricMoney));
                        int totalMoney = Integer.parseInt(RentingMoney) + totalElectricMoney + Integer.parseInt(Water.getText()) + Integer.parseInt(Internet.getText()) + Integer.parseInt(Gabarge.getText());
                        Total_money.setText(standardMoneyDisplay(totalMoney));
                        
                        int Electric_display_hidden = totalElectricMoney;
                        int Renting_money_display_hidden = Integer.parseInt(Renting_home_money.getText());
                        int Water_display_hidden = Integer.parseInt(Water.getText());
                        int Gabarge_display_hidden = Integer.parseInt(Gabarge.getText());
                        int Internet_display_hidden = Integer.parseInt(Internet.getText());
                        
                        Hidden_renting_home_money.setText(standardMoneyDisplay(Renting_money_display_hidden));
                        Electric_hidden_field.setText(standardMoneyDisplay(Electric_display_hidden));
                        Water_hidden_field.setText(standardMoneyDisplay(Water_display_hidden));
                        Internet_hidden_field.setText(standardMoneyDisplay(Internet_display_hidden));
                        Gabarge_hidden_field.setText(standardMoneyDisplay(Gabarge_display_hidden));
                                
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        int rst = JOptionPane.showConfirmDialog(null, "All information will be saved, are you sure ?","Warning",dialogButton);
                        if(rst == JOptionPane.YES_OPTION){
                            BufferedWriter bw = null;
                            try {
                                JOptionPane.showMessageDialog(null, "Saving success");
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
                                LocalDateTime now = LocalDateTime.now();
                                File f = new File("./bill/"+dtf.format(now)+"_renting_bill.htm");
                                
                                bw = new BufferedWriter(new FileWriter(f, StandardCharsets.UTF_8));
                                bw.write("<head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/></head>");
                                //bw.write("<head><meta charset='UTF-8'></head>");
                                bw.write("<html><body><h1>Hóa đơn tiền nhà</h1>");
                                bw.write("<br>Ngày/thời gian đóng(năm/tháng/ngày giờ): "+Date.getText());
                                bw.write("<br>Tiền nhà: "+standardMoneyDisplay(Renting_money_display_hidden));
                                bw.write("<br>Tiền mạng: "+standardMoneyDisplay(Internet_display_hidden));
                                bw.write("<br>Tiền nước: "+standardMoneyDisplay(Water_display_hidden));
                                bw.write("<br><b>Tiền điện ("+String.valueOf(ElectricStandardCount)+" vnd một số):<b>");
                                bw.write("<br><ul>");
                                bw.write("<li>Số đầu: "+Start_num_elec.getText()+"</li>");
                                bw.write("<li>Số cuối: "+End_num_elec.getText()+"</li>");
                                bw.write("</ul>");
                                bw.write("<br>Tổng số tiêu thụ: "+String.valueOf(totalNum));
                                bw.write("<br>Tổng tiền điện: "+standardMoneyDisplay(Electric_display_hidden));
                                bw.write("<br>Tiền rác: "+standardMoneyDisplay(Gabarge_display_hidden));
                                bw.write("<h3>Tổng tiền: "+standardMoneyDisplay(totalMoney)+"</h3>");
                                bw.write("<br>Người nhận tiền: "+ReceiverName);
                                bw.write("<br>Tài khoản người nhận tiền: "+ReceiverBankAccount);
                                bw.write("<br>Tên ngân hàng: "+ReceiverBankName);
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
                    }else{
                        JOptionPane.showMessageDialog(null, "Please check, Start count number cannot greater than End count number");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Please check, cannot leave empty field");
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
        MainPanel.add(Label_renting_home_money, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        MainPanel.add(Renting_home_money, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        MainPanel.add(Hidden_renting_home_money, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        MainPanel.add(Internet_label, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        MainPanel.add(Internet, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        MainPanel.add(Internet_hidden_field, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        MainPanel.add(Water_label, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        MainPanel.add(Water, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        MainPanel.add(Water_hidden_field, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        MainPanel.add(Start_num_elec_label, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        MainPanel.add(Start_num_elec, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 8;
        MainPanel.add(End_num_elec_label,gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        MainPanel.add(End_num_elec,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 9;
        MainPanel.add(Electric_label, gbc);
        gbc.gridx = 1;
        gbc.gridy = 9;
        MainPanel.add(Electric, gbc);
        gbc.gridx = 1;
        gbc.gridy = 10;
        MainPanel.add(Electric_hidden_field, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 11;
        MainPanel.add(Gabarge_label, gbc);
        gbc.gridx = 1;
        gbc.gridy = 11;
        MainPanel.add(Gabarge, gbc);
        gbc.gridx = 1;
        gbc.gridy = 12;
        MainPanel.add(Gabarge_hidden_field, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 13;
        MainPanel.add(Total_money_label, gbc);
        gbc.gridx = 1;
        gbc.gridy = 13;
        MainPanel.add(Total_money, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 14;
        MainPanel.add(submitBtb, gbc);
        
        MainFrame.setJMenuBar(MainMenu);
        MainFrame.add(MainPanel);
        MainFrame.setSize(350, 400);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setVisible(true);
        
        
    }
    
    public String standardMoneyDisplay(int money){
        String str = "";
        String tmp = "";
        int a;
        int b = money;
        while(b != 0){
            a = b % 10;
            b = b / 10;
           
            str = a + str;
            tmp = a + tmp;
            if(b != 0 && tmp.length() == 3){
                str = '.' + str;
                tmp = "";
            }
          
        }
        return str + " vnds";
    }
    
    public void setEdittingRentingHomeMoneyInterface(JTextField rentingMoneyField){
        JFrame SubFrame3 = new JFrame("");
        JPanel SubPanel3 = new JPanel();
        JLabel label_new_renting_home =  new JLabel();
        JLabel label_old_renting_home = new JLabel();
        
        JTextField new_renting_home = new JTextField(20);
        JTextField old_renting_home = new JTextField(20);
        
        label_new_renting_home.setText("New renting money");
        label_old_renting_home.setText("Renting money");
        
        old_renting_home.setText(RentingMoney);
        JButton SubSubmitBtn = new JButton();
        SubSubmitBtn.setText("Save");
        SubSubmitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int buttonDialog = JOptionPane.YES_NO_OPTION;
               int rst = JOptionPane.showConfirmDialog(null, "All information will be saved, are you sure ?", "Warning", buttonDialog);
               if(rst == JOptionPane.YES_OPTION){
                   RentingMoney = new_renting_home.getText();
                   old_renting_home.setText(RentingMoney);
                   rentingMoneyField.setText(RentingMoney);
               }
            }
        });
        old_renting_home.setEditable(false);
        GridBagLayout layout = new GridBagLayout();
        SubPanel3.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        SubPanel3.add(label_old_renting_home, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        SubPanel3.add(old_renting_home, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        SubPanel3.add(label_new_renting_home, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        SubPanel3.add(new_renting_home, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        SubPanel3.add(SubSubmitBtn, gbc);
        
        SubFrame3.setSize(350, 400);
        SubFrame3.add(SubPanel3);
        SubFrame3.setVisible(true);
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
        JLabel label_receiver_bank_name = new JLabel();
        label_receiver_bank_name.setText("Bank's name");
        JTextField receiver_bank_name = new JTextField(15);
        receiver_bank_name.setText(ReceiverBankName);
        receiver_bank_name.setEditable(false);
        
        JLabel label_new_name_receiver = new JLabel();
        label_new_name_receiver.setText("New receiver's name");
        JTextField new_name_receiver = new JTextField(15);
        JLabel label_new_receiver_bank_account = new JLabel();
        label_new_receiver_bank_account.setText("New receiver's bank acc");
        JTextField new_receiver_bank_Account = new JTextField(15);
        JLabel label_new_receiver_bank_name = new JLabel();
        label_new_receiver_bank_name.setText("New bank's name");
        JTextField new_receiver_bank_name = new JTextField(15);
        
       
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
                if(new_name_receiver.getText().length() != 0 || new_receiver_bank_Account.getText().length() != 0 || new_receiver_bank_name.getText().length() !=0){
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int rst = JOptionPane.showConfirmDialog(null, "All information will be saved, are you sure ?", "Warning", dialogButton);
                    if(rst == JOptionPane.YES_OPTION){
                        if(new_name_receiver.getText().length() != 0){
                            name_receiver.setText(new_name_receiver.getText());
                        }
                        if(new_receiver_bank_Account.getText().length() != 0){
                            receiver_bank_Account.setText(new_receiver_bank_Account.getText());
                        }
                        if(new_receiver_bank_name.getText().length() !=0){
                            receiver_bank_name.setText(new_receiver_bank_name.getText());
                        }
                        ReceiverName = new_name_receiver.getText();
                        ReceiverBankAccount = new_receiver_bank_Account.getText();
                        ReceiverBankName = new_receiver_bank_name.getText();
                        new_name_receiver.setText("");
                        new_receiver_bank_Account.setText("");
                        new_receiver_bank_name.setText("");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Please check, cannot find something new");
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
        SubPanel2.add(label_receiver_bank_name, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        SubPanel2.add(receiver_bank_name, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        SubPanel2.add(label_new_name_receiver,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        SubPanel2.add(new_name_receiver, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        SubPanel2.add(label_new_receiver_bank_account,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        SubPanel2.add(new_receiver_bank_Account, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        SubPanel2.add(label_new_receiver_bank_name,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        SubPanel2.add(new_receiver_bank_name, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 6;
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
