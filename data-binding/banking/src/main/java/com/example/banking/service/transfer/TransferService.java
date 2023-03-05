package com.example.banking.service.transfer;

import com.example.banking.model.Transfer;
import com.example.banking.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransferService implements ITransferService{
    @Autowired
    private TransferRepository transferRepository;
    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        return transferRepository.findById(id);
    }

    @Override
    public Boolean existById(Long id) {
        return null;
    }

    @Override
    public Transfer save(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    @Override
    public void delete(Transfer transfer) {

    }

    @Override
    public void deleteById(Long id) {
    }
}
