package pl.kopytka.payment.application.exception;

public class PaymentNotFoundException extends RuntimeException {
    
    public PaymentNotFoundException(String message) {
        super(message);
    }
}
