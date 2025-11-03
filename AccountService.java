package com.nimbus.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Transactional
    public void transferMoney(int fromAcc, int toAcc, double amount) {
        Account sender = accountDAO.getAccount(fromAcc);
        Account receiver = accountDAO.getAccount(toAcc);

        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance!");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        accountDAO.updateAccount(sender);
        accountDAO.updateAccount(receiver);
    }
}
