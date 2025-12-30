package by.bsu.biryuk.Lab7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import by.bsu.biryuk.Lab7.entities.Account;
import by.bsu.biryuk.Lab7.repository.AccountRepository;

@Controller
public class GetAccountsController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("pages/accounts")
    public String getAccounts(
            @RequestParam(required = false) String ClientId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        if (ClientId != null && !ClientId.isEmpty()) {
            int clientId = Integer.parseInt(ClientId);
            Pageable pageable = PageRequest.of(page, size);
            Page<Account> result = accountRepository.getClientAccountsPage(clientId, pageable);
            model.addAttribute("accounts", result.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", result.getTotalPages());
            model.addAttribute("totalItems", result.getTotalElements());
            model.addAttribute("pageSize", size);
            model.addAttribute("clientId", clientId);
        }
        return "accounts";
    }
}
