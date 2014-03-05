package com.clouway.pool;

/**
 * Created by clouway on 2/26/14.
 */
public class DemoPool {
  public static void main(String[] args) {
    RestrictedObject book1 = new Book();
    RestrictedObject book2 = new Book();
    RestrictedObject book3 = new Book();
    RestrictedObject[] books = {book1, book2, book3};
    PoolObject pool = new PoolObject(books);

    try {
      RestrictedObject restrictedObject1 = pool.acquire();
      RestrictedObject restrictedObject2 = pool.acquire();

      pool.release(restrictedObject2);

      RestrictedObject restrictedObject3 = pool.acquire();

      pool.release(restrictedObject3);

      RestrictedObject restrictedObject4 = pool.acquire();
      RestrictedObject restrictedObject5 = pool.acquire();

      pool.release(restrictedObject4);
      pool.release(restrictedObject5);

      RestrictedObject restrictedObject6 = pool.acquire();
      RestrictedObject restrictedObject7 = pool.acquire();
      RestrictedObject restrictedObject8 = pool.acquire();

    } catch (NoAvailableObjectsException e) {
      System.out.println("No available objects.");
    }
  }
}
