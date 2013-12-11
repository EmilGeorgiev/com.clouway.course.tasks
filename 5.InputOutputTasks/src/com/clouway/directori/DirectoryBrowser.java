package com.clouway.directori;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/6/13
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class DirectoryBrowser {

    /**
     * Displays the contents of the directory on the file system.Verify whether the path points to a file or directory.
     * If you point to a file - returns a message that is submitted name file.
     * If points to the directory - returns a list of names of all files and directories contained in it.
     *
     * @param path the name of the directory or file with the full path before him.
     */
    public List<FileObject> listContent(String path) {
        File file = new File(path);
        if (file.exists()) {
            for (File child : file.listFiles()) {
                if (child.isDirectory()) {
                    System.out.println("D " + child.getName());
                }else {
                    System.out.println("F " + child.getName());
                }
            }
        } else {
            System.out.println("File does not exist.");
        }
    }
}
