package com.jalasoft.search.model;

/**
 *
 The SearchCriterial class with getter  and setter methods to get data from view side and sent them to model side
 *
 * @version
26 Mar 2018
 *
 *  @author
Gretta Rocha
 */

public class SearchCriterial {

    private String fileName;
    private String filePath;
    private String owner;
    private boolean hidden;
    private boolean readOnly;

    public SearchCriterial(){}

    public  SearchCriterial(String fileName, String filePath, String owner, boolean hidden, boolean readOnly){
        this.fileName = fileName;
        this.filePath = filePath;
        this.owner = owner;
        this.hidden = hidden;
        this.readOnly = readOnly;
    }
    // method to get fileName
    public String getFileName(){
        return this.fileName;
    }

    // method to get filePath
    public String getFilePath() {
        return this.filePath;
    }

    //method to get owner
    public String getOwner() {
        return this.owner;
    }

    //method to get the value of hidden property
    public boolean getHidden() {
        return this.hidden;
    }

    //method to get the value of read only property
    public boolean getReadOnly() {
        return this.readOnly;
    }

    //method to set the value fileName
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    //method to set the value filePath
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //method to set the value of owner
    public void setOwner(String owner) {
        this.owner = owner;
    }

    //method to set the value of hidden property
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    //method to set the value of read only property
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}
