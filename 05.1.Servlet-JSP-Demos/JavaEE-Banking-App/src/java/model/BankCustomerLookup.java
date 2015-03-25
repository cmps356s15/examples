package model;

import model.BankCustomer;
import java.util.*;

public class BankCustomerLookup {
  // Makes a small table of banking customers.
  
  private static Map<String,BankCustomer> customers;

  static {
    customers = new HashMap<String,BankCustomer>();
    customers.put("id001",
                  new BankCustomer("id001",
                                   "Ali",
                                   "Hacker",
                                   -3456.78));
    customers.put("id002",
                  new BankCustomer("id002",
                                   "Jane",
                                   "Hacker",
                                   1234.56));
    customers.put("id003",
                  new BankCustomer("id003",
                                   "Rich",
                                   "Hacker",
                                   987654.32));
  }
  
  public static Map<String,BankCustomer> getSampleCustomers() {
    return(customers);
  }

  /** Finds the customer with the given ID.
   *  Returns null if there is no match.
   */
  
  public static BankCustomer getCustomer(String id) {
    return(customers.get(id));
  }
  
  private BankCustomerLookup() {} // Non-instantiatable class
}
