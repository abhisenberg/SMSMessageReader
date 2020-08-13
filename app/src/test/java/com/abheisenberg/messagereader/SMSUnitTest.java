package com.abheisenberg.messagereader;

import com.abheisenberg.messagereader.utils.Utility;

import org.junit.Test;
import static org.junit.Assert.*;

public class SMSUnitTest {

    @Test
    public void test_isPersonalSMS(){

        /*
        isMessagePersonal() is a function that returns true if the sender is a normal person.
        And returns false if it is a transactional/promotional message.

        Test case 1 is a personal number so it should return true.
        Test case 2 is a bank sms so it should return false.
        Test case 3 is a merchant so it should return false.
         */

        String senderTestCase1 = "+917503564945";
        assertTrue(Utility.isMessagePersonal(senderTestCase1));

        String senderTestCase2 = "IB-ICICI";
        assertFalse(Utility.isMessagePersonal(senderTestCase2));

        String senderTestCase3 = "IDPAYTM";
        assertFalse(Utility.isMessagePersonal(senderTestCase3));
    }

    @Test
    public void test_isTransactionalSMS(){

        /*
        isMessageTransactional() is a function that returns true if sms is a transactional message,
        i.e. it denotes some kind of exchange of money to/from the user's account.
        And returns false otherwise.

        Test case 1 is an advertisement msg, not a transactional msg, so should return false.
        Test case 1 is a promotional msg, not a transactional msg, so should return false.
        Test case 3 is transactional sms so it should return true.
        Test case 4 is transactional sms so it should return true.
         */

        String bodyTestCase1 = "Dear customer, you loan of Rs. 20L is approved.";
        assertFalse(Utility.isMessageTransactional(bodyTestCase1));

        String bodyTestCase2 = "Buy glasses for INR 300 only!";
        assertFalse(Utility.isMessageTransactional(bodyTestCase2));

        String bodyTestCase3 = "Dear Mr. John, your account has been credited with Rs 30,500.50";
        assertTrue(Utility.isMessageTransactional(bodyTestCase3));

        String bodyTestCase4 = "INR 500 have been paid from your GPay wallet.";
        assertTrue(Utility.isMessageTransactional(bodyTestCase4));
    }

    @Test
    public void test_parseAmountFromMessage(){

        /*
        parseAmountFromMessage() is a function the amount of money that is transacted from the
        account (if any), otherwise returns null;

        In Test case 1, a particular amount is credited in the account, hence it should return that.
        In Test case 2, a particular amount is paid from the account, hence it should return that.
        In Test case 3, no amount is present, hence should return null.
        In Test case 4, no amount is present, hence should return null.
         */

        String bodyTestCase1 = "Dear Mr. John, your account has been credited with Rs 30,500.50.";
        assertEquals(Utility.parseAmountFromMessage(bodyTestCase1), "30,500.50");

        String bodyTestCase2 = "INR 500 have been paid from your GPay wallet.  Your Balance is Rs. 65,000.";
        assertEquals(Utility.parseAmountFromMessage(bodyTestCase2), "500");

        String bodyTestCase3 = "Good morning dear customer. Your s.no. is 450.";
        assertNull(Utility.parseAmountFromMessage(bodyTestCase3));

        String bodyTestCase4 = "You have walked 1000 miles today!";
        assertNull(Utility.parseAmountFromMessage(bodyTestCase4));
    }

}
