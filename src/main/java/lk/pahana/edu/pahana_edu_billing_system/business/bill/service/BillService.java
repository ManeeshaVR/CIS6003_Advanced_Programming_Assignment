package lk.pahana.edu.pahana_edu_billing_system.business.bill.service;

import lk.pahana.edu.pahana_edu_billing_system.business.bill.dto.BillDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.dto.InvoiceDTO;

import java.util.List;

public interface BillService {

    boolean saveBill(BillDTO billDTO);

    InvoiceDTO getLastBill();

    int getBillCount();

    List<BillDTO> getAllBills();

    InvoiceDTO findBillById(String billId);

}
