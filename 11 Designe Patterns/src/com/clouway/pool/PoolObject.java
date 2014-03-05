package com.clouway.pool;

/**
 * Created by clouway on 2/26/14.
 */
public class PoolObject {

  //Keeps objects that are not more than numberRestrictObjects;

  private int index;
  private RestrictedObject[] restrictedObjects;

  public PoolObject(RestrictedObject[] restrictedObjects) {

    this.restrictedObjects = restrictedObjects;
    this.index = restrictedObjects.length - 1;
  }

  /**
   * Acquired object if array is not empty.
   * @return RestrictObjects if available or throw Exception.
   * @throws NoAvailableObjectsException
   */
  public RestrictedObject acquire() throws NoAvailableObjectsException {

    if (index != -1) {
      RestrictedObject availableObject = restrictedObjects[index];
      restrictedObjects[index--] = null;
      return availableObject;
    } else {
      throw new NoAvailableObjectsException();
    }
  }

  /**
   * If index is different from size of the array
   * @param restrictedObject return object in array.
   */
  public void release(RestrictedObject restrictedObject) {
    if (index != restrictedObjects.length) {

      restrictedObjects[++index] = restrictedObject;
    }
  }
}
