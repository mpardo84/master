/*
 *     1 22/03/18
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

package com.jalasoft.search.model;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.nio.file.attribute.*;


/**
 *
 * This class is going to perform the file search
 *
 * @version 1 22 Mar 2018  * @author Marco Mendieta
 */
public class Search
{
    private SearchCriterial searchCriterial;
    private FactoryFileObject factoryFileObject;

    public Search()
    {
        this.searchCriterial = new SearchCriterial();
    }

    public SearchCriterial getSearchCriterial() {
        return searchCriterial;
    }

    /**
     * This method is going to search a file in a given folder
     *
     * @param fileDir  the name of the directory to search the file
     * */
    public void searchFile(String fileDir)
    {
        searchCriterial.setFileDirectory(fileDir);
        File[] list = searchCriterial.getFileDirectory().listFiles();

        if(list!=null)
            for (File file : list)
            {
                if (file.isDirectory())
                {
                    searchFile(file.getAbsolutePath());
                }
                else if (file.getName().toLowerCase().contains((searchCriterial.getFileName() + searchCriterial.getFileType()).toLowerCase()))
                {

                    if (file.isHidden() == searchCriterial.isHidden())
                    {
                        if (file.canWrite() != searchCriterial.isReadOnly())
                        {
                            try {
                                if (Files.getOwner(Paths.get(file.getAbsolutePath())).getName().toLowerCase()
                                        .contains(searchCriterial.getOwnerName().toLowerCase())) {

                                    if (searchCriterial.getSizeOption() == "" )
                                    {
                                        if (isWithinModifiedRange(file) && isWithinCreatedRange(file) && isWithinAccessedRange(file))
                                            if (isStringContainedInFile(file) || searchCriterial.getFileContains().isEmpty())
                                                setFoundFileObject(file);
                                    }
                                    else{
                                         if(searchCriterial.getSizeOption()==">" &&(Files.size(Paths.get(file.getAbsolutePath()))) > searchCriterial.getSize()) {
                                             if (isWithinModifiedRange(file) && isWithinCreatedRange(file) && isWithinAccessedRange(file))
                                                 if (isStringContainedInFile(file) || searchCriterial.getFileContains().isEmpty())
                                                     setFoundFileObject(file);
                                        }
                                        else {
                                            if (searchCriterial.getSizeOption() == "<" && (Files.size(Paths.get(file.getAbsolutePath()))) < searchCriterial.getSize()) {
                                                if (isWithinModifiedRange(file) && isWithinCreatedRange(file) && isWithinAccessedRange(file))
                                                    if (isStringContainedInFile(file) || searchCriterial.getFileContains().isEmpty())
                                                        setFoundFileObject(file);
                                            }
                                            else {

                                                if (searchCriterial.getSizeOption() == "=" && (Files.size(Paths.get(file.getAbsolutePath()))) == searchCriterial.getSize()) {
                                                    if (isWithinModifiedRange(file) && isWithinCreatedRange(file) && isWithinAccessedRange(file))
                                                        if (isStringContainedInFile(file) || searchCriterial.getFileContains().isEmpty())
                                                            setFoundFileObject(file);
                                                }
                                            }
                                        }
                                }

                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
    }

    public void searchDirectory(String fileDir)
    {
        searchCriterial.setFileDirectory(fileDir);
        File[] list = searchCriterial.getFileDirectory().listFiles();

        if(list!=null)
            for (File dir : list) {

                if (dir.isDirectory()) {
                    if (dir.getName().toLowerCase().toLowerCase().contains((searchCriterial.getFileName()).toLowerCase())) {
                        Path filePath = dir.toPath();
                        if (dir.isHidden() == searchCriterial.isHidden()) {
                            if (dir.canWrite() != searchCriterial.isReadOnly()) {
                                try {
                                    BasicFileAttributes attributes = Files.readAttributes(filePath, BasicFileAttributes.class);
                                    if (Files.getOwner(Paths.get(dir.getAbsolutePath())).getName().toLowerCase()
                                            .contains(searchCriterial.getOwnerName().toLowerCase())) {

                                        if (searchCriterial.getSizeOption() == "") {
                                            setFoundDirectoryObject(dir);
                                        } else {
                                            if (searchCriterial.getSizeOption() == ">" && (Files.size(Paths.get(dir.getAbsolutePath()))) > searchCriterial.getSize()) {
                                                setFoundDirectoryObject(dir);
                                            } else {
                                                if (searchCriterial.getSizeOption() == "<" && (Files.size(Paths.get(dir.getAbsolutePath()))) < searchCriterial.getSize()) {
                                                    setFoundDirectoryObject(dir);
                                                } else {

                                                    if (searchCriterial.getSizeOption() == "=" && (Files.size(Paths.get(dir.getAbsolutePath()))) == searchCriterial.getSize()) {
                                                        setFoundDirectoryObject(dir);

                                                    }

                                                }
                                            }
                                        }

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }

    }

    private void setFoundFileObject(File file)
    {
        try {
            Date dateModified = new Date(Files.getLastModifiedTime(Paths.get(file.getAbsolutePath())).toMillis());
            Date dateCreated = new Date(Files.getLastModifiedTime(Paths.get(file.getAbsolutePath())).toMillis());
            Date dateAccessed = new Date(Files.getLastModifiedTime(Paths.get(file.getAbsolutePath())).toMillis());
            String ownerName = Files.getOwner(Paths.get(file.getAbsolutePath())).getName();
            long size = Files.size(Paths.get(file.getAbsolutePath()));

            factoryFileObject.setFileObjectFound(this.searchCriterial, "file", FilenameUtils.getBaseName(file.getName()),
                    file.getParent(), !file.canWrite(), file.isHidden(), ownerName,
                    dateModified, size, dateCreated, dateAccessed,
                    searchCriterial.getFileContains(), 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isWithinModifiedRange(File file)
    {
        Date testDate = new Date(file.lastModified());
        return !(testDate.before(searchCriterial.getModifiedStartDate()) || testDate.after(searchCriterial.getModifiedEndDate()));
    }

    private boolean isWithinCreatedRange(File file)
    {
        BasicFileAttributes fileAttributes;
        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        try {
            fileAttributes = Files.readAttributes(Paths.get(file.getAbsolutePath()), BasicFileAttributes.class);
            Date testDate = dateFormat.parse(dateFormat.format(fileAttributes.creationTime().toMillis()));
            return !(testDate.before(searchCriterial.getCreatedStartDate()) || testDate.after(searchCriterial.getCreatedEndDate()));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isWithinAccessedRange(File file)
    {
        BasicFileAttributes fileAttributes;
        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        try {
            fileAttributes = Files.readAttributes(Paths.get(file.getAbsolutePath()), BasicFileAttributes.class);
            Date testDate = dateFormat.parse(dateFormat.format(fileAttributes.lastAccessTime().toMillis()));
            return !(testDate.before(searchCriterial.getAccessedStartDate()) || testDate.after(searchCriterial.getAccessedEndDate()));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isStringContainedInFile(File file)
    {
        try {
            Scanner scanner = new Scanner(file);

            //now read the file line by line...
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                if(line.toLowerCase().contains(searchCriterial.getFileContains().toLowerCase())) {
                    return true;
                }
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void setFoundDirectoryObject(File file)
    {
        int numberOfFiles = 0;

        if (file.listFiles() != null)
            numberOfFiles = file.listFiles().length;

        try {
            Date dateModified = new Date(Files.getLastModifiedTime(Paths.get(file.getAbsolutePath())).toMillis());
            Date dateCreated = new Date(Files.getLastModifiedTime(Paths.get(file.getAbsolutePath())).toMillis());
            Date dateAccessed = new Date(Files.getLastModifiedTime(Paths.get(file.getAbsolutePath())).toMillis());
            String ownerName = Files.getOwner(Paths.get(file.getAbsolutePath())).getName();
            long size = Files.size(Paths.get(file.getAbsolutePath()));

            factoryFileObject.setFileObjectFound(this.searchCriterial, "directory", FilenameUtils.getBaseName(file.getName()),
                    file.getParent(), !file.canWrite(), file.isHidden(), ownerName,
                    dateModified, size, dateCreated, dateAccessed,
                    searchCriterial.getFileContains(), numberOfFiles);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
