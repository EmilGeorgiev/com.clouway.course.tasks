package com.clouway.core;

/**
 * Created by clouway on 7/25/14.
 */
public interface UserMessage {
  String failed();

  String success();

  String successTransaction();

  String failedTransaction();
}
