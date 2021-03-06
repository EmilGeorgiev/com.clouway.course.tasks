package com.clouway.consolereader.classes;

import com.clouway.consolereader.validator.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/5/13
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReaderFloat extends ScannerIn {

    public ReaderFloat(String fileName) {
        super(fileName);
    }

    /**
     * Check whether the argument is of type Float
     * @param message contains only numbers of type Float
     * @return true if message contains only numbers, otherwise return false.
     */
    @Override
    public boolean validate(String message) {
        try {
            Float.parseFloat(message);
        } catch (NumberFormatException ex) {
            System.err.println("The argument: " + "\"" + message + "\"" + " is not of type Float");
            return false;
        }
        return true;
    }
}
