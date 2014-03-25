package com.clouway.travel;

import org.junit.runners.model.Statement;

/**
 * Created by clouway on 3/20/14.
 */
public class TravelStatement extends Statement {


  private final Statement statement;
  private final Travel travel;

  public TravelStatement(Statement statement, Travel travel) {

    this.statement = statement;
    this.travel = travel;
  }

  @Override
  public void evaluate() throws Throwable {

    travel.cleanerTables();

    Person petar = new Person("1111111111", "Petar", 25, "georgiev88@abv.bg");
    Trip trip = new Trip("Varna", "2014-03-25", "2014-04-13", "1111111111");

    travel.addNewTravel(petar, trip);

    try {
      statement.evaluate();
    } finally {
      travel.closeConnection();
    }
  }
}
