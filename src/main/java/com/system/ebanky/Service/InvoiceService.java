package com.system.ebanky.Service;
import com.system.ebanky.DTO.InvoiceDTO;

import java.util.List;

public interface InvoiceService {

    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);

    InvoiceDTO getInvoiceById(Long invoiceId);

    List<InvoiceDTO> getInvoicesByUserId(Long userId);

    List<InvoiceDTO> getAllInvoices();

    InvoiceDTO updateInvoice(Long invoiceId, InvoiceDTO invoiceDTO);

    boolean deleteInvoice(Long invoiceId);

    InvoiceDTO markInvoiceAsPaid(Long invoiceId);

    List<InvoiceDTO> getOverdueInvoices();
}
