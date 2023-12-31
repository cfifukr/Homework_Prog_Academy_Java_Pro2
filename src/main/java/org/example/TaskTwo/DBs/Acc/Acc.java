package org.example.TaskTwo.DBs.Acc;

import org.example.TaskTwo.DBs.Client;
import org.example.TaskTwo.DBs.Currency;

import javax.persistence.OneToMany;
import java.math.BigDecimal;

public interface Acc {


    Double getBalance();
    Client getClient();
    double convertToUAH(double value);
    double convertFromUAH(double value);
}
