package by.bsu.biryuk.Lab7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsu.biryuk.Lab7.entities.Account;
import by.bsu.biryuk.Lab7.entities.CreditCard;
import by.bsu.biryuk.Lab7.repository.AccountRepository;
import by.bsu.biryuk.Lab7.repository.CreditCardsRepository;

import java.util.Optional;

@Controller
public class MakeAPayController {

    @Autowired
    private CreditCardsRepository creditCardsRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("pages/makeAPay")
    public String showMakeAPayForm() {
        return "makeAPay";
    }

    @PostMapping("pages/makeAPay")
    @Transactional
    public String makeAPay(
            @RequestParam(required = false) String CardNum,
            @RequestParam(required = false) String AccountIdTo,
            @RequestParam(required = false) String Sum,
            Model model) {
        
        if (CardNum != null && !CardNum.isEmpty() &&
            AccountIdTo != null && !AccountIdTo.isEmpty() &&
            Sum != null && !Sum.isEmpty()) {
            
            try {
                CreditCard card = creditCardsRepository.getCreditCardByCardNumber(CardNum);
                Long accountId = Long.parseLong(AccountIdTo);
                Double amount = Double.parseDouble(Sum);
                
                if (card != null) {
                    Optional<Account> accountOptional = accountRepository.findById(accountId);
                    if (accountOptional.isPresent()) {
                        Account toAccount = accountOptional.get();
                        creditCardsRepository.makePayment(card, toAccount, amount);
                        model.addAttribute("success", true);
                    } else {
                        model.addAttribute("success", false);
                    }
                } else {
                    model.addAttribute("success", false);
                }
            } catch (NumberFormatException e) {
                model.addAttribute("success", false);
            }
        }
        
        return "makeAPay";
    }
}
