package com.example.banking.service.deposit;

import com.example.banking.model.Deposit;
import com.example.banking.repository.DepositRepository;
import com.example.banking.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface IDepositService extends IGeneralService<Deposit> {
}
