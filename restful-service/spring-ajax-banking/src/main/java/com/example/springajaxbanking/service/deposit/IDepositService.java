package com.example.springajaxbanking.service.deposit;

import com.example.springajaxbanking.model.Deposit;
import com.example.springajaxbanking.model.dto.DepositViewDTO;
import com.example.springajaxbanking.service.IGeneralService;

import java.util.List;

public interface IDepositService extends IGeneralService<Deposit> {
    List<DepositViewDTO> findAllDepositViewDTO();
}
