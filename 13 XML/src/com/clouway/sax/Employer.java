package com.clouway.sax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by clouway on 3/31/14.
 */
@XmlComplexElement(packageName ="com.clouway.sax.", beginningMethodName = "set")
public class Employer {

  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  private String name;
  private Date startDate;
  private Date endDate;

  public Employer(String name, Date startDate, Date endDate) {
    this.name = name;

    this.startDate = startDate;

    this.endDate = endDate;
  }

  public Employer() {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date setStartDate(String startDate) {
    Date date = null;
    try {
      date = dateFormat.parse(startDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    this.startDate = date;
    return date;
  }

  public Date getEndDate() {
    return endDate;
  }

  public Date setEndDate(String endDate) {
    Date date = null;
    try {
      date = dateFormat.parse(endDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    this.endDate = date;
    return date;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Employer employer = (Employer) o;

    if (dateFormat != null ? !dateFormat.equals(employer.dateFormat) : employer.dateFormat != null) return false;
    if (endDate != null ? !endDate.equals(employer.endDate) : employer.endDate != null) return false;
    if (name != null ? !name.equals(employer.name) : employer.name != null) return false;
    if (startDate != null ? !startDate.equals(employer.startDate) : employer.startDate != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = dateFormat != null ? dateFormat.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
    result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
    return result;
  }
}
