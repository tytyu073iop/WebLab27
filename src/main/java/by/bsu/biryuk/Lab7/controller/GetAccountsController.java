package by.bsu.biryuk.Lab7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsu.biryuk.Lab7.entities.Account;
import by.bsu.biryuk.Lab7.repository.AccountRepository;

import java.util.List;

@Controller
public class GetAccountsController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("pages/accounts")
    public String getAccounts(@RequestParam(required = false) String ClientId, Model model) {
        if (ClientId != null && !ClientId.isEmpty()) {
            int clientId = Integer.parseInt(ClientId);
            List<Account> result = accountRepository.getClientAccounts(clientId);
            model.addAttribute("accounts", result);
        }
        return "accounts";
    }
}
