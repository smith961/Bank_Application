package com.example.BankApplicationDevelopment;

import com.example.BankApplicationDevelopment.service.BankingOperationService;
import com.example.BankApplicationDevelopment.service.TransactionService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BankApplicationDevelopmentApplicationTests {
    @Autowired
    private BankingOperationService bankingOperationService;

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testDeposit() throws MessagingException{
        assertEquals(351300.00, bankingOperationService.depositFund())
    }

    @Test
    public void testWithdrawal() throws MessagingException{
        assertEquals();
    }

	@Test
	void contextLoads() {
	}

}
