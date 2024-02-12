package com.betuiasi.server.controller;

import com.betuiasi.server.persistence.Data.Order;
import com.betuiasi.server.service.PayPalService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class PaymentController {

    private final PayPalService payPalClient;
    @Autowired
    PaymentController(PayPalService payPalClient){
        this.payPalClient = payPalClient;
    }

    @PostMapping(value = "/pay")
    public Map<String, Object> makePayment(@RequestBody Order order){
        System.out.println(order);
        return payPalClient.createPayment(String.valueOf(order.getPrice()), order.getRedirectUrl(), order.getRedirectUrl());
    }

    @PostMapping(value = "/complete/payment")
    public Map<String, Object> completePayment(HttpServletRequest request,  @RequestParam("paymentId") String paymentId, @RequestParam("payerId") String payerId){
        return payPalClient.completePayment(request);
    }


//    @Autowired
//    PayPalService service;

//    public static final String SUCCESS_URL = "pay/success";
//    public static final String CANCEL_URL = "pay/cancel";
//    public static double price;

//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }

//    @PostMapping("/api/users/pay")
//    public String payment(@RequestBody Order order) {
//        try {
//            price = order.getPrice();
//
//            Payment payment = service.createPayment(order.getPrice(), order.getRedirectUrl(),
//                    order.getRedirectUrl());
//            for(Links link:payment.getLinks()) {
//                if(link.getRel().equals("approval_url")) {
//                    return String.format("{\"forwardLink\": \"%s\"}", link.getHref());
////                    return "redirect:"+link.getHref();
//                }
//            }
//
//        } catch (PayPalRESTException e) {
//
//            e.printStackTrace();
//        }
//        return String.format("{\"forwardLink\": \"%s\"}", "/");
////        return "redirect:/";
//    }

//    @GetMapping(value = CANCEL_URL)
//    public String cancelPay() {
//        return "cancel";
//    }
//
//    @GetMapping(value = SUCCESS_URL)
//    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
//        try {
//            Payment payment = service.executePayment(paymentId, payerId);
//            System.out.println(payment.toJSON());
//            if(hasSufficientFunds(1))
//                if (payment.getState().equals("approved")) {
//                    return String.format("{\"forwardLink\": \"%s\"}", "/");
//                }
//        } catch (PayPalRESTException e) {
//            System.out.println(e.getMessage());
//        }
//        return String.format("{\"forwardLink\": \"%s\"}", "/");
////        return "redirect:/";
//    }

    // Placeholder method to check if the payer has sufficient funds
//    public boolean hasSufficientFunds(double accountBalance) {
//
//        // Check if the account balance is above the threshold
//        System.out.println("BALANCEEEE: " + accountBalance + "    " + price);
//        return accountBalance >= price;
//    }

}