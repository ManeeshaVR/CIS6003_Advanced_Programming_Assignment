package lk.pahana.edu.pahana_edu_billing_system.persistence.bill.dao;

import lk.pahana.edu.pahana_edu_billing_system.business.bill.model.Bill;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.model.Invoice;

import java.util.List;

public interface BillDAO {

    boolean save(Bill bill);

    Invoice findLast();

    int getCount();

    List<Bill> findAll();

    Invoice findById(String billId);

}
