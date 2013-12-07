package com.clouway.directori;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/6/13
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class DemoDirectoryBrowser {
    public static void main(String[] args) {
        DirectoryBrowser dir = new DirectoryBrowser();
        dir.listContent("/home/clouway/IdeaProjects/workSpace/5.InputOutputTasks");
    }
}
