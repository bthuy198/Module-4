package com.example.springajaxbanking.service.transfer;

import com.example.springajaxbanking.model.Transfer;
import com.example.springajaxbanking.model.dto.TransferViewDTO;
import com.example.springajaxbanking.service.IGeneralService;

import java.util.List;

public interface ITransferService extends IGeneralService<Transfer> {
    List<TransferViewDTO> findAllTransferViewDTO();
}
