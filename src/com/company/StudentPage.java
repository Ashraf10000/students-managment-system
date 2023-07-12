package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.sql.*;
import java.text.MessageFormat;


public class StudentPage extends JFrame  implements ActionListener  {
    private JLabel title;
    private JLabel Studentlabel[] = new JLabel[6];
    private JLabel Subjectslabel[] = new JLabel[8];
    private JTextField textFields[] = new JTextField[5];
    private JPanel infopanel;
    private JPanel infotxtpanel;
    private JPanel infoholder;
    private JPanel subjectspanel;
    public JPanel subjectspanel2;
    public JPanel subjectholder;
    public JPanel buttonspanel;
    public JTable datatable;
    public DefaultTableModel model;
    public JPanel tablepanel;
    public JScrollPane scroll;
    public String[] data = new String[14];
    private String []header = new String[14];
    public JButton Buttons[] = new JButton[7];
    public JComboBox gender;
    public JComboBox subjects[] = new JComboBox[8];

    // connecting the database
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_database","root","password");
    Statement statement= con.createStatement();
    ResultSet resultSet = statement.executeQuery("select*from student");



    public StudentPage() throws SQLException {


        //Frame Creation and Setting
        setSize(1080, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Student Management System");
        setResizable(true);

        //  Title creation and setting
        title = new JLabel();
        title.setBounds(2, 0, 1060, 80);
        title.setText("Students Managemant Sysyem App Using Java");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 40));
        title.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5, false));
        title.setForeground(Color.black);
        title.setBackground(Color.blue);
        title.setOpaque(true);

        //  student info panal creation and adjusting
        infopanel = new JPanel();
        infopanel.setBounds(15, 90, 110, 140);
        infopanel.setLayout(new GridLayout(6, 0, 0, 5));

        Studentlabel[0] = new JLabel("Student Id");
        Studentlabel[1] = new JLabel("Frist Name");
        Studentlabel[2] = new JLabel("Sur Name");
        Studentlabel[3] = new JLabel("Address");
        Studentlabel[4] = new JLabel("Phone Number");
        Studentlabel[5] = new JLabel("Gender");

        //student info color, font ,etc...
        for (int i = 0; i < 6; i++) {
            Studentlabel[i].setFont(new Font("Tahoma", Font.BOLD, 17));
            infopanel.add(Studentlabel[i]);
        }

        // text fields for student info
        infotxtpanel = new JPanel();
        infotxtpanel.setBounds(140, 90, 140, 135);
        infotxtpanel.setLayout(new GridLayout(6, 0, 0, 5));

        // creating combobox for gender and adjusting it
        gender = new JComboBox();
        gender.addItem("male");
        gender.addItem("female");

        //adding all txtfields to the panal
        for (int i = 0; i < 5; i++) {
            textFields[i] = new JTextField();
            infotxtpanel.add(textFields[i]);
        }


        infoholder = new JPanel();
        infotxtpanel.add(gender);
        infoholder.add(infopanel);
        infoholder.add(infotxtpanel);
        infoholder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5, false));

        subjectspanel = new JPanel();
        subjectspanel.setBounds(15, 270, 140, 235);
        subjectspanel.setLayout(new GridLayout(8, 0, 0, 5));
        Subjectslabel[0] = new JLabel("Math");
        Subjectslabel[1] = new JLabel("Descrite");
        Subjectslabel[2] = new JLabel("Data Base");
        Subjectslabel[3] = new JLabel("Network");
        Subjectslabel[4] = new JLabel("Operating Systems");
        Subjectslabel[5] = new JLabel("Data Structures");
        Subjectslabel[6] = new JLabel("Programing");
        Subjectslabel[7] = new JLabel("Logic Desgin");
        for (int i = 0; i < 8; i++) {
            Subjectslabel[i].setFont(new Font("Tahoma", Font.BOLD, 17));
            subjectspanel.add(Subjectslabel[i]);
        }

        subjectspanel2 = new JPanel();
        subjectspanel2.setBounds(210, 270, 70, 235);
        subjectspanel2.setLayout(new GridLayout(8, 0, 0, 5));
        for (int i = 0; i < 8; i++) {
            subjects[i] = new JComboBox();
            subjects[i].addItem("no");
            subjects[i].addItem("yes");
            subjectspanel2.add(subjects[i]);
        }
        subjectholder = new JPanel();
        subjectholder.add(subjectspanel);
        subjectholder.add(subjectspanel2);
        subjectholder.setLayout(null);
        subjectholder.setBackground(Color.red);
        subjectholder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5, false));

        //creating data table
        tablepanel = new JPanel();
        tablepanel.setLayout(new BorderLayout());
        tablepanel.setBounds(300, 90, 760, 420);
        tablepanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 6, false));
        datatable = new JTable(0, 14);
        datatable.isOpaque();
        datatable.setAutoResizeMode(0);
        scroll = new JScrollPane(datatable);
        tablepanel.add(scroll);
        header[0]= "student_id";
        header[1]= "firstname";
        header[2]= "surname";
        header[3]= "Address";
        header[4]= "phonenumber";
        header[5]= "gender";
        header[6]= "math";
        header[7]= "descrite";
        header[8]= "data_base";
        header[9]= "network";
        header[10]= "operating_systems";
        header[11]= "data_structuers";
        header[12]= "programing";
        header[13]= "logic_design";
        model = new DefaultTableModel(null,header){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        datatable.setModel(model);
        //  uploading data  from database into the table
        String[]upload = new String[14];
        while (resultSet.next()){
            for(int i=1;i<=14;i++){
                upload[i-1]=resultSet.getString(i);
            }
            model.addRow(upload);
        }






        //creating panal to hold the buttons
        buttonspanel = new JPanel();
        buttonspanel.setBounds(15, 540, 1040, 60);
        buttonspanel.setLayout(new GridLayout(1, 6, 10, 5));

        //naming the buttons
        Buttons[0] = new JButton("Add New");
        Buttons[1] = new JButton("Print");
        Buttons[2] = new JButton("Update");
        Buttons[3] = new JButton("Reset");
        Buttons[4] = new JButton("Delete");
        Buttons[5] = new JButton("GPA?");
        Buttons[6] = new JButton("Exit");

        //setting buttons color,font, etc...
        for (int i = 0; i < 7; i++) {
            Buttons[i].addActionListener(this);
            Buttons[i].setFont(new Font("Tahoma", Font.BOLD, 27));
            Buttons[i].setBackground(new Color(0, 100, 255));
            Buttons[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5, false));
            buttonspanel.add(Buttons[i]);
        }


        //adding items
        add(tablepanel);
        add(title);
        add(buttonspanel);
        add(subjectspanel);
        add(subjectspanel2);
        add(infopanel);
        add(infotxtpanel);
        add(infoholder);
        add(subjectholder);
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //reset button
        if (e.getSource() == Buttons[3]) {
            gender.setSelectedIndex(0);
            for (int i = 0; i < subjects.length; i++) {
                subjects[i].setSelectedIndex(0);
            }
            for (int i = 0; i < textFields.length; i++) {
                textFields[i].setText("");
            }
        }
        //exit button
        if (e.getSource() == Buttons[6]) {
            System.exit(0);
        }

        //add new button
        if (e.getSource() == Buttons[0]) {
            for (int j = 0; j < 5; j++) {
                data[j] = textFields[j].getText();
            }

            data[5] = (String) gender.getSelectedItem();
            for (int j = 0; j < 8; j++) {
                data[j + 6] = (String) subjects[j].getSelectedItem();
            }
            try {
                statement.execute("INSERT INTO student VALUES ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"" +
                        "','"+data[6]+"','"+data[7]+"','"+data[8]+"','"+data[9]+"','"+data[10]+"','"+data[11]+"','"+data[12]+"','"+data[13]+"')");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            model.addRow(data);
        }

        //Delelte Button
        if (e.getSource() == Buttons[4]) {
            if (datatable.getSelectedRowCount() == 1) {
                try {
                    statement.execute("delete from student where student_id = '"+data[0]+"'");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                model.removeRow(datatable.getSelectedRow());
            }
        }

        //Update Button
        datatable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evnt) {
                for (int j = 0; j < 14; j++)
                    data[j] = (String) model.getValueAt(datatable.getSelectedRow(), j);

                for (int j = 0; j < 5; j++) {
                    textFields[j].setText(data[j]);
                }

                gender.setSelectedItem(data[5]);

                for (int j = 0; j < 8; j++) {
                    subjects[j].setSelectedItem(data[j + 6]);
                }
            }
        });
        //still in Update Button
        if (e.getSource() == Buttons[2]) {
            for (int j = 0; j < 5; j++) {
                data[j] = textFields[j].getText();
            }

            data[5] = (String) gender.getSelectedItem();
            for (int j = 0; j < 8; j++) {
                data[j + 6] = (String) subjects[j].getSelectedItem();
            }
            if (datatable.getSelectedRowCount() == 1) {
                for (int j = 0; j < 14; j++)
                    model.setValueAt(data[j], datatable.getSelectedRow(), j);
            }
            try {
                for(int i=0;i<14;i++)
                    statement.execute("update student set "+header[i]+"='"+data[i]+"' where student_id='"+textFields[0].getText()+"'");
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        //GPA Button
        if (e.getSource() == Buttons[5]) {
            gpa gpa = new gpa();
            gpa.setVisible(true);
            gpa.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        }

        //print Button

        if(e.getSource() == Buttons[1]){
            MessageFormat header = new MessageFormat("Students data");
            MessageFormat footer = new MessageFormat("page{0,number,integer}");

            try {
                datatable.print(JTable.PrintMode.NORMAL,header,footer);
            }
            catch (PrinterException printerException) {
                printerException.printStackTrace();
            }


        }
    }
}



