/*
 *     1 21/03/18
 *
 * Copyright (c) 2018 Sun Microsystems, Inc.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 *  with Sun.
 *
 */

package com.jalasoft.search.view;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.Color;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

/**
 *
 This class contains the fields that are part of the search criteria for a File
 *
 * @version
1.1 25 Mar 2018  * @author
Monica Pardo */

public class SearchPanelCriteria extends JPanel {
    private JLabel title;
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel containsLabel;
    private JLabel ownerLabel;
    private JLabel attributesLabel;
    private JLabel readOnlyLabel;
    private JLabel pathLabel;
    private JLabel hiddenLabel;
    private JLabel fileTimeLabel;
    private JLabel createdLabel;
    private JLabel modifiedLabel;
    private JLabel accessedLabel;
    private JLabel fromCreatedLabel;
    private JLabel toCreatedLabel;
    private JLabel fromModifiedLabel;
    private JLabel toModifedLabel;
    private JLabel fromAccessedLabel;
    private JLabel toAccessedLabel;
    private JLabel extensionLabel;
    private JLabel sizeLabel;
    private JLabel defaultSize;
    private JTextField  nameField;
    private JTextField typeField;
    private JTextArea containsField;
    private JTextField ownerField;
    private JTextField pathValue;
    private JTextField sizeValue;
    private JFileChooser chooser;
    private JButton browseButton;
    private JButton saveButton;
    private JButton searchButton;
    private JButton cancelButton;
    private JSeparator separator;
    private JComboBox readOnlyOptions;
    private JComboBox hiddenOptions;
    private JComboBox createdOptions;
    private JComboBox modifiedOptions;
    private JComboBox accessedOptions;
    private JComboBox sizeOptions;
    private Font negritaFont;
    private JDateChooser fromCreatedDate;
    private JDateChooser toCreatedDate;
    private JDateChooser fromModifiedDate;
    private JDateChooser toModifiedDate;
    private JDateChooser fromAccessedDate;
    private JDateChooser toAccessedDate;
    private  String[] comboBoxTimeValues;
    private String typeObject;
    private String message;
    private  ImageIcon saveIcon;
    private ImageIcon cancelIcon;
    private ImageIcon searchIcon;
    private ImageIcon browseIcon;

    public SearchPanelCriteria() {
        setLayout(null);
        comboBoxTimeValues = new String[] { "AllTime", "Time Range","Today","Yesterday" };
        this.setBackground(new Color(224, 224, 224));
        generalSearchCriteria();
        searchAttributesSection();
        searchFileTimeSection();
        searchPanelButtons();


    }

    //Get method to get the path value from UI
    public String getPathValue() {
        return pathValue.getText();
    }

    //Get method to get the file name value from UI
    public String getNameField() {
        return nameField.getText();
    }

    //Get method to get the file owner value from UI
    public String getOwnerField() {
        return ownerField.getText();
    }

    //Get method to get the file type value from UI
    public String getTypeField() {
        return typeField.getText();
    }

    //Get method to get the file contains value from UI
    public String getContainsField() {
        return containsField.getText();
    }

    //Get method to get if te file is read only value from UI
    public String getReadOnlyOptions() {
        return (String)readOnlyOptions.getSelectedItem();
    }

    //Get method to get if te file is hidden value from UI
    public String getHiddenOptions() {
        return (String)hiddenOptions.getSelectedItem();
    }

    //Get Method to set the type of the objetct is this is a File or not
    public String getTypeObject(){
        return typeObject="File";
    }

    //Get method to get the created option selected from UI
    public String getCreatedOptions() {
        return (String)createdOptions.getSelectedItem();
    }

    //Get method to get the modified option selected from UI
    public String getModifiedOptions() {
        return (String)modifiedOptions.getSelectedItem();
    }

    //Get method to get the acessed option selected from UI
    public String getAccessedOptions() {
        return (String)accessedOptions.getSelectedItem();
    }

    //Get method to get the  range for the created option selected from UI
    public Date getFromCreatedDate() {
        return fromCreatedDate.getDate();
    }

    //Get method to get the range for the  created option selected from UI
    public Date getToCreatedDate() {
        return toCreatedDate.getDate();
    }

    //Get method to get the range for the modified option selected from UI
    public Date getFromModifiedDate() {
        return fromModifiedDate.getDate();
    }

    //Get method to get the range for the modified option selected from UI
    public Date getToModifiedDate() {
        return toModifiedDate.getDate();
    }

    //Get method to get the range for the accessed option selected from UI
    public Date getFromAccessedDate() {
        return fromAccessedDate.getDate();
    }

    //Get method to get the range for the accessed option selected from UI
    public Date getToAccessedDate() {
        return toAccessedDate.getDate();
    }

    //Get method to get the size Option value
    public String getSizeOptions() {
        return (String)sizeOptions.getSelectedItem();
    }

    //Get method to get the size
    public String getSizeValue() {
        return sizeValue.getText();
    }

    //Get the search button
    public JButton getSearchButton() {
        return searchButton;
    }

    //Get the Cancel button
    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setHiddenOptions(JComboBox hiddenOptions) {
        this.hiddenOptions = hiddenOptions;
    }

    public void setReadOnlyLabel(JLabel readOnlyLabel) {
        this.readOnlyLabel = readOnlyLabel;
    }

    //method to allows set the message value
    public void setMessage(String message) {
        this.message = message;
    }

    //This method allows to select a file
    public void browseFileAction(){
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("c:"));
                chooser.setDialogTitle("Browse the folder to search");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String filePath=chooser.getSelectedFile().getPath();
                    pathValue.setText(filePath);
                }
            }
        });
    }

    //this method will contains the general components for the UI
    public void generalSearchCriteria(){
        title = new JLabel("SEARCH OPTIONS");
        title.setBounds(0, 7, 200, 40);
        pathLabel = new JLabel(" Search Path:");
        pathLabel.setBounds(30, 50, 80, 20);
        pathValue = new JTextField();
        pathValue.setBounds(120, 50, 200, 25);
        pathValue.setBackground(new Color(240,248,255));
        browseIcon = new ImageIcon(
                this.getClass().getResource("/images/folder.png"));
        browseButton = new JButton("Browse",browseIcon);
        browseButton.setBounds(325,50,105,23);

        nameLabel = new JLabel("File Name:");
        nameLabel.setBounds(30, 85, 80, 20);
        nameField = new JTextField();
        nameField.setBounds(120, 85, 200, 25);
        nameField.setBackground(new Color(240,248,255));

        typeLabel =new JLabel("File Type:");
        typeLabel.setBounds(30, 120, 80, 20);
        typeField = new JTextField();
        typeField.setBounds(120, 120, 40, 25);
        typeField.setBackground(new Color(240,248,255));

        extensionLabel = new JLabel("(Extension)");
        extensionLabel.setBounds(165, 120, 80, 25);
        extensionLabel.setFont(new java.awt.Font("Century", 0, 11));

        containsLabel = new JLabel("File Contains:");
        containsLabel.setBounds(30, 155, 80, 20);
        containsField = new JTextArea();
        JScrollPane scrollContains=new JScrollPane(containsField);
        scrollContains.setBounds(120, 155, 300, 35);
        containsField.setBackground(new Color(240,248,255));

        ownerLabel = new JLabel("File Owner:");
        ownerLabel.setBounds(30, 200, 80, 20);
        ownerField = new JTextField();
        ownerField.setBounds(120, 200, 150, 25);
        ownerField.setBackground(new Color(240,248,255));

        sizeLabel = new JLabel("File Size:");
        sizeLabel.setBounds(30, 240, 80, 20);
        String[] comboBoxSizesValues = { "","=", ">","<" };
        sizeOptions = new JComboBox(comboBoxSizesValues);
        sizeOptions.setBounds(120, 240, 40, 25);
        sizeValue = new JTextField();
        sizeValue.setBounds(165, 240, 80, 25);
        sizeValue.setBackground(new Color(224,224,224));
        sizeValue.setEnabled(false);
        defaultSize = new JLabel("Kb");
        defaultSize.setBounds(250, 240, 20, 25);
        sizeOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String option = (String) sizeOptions.getSelectedItem();
                if (option.equals("")) {
                    sizeValue.setEnabled(false);
                    sizeValue.setText("");
                    sizeValue.setBackground(new Color(224,224,224));
                }
                else
                {
                    sizeValue.setEnabled(true);
                    sizeValue.setBackground(new Color(240, 248, 255));
                }
            }
        });
        separator=new JSeparator();
        separator.setBounds(0, 275, 500, 20);

        add(title);
        add(pathLabel);
        add(pathValue);
        add(browseButton);
        add(nameLabel);
        add(nameField);
        add(typeLabel);
        add(typeField);
        add(extensionLabel);
        add(containsLabel);
        add(scrollContains);
        add(ownerLabel);
        add(ownerField);
        add(sizeLabel);
        add(sizeOptions);
        add(sizeValue);
        add(defaultSize);
        add(separator);
        browseFileAction();
    }

    //This method contains the components for the Attribute section in UI
    public void searchAttributesSection(){
        String[] comboBoxValues = { "False", "True" };
        attributesLabel=new JLabel("ATTRIBUTES");

        attributesLabel.setBounds(0,280,80,20);
        readOnlyLabel=new JLabel("Read Only:");
        readOnlyLabel.setBounds(30,305,80,20);
        readOnlyOptions= new JComboBox(comboBoxValues);
        readOnlyOptions.setBounds(120,305,100,20);

        hiddenLabel=new JLabel("Hidden:");
        hiddenLabel.setBounds(30,335,80,20);
        hiddenOptions= new JComboBox(comboBoxValues);
        hiddenOptions.setBounds(120,335,100,20);

        separator=new JSeparator();
        separator.setBounds(0, 370, 500, 20);

        add(attributesLabel);
        add(readOnlyLabel);
        add(readOnlyOptions);
        add(hiddenLabel);
        add(hiddenOptions);
        add(separator);
    }

    //This method contains the components for the File Time section in UI
    public void searchFileTimeSection(){

        fileTimeLabel=new JLabel("FILE TIME");
        negritaFont=new Font("ITALIC", Font.BOLD, 13);
        fileTimeLabel.setBounds(0,380,80,20);
        fileTimeLabel.setFont(negritaFont);
        //Created Date section method
        createdDateSection();
        //Modified Date section method
        modifiedDateSection();
        // accessed Date section methof
        accessedDateSection();

        separator=new JSeparator();
        separator.setBounds(0, 540, 500, 20);

        add(fileTimeLabel);
        add(createdLabel);
        add(createdOptions);
        add(fromCreatedLabel);
        add(fromCreatedDate);
        add(toCreatedLabel);
        add(toCreatedDate);
        add(modifiedLabel);
        add(modifiedOptions);
        add(fromModifiedLabel);
        add(fromModifiedDate);
        add(toModifedLabel);
        add(toModifiedDate);
        add(accessedLabel);
        add(accessedOptions);
        add(fromAccessedLabel);
        add(fromAccessedDate);
        add(toAccessedLabel);
        add(toAccessedDate);
        add(separator);
    }

    //This method allows to create the buttons
    public void searchPanelButtons(){
        saveIcon = new ImageIcon(
                this.getClass().getResource("/images/save.png"));
        saveButton=new JButton("Save",saveIcon);
        saveButton.setBounds(110,600,90,20);
        searchIcon = new ImageIcon(
                this.getClass().getResource("/images/edit_find.png"));
        searchButton=new JButton("Search",searchIcon);
        searchButton.setBounds(205,600,100,20);
        cancelIcon = new ImageIcon(
                this.getClass().getResource("/images/close.png"));
        cancelButton=new JButton("Close",cancelIcon);
        cancelButton.setBounds(310,600,90,20);
        add(saveButton);
        add(searchButton);
        add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

    }

    //This method contains all the UI compoments for the createdDate section
    public void createdDateSection(){
        createdLabel=new JLabel("Created:");
        createdLabel.setBounds(15,410,80,20);
        createdOptions= new JComboBox(comboBoxTimeValues);
        createdOptions.setBounds(90,410,100,20);

        fromCreatedLabel= new JLabel("From:");
        fromCreatedLabel.setBounds(200,410,80,20);
        fromCreatedLabel.setVisible(false);
        fromCreatedDate = new JDateChooser();
        fromCreatedDate.setDate((Calendar.getInstance()).getTime());
        fromCreatedDate.setDateFormatString("dd/MM/yyyy");
        fromCreatedDate.setBounds(240, 410, 90, 20);
        fromCreatedDate.setVisible(false);

        toCreatedLabel= new JLabel("To:");
        toCreatedLabel.setBounds(340,410,80,20);
        toCreatedLabel.setVisible(false);
        toCreatedDate = new JDateChooser();
        toCreatedDate.setDate((Calendar.getInstance()).getTime());
        toCreatedDate.setDateFormatString("dd/MM/yyyy");
        toCreatedDate.setBounds(370, 410, 90, 20);
        toCreatedDate.setVisible(false);

        createdOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String selectedBook = (String) createdOptions.getSelectedItem();
                if (selectedBook.equals("Time Range")) {
                    fromCreatedLabel.setVisible(true);
                    fromCreatedDate.setVisible(true);
                    toCreatedLabel.setVisible(true);
                    toCreatedDate.setVisible(true);
                }
                else
                {
                    fromCreatedLabel.setVisible(false);
                    fromCreatedDate.setVisible(false);
                    toCreatedLabel.setVisible(false);
                    toCreatedDate.setVisible(false);
                }
            }
        });
    }

    //This method contains all the UI components for the  modified Date section
    public void modifiedDateSection(){
        modifiedLabel=new JLabel("Modified:");
        modifiedLabel.setBounds(15,450,80,20);
        modifiedOptions= new JComboBox(comboBoxTimeValues);
        modifiedOptions.setBounds(90,450,100,20);

        fromModifiedLabel= new JLabel("From:");
        fromModifiedLabel.setBounds(200,450,80,20);
        fromModifiedLabel.setVisible(false);
        fromModifiedDate = new JDateChooser();
        fromModifiedDate.setDate((Calendar.getInstance()).getTime());
        fromModifiedDate.setDateFormatString("dd/MM/yyyy");
        fromModifiedDate.setBounds(240, 450, 90, 20);
        fromModifiedDate.setVisible(false);

        toModifedLabel= new JLabel("To:");
        toModifedLabel.setBounds(340,450,80,20);
        toModifedLabel.setVisible(false);
        toModifiedDate = new JDateChooser();
        toModifiedDate.setDate((Calendar.getInstance()).getTime());
        toModifiedDate.setDateFormatString("dd/MM/yyyy");
        toModifiedDate.setBounds(370, 450, 90, 20);
        toModifiedDate.setVisible(false);

        modifiedOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String selectedBook = (String) modifiedOptions.getSelectedItem();
                if (selectedBook.equals("Time Range")) {
                    fromModifiedLabel.setVisible(true);
                    fromModifiedDate.setVisible(true);
                    toModifedLabel.setVisible(true);
                    toModifiedDate.setVisible(true);
                }
                else
                {
                    fromModifiedLabel.setVisible(false);
                    fromModifiedDate.setVisible(false);
                    toModifedLabel.setVisible(false);
                    toModifiedDate.setVisible(false);
                }
            }
        });
    }

    //This method contains all the UI components for the  accessed Date section
    public void accessedDateSection(){
        accessedLabel=new JLabel("Accessed:");
        accessedLabel.setBounds(15,490,80,20);
        accessedOptions= new JComboBox(comboBoxTimeValues);
        accessedOptions.setBounds(90,490,100,20);

        fromAccessedLabel= new JLabel("From:");
        fromAccessedLabel.setBounds(200,490,80,20);
        fromAccessedLabel.setVisible(false);
        fromAccessedDate = new JDateChooser();
        fromAccessedDate.setDate((Calendar.getInstance()).getTime());
        fromAccessedDate.setDateFormatString("dd/MM/yyyy");
        fromAccessedDate.setBounds(240, 490, 90, 20);
        fromAccessedDate.setVisible(false);

        toAccessedLabel= new JLabel("To:");
        toAccessedLabel.setBounds(340,490,80,20);
        toAccessedLabel.setVisible(false);
        toAccessedDate = new JDateChooser();
        toAccessedDate.setDate((Calendar.getInstance()).getTime());
        toAccessedDate.setDateFormatString("dd/MM/yyyy");
        toAccessedDate.setBounds(370, 490, 90, 20);
        toAccessedDate.setVisible(false);


        accessedOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String selectedBook = (String) accessedOptions.getSelectedItem();
                if (selectedBook.equals("Time Range")) {
                    fromAccessedLabel.setVisible(true);
                    fromAccessedDate.setVisible(true);
                    toAccessedLabel.setVisible(true);
                    toAccessedDate.setVisible(true);
                }
                else
                {
                    fromAccessedLabel.setVisible(false);
                    fromAccessedDate.setVisible(false);
                    toAccessedLabel.setVisible(false);
                    toAccessedDate.setVisible(false);
                }
            }
        });
    }

    //Method to allows validate if the required field is empty
    public void validateRequiredField() {
        if (message!="") {
            JOptionPane.showMessageDialog(null,message);
        }
    }

}


