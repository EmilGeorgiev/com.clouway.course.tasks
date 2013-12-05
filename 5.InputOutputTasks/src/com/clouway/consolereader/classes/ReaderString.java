package com.clouway.consolereader.classes;

import com.clouway.consolereader.validator.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/5/13
 * Time: 5:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReaderString extends ScannerIn implements Validator {

    public ReaderString(String fileName) {
        super(fileName);
    }

    /**
     * Check whether the argument is of type String
     * @param message contains only numbers of type String
     * @return aways return true.
     */
    @Override
    public boolean validate(String message) {
        return true;
    }
}
