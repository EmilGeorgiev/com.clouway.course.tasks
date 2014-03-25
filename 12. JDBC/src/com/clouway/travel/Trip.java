package com.clouway.travel;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by clouway on 3/18/14.
 */
public class Trip {
  private final String city;
  private final Date dateArrival;
  private final Date dateDeparture;
  private final String egn;

  public Trip(String city, String dateArrival, String dateDeparture, String egn) {
    this.city = city;
    this.dateArrival = createDate(dateArrival);
    this.dateDeparture = createDate(dateDeparture);
    this.egn = egn;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Trip trip = (Trip) o;

    if (city != null ? !city.equals(trip.city) : trip.city != null) return false;
    if (dateArrival != null ? !dateArrival.equals(trip.dateArrival) : trip.dateArrival != null) return false;
    if (dateDeparture != null ? !dateDeparture.equals(trip.dateDeparture) : trip.dateDeparture != null) return false;
    if (egn != null ? !egn.equals(trip.egn) : trip.egn != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = city != null ? city.hashCode() : 0;
    result = 31 * result + (dateArrival != null ? dateArrival.hashCode() : 0);
    result = 31 * result + (dateDeparture != null ? dateDeparture.hashCode() : 0);
    result = 31 * result + (egn != null ? egn.hashCode() : 0);
    return result;
  }

  public String getCity() {
    return city;
  }

  public Date getDateArrival() {
    return dateArrival;
  }

  public Date getDateDeparture() {
    return dateDeparture;
  }

  public String getEgn() {
    return egn;
  }

  private Date createDate(String date) {
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date dateFormat = null;

    try {
      dateFormat = formatter.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    if (dateFormat != null) {
      return new Date(dateFormat.getTime());
    }

    return null;
  }

}
