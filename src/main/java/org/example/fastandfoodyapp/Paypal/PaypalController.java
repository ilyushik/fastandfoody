package org.example.fastandfoodyapp.Paypal;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class PaypalController {

    private final PaypalService paypalService;

    @GetMapping("/payment/{sum}")
    public String startPayment(@PathVariable("sum") int sum, Model model) {
        model.addAttribute("sum", sum);
        return "paypal/Index";
    }

    @PostMapping("/payment/create/{sum}")
    public RedirectView createPayment(@PathVariable("sum") int sum) {
        try {
            String cancelUrl = "http://localhost:8080/payment/cancel";
            String successUrl = "http://localhost:8080/payment/success";
            Payment payment = paypalService.createPayment(
                    Double.valueOf(sum),
                    "USD",
                    "Paypal",
                    "sale",
                    cancelUrl,
                    successUrl
            );

            for (Links links: payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return new RedirectView(links.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        } finally{}
        return new RedirectView("/payment/error");
    }

    @GetMapping("/payment/success")
    public String paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId
    ) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return "paypal/paymentSuccess";
            }
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        }
        return "paypal/paymentSuccess";
    }

    @GetMapping("/payment/cancel")
    public String paymentCancel() {
        return "paypal/paymentCancel";
    }

    @GetMapping("/payment/error")
    public String paymentError() {
        return "paypal/paymentError";
    }
}