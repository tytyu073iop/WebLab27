package by.bsu.biryuk.Lab7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsu.biryuk.Lab7.repository.PaymentsRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class GetSumPayments {

    @Autowired
    private PaymentsRepository paymentsRepository;

    @GetMapping("pages/sumPayments")
    public String getSumPayments(
            @RequestParam(required = false) String ClientId,
            @RequestParam(required = false) String dateFrom,
            @RequestParam(required = false) String dateTo,
            Model model) {
        
        if (ClientId != null && !ClientId.isEmpty() && 
            dateFrom != null && !dateFrom.isEmpty() && 
            dateTo != null && !dateTo.isEmpty()) {
            
            try {
                int clientId = Integer.parseInt(ClientId);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date from = dateFormat.parse(dateFrom);
                Date to = dateFormat.parse(dateTo);
                
                Double result = paymentsRepository.getClientPaymentsSum(clientId, from, to);
                model.addAttribute("sum", result);
            } catch (ParseException e) {
                // Handle date parsing error
                model.addAttribute("sum", null);
            } catch (NumberFormatException e) {
                // Handle ClientId parsing error
                model.addAttribute("sum", null);
            }
        }
        return "sumPayments";
    }
}
