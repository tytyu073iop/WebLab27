package by.bsu.biryuk.Lab7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsu.biryuk.Lab7.entities.Account;
import by.bsu.biryuk.Lab7.repository.AccountRepository;

import java.util.Optional;

@Controller
public class GetAccountBalanceController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("pages/accountBalance")
    public String getAccountBalance(@RequestParam(required = false) String AccountId, Model model) {
        if (AccountId != null && !AccountId.isEmpty()) {
            Long accountId = Long.parseLong(AccountId);
            Optional<Account> accountOptional = accountRepository.findById(accountId);
            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();
                Double result = account.getBalance();
                model.addAttribute("balance", result);
            }
        }
        return "accountBalance";
    }
}
