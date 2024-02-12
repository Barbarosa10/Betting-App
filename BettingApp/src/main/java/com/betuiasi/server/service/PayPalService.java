package com.betuiasi.server.service;

import com.paypal.api.payments.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PayPalService {

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    @Autowired
    private APIContext apiContext;
    @Autowired
    private UserService userService;

    public Map<String, Object> createPayment(String sum, String cancelUrl, String returnUrl){
        Map<String, Object> response = new HashMap<String, Object>();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(sum);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(returnUrl);
        payment.setRedirectUrls(redirectUrls);
        Payment createdPayment;
        try {
            String redirectUrl = "";
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            createdPayment = payment.create(context);
            if(createdPayment!=null){
                List<Links> links = createdPayment.getLinks();
                for (Links link:links) {
                    if(link.getRel().equals("approval_url")){
                        redirectUrl = link.getHref();
                        break;
                    }
                }
                response.put("status", "success");
                response.put("redirect_url", redirectUrl);
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error happened during payment creation!");
        }
        return response;
    }

    public Map<String, Object> completePayment(HttpServletRequest req){
        Map<String, Object> response = new HashMap<>();
        Payment payment = new Payment();
        payment.setId(req.getParameter("paymentId"));

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(req.getParameter("payerId"));
        try {
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            Payment createdPayment = payment.execute(context, paymentExecution);
            if(createdPayment!=null){
                Double totalAmount = Double.parseDouble(createdPayment.getTransactions().get(0).getAmount().getTotal());
                String funds = userService.addFunds(createdPayment.getPayer().getPayerInfo().getFirstName(), totalAmount);
                response.put("status", "success");
                response.put("funds", funds);
                System.out.println(createdPayment);
//                response.put("payment", createdPayment);
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        return response;
    }

//    public Payment createPayment(
//            Double total,
//            String cancelUrl,
//            String successUrl) throws PayPalRESTException{
//        Amount amount = new Amount();
//        amount.setCurrency("USD");
//        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
//        amount.setTotal(total.toString());
//
//        Transaction transaction = new Transaction();
//        transaction.setDescription("description");
//        transaction.setAmount(amount);
//
//        List<Transaction> transactions = new ArrayList<>();
//        transactions.add(transaction);
//
//        Payer payer = new Payer();
//        payer.setPaymentMethod("paypal");
//
//        Payment payment = new Payment();
//        payment.setIntent("sale");
//        payment.setPayer(payer);
//        payment.setTransactions(transactions);
//        RedirectUrls redirectUrls = new RedirectUrls();
//        redirectUrls.setCancelUrl(cancelUrl);
//        redirectUrls.setReturnUrl(successUrl);
//        payment.setRedirectUrls(redirectUrls);
//
//        return payment.create(apiContext);
//    }
//
//    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
//        Payment payment = new Payment();
//        payment.setId(paymentId);
//        PaymentExecution paymentExecute = new PaymentExecution();
//        paymentExecute.setPayerId(payerId);
//        return payment.execute(apiContext, paymentExecute);
//    }

}

