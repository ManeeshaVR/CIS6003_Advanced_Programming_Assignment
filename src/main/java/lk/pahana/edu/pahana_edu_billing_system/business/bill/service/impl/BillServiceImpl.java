package lk.pahana.edu.pahana_edu_billing_system.business.bill.service.impl;

import lk.pahana.edu.pahana_edu_billing_system.business.bill.dto.BillDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.dto.InvoiceDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.mapper.BillMapper;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.mapper.InvoiceMapper;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.model.Bill;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.service.BillService;
import lk.pahana.edu.pahana_edu_billing_system.persistence.bill.dao.BillDAO;
import lk.pahana.edu.pahana_edu_billing_system.persistence.bill.dao.impl.BillDAOImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BillServiceImpl implements BillService {

    private BillDAO billDAO = new BillDAOImpl();

    @Override
    public boolean saveBill(BillDTO billDTO) {
        billDTO.setBillId(UUID.randomUUID().toString());
        billDTO.setDate(LocalDateTime.now());
        return billDAO.save(BillMapper.toEntity(billDTO));
    }

    @Override
    public InvoiceDTO getLastBill() {
        return InvoiceMapper.toDTO(billDAO.findLast());
    }

    @Override
    public int getBillCount() {
        return billDAO.getCount();
    }

    @Override
    public List<BillDTO> getAllBills() {
        List<BillDTO> billDTOS = new ArrayList<>();
        List<Bill> bills = billDAO.findAll();
        for (Bill bill : bills) {
            billDTOS.add(BillMapper.toDTO(bill));
        }
        return billDTOS;
    }
}
