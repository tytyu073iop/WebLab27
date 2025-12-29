package by.bsu.biryuk.Lab7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsu.biryuk.Lab7.entities.CreditCard;
import by.bsu.biryuk.Lab7.repository.CreditCardsRepository;

@Controller
public class DeactivateCardController {

    @Autowired
    private CreditCardsRepository creditCardsRepository;

    @GetMapping("pages/deactivateCard")
    public String showDeactivateCardForm() {
        return "deactivateCard";
    }

    @PostMapping("pages/deactivateCard")
    @Transactional
    public String deactivateCard(@RequestParam(required = false) String CardNum, Model model) {
        if (CardNum != null && !CardNum.isEmpty()) {
            CreditCard card = creditCardsRepository.getCreditCardByCardNumber(CardNum);
            if (card != null) {
                creditCardsRepository.deactivateCard(card);
                model.addAttribute("success", true);
            } else {
                model.addAttribute("success", false);
            }
        }
        return "deactivateCard";
    }
    
}
