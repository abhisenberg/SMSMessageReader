package com.abheisenberg.messagereader.sms;

public class SampleJSON {
    public static String sample = "[\n" +
            "   {\n" +
            "      \"senderName\":\"JDICICIB\",\n" +
            "      \"date\":\"13/05/2020\",\n" +
            "      \"body\":\"Dear Customer, your Account XX545 has been credited with INR 90,000.00 on 09-AUG-20. Info: INF*INFT*022584696091*Abhish.\",\n" +
            "      \"amount\":\"90,000.00\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"senderName\":\"JDICICIB\",\n" +
            "      \"date\":\"11/05/2020\",\n" +
            "      \"body\":\"Dear Customer, your Account XX545 has been credited with INR 87,471.00 on 08-AUG-20. Info: INF*INFT*022582953561*Abhish.\",\n" +
            "      \"amount\":\"47,471.00\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"senderName\":\"VMICICIB\",\n" +
            "      \"date\":\"11/05/2020\",\n" +
            "      \"body\":\"Dear Customer, acct XXX545 is credited with Rs.5000.00 on 06-Aug-20 from abhishek181gupta-2@oksbi. UPI Ref no 021914998899\",\n" +
            "      \"amount\":\"5000.00\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"senderName\":\"VKICICIB\",\n" +
            "      \"date\":\"10/05/2020\",\n" +
            "      \"body\":\"Dear Customer, acct XXX545 is credited with Rs.10.00 on 06-Aug-20 from abhishek181gupta-2@oksbi. UPI Ref no 021914937829\",\n" +
            "      \"amount\":\"10.00\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"senderName\":\"TMICICIB\",\n" +
            "      \"date\":\"08/05/2020\",\n" +
            "      \"body\":\"INR3,500.00 debited on Credit Card XX4005 on 02-Aug-20.Info:PAYTM.Avbl Lmt:INR91,499.62.Call 18002662 for dispute or SMS BLOCK 4005 to 9215676766\",\n" +
            "      \"amount\":\"3,500.00\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"senderName\":\"TMICICIB\",\n" +
            "      \"date\":\"13/05/2020\",\n" +
            "      \"body\":\"215246 is OTP for txn of INR 3500.00 at PAYTM on ICICI Bank Credit Card XX4005. OTPs are SECRET. DO NOT disclose it to anyone. Bank NEVER asks for OTP.\",\n" +
            "      \"amount\":\"3500.00\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"senderName\":\"TMICICIB\",\n" +
            "      \"date\":\"08/05/2020\",\n" +
            "      \"body\":\"INR5,000.00 debited on Credit Card XX4005 on 02-Aug-20.Info:PAYTM.Avbl Lmt:INR94,999.62.Call 18002662 for dispute or SMS BLOCK 4005 to 9215676766\",\n" +
            "      \"amount\":\"5,000.00\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"senderName\":\"TMICICIB\",\n" +
            "      \"date\":\"07/05/2020\",\n" +
            "      \"body\":\"Dear Customer, Payment of INR 7100 has been received on your ICICI Bank Credit Card Account 4xxx4005 on 02-AUG-20.Thank you.\",\n" +
            "      \"amount\":\"7100\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"senderName\":\"JDICICIB\",\n" +
            "      \"date\":\"05/05/2020\",\n" +
            "      \"body\":\"Acct XX545 debited with INR 7,100.00 on 02-AUG-20.Info: BIL*002042624.Avbl Bal:INR 5,50,616.35.Call 18002662 for dispute or SMS BLOCK 733 to 9215676766\",\n" +
            "      \"amount\":\"7,100.00\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"senderName\":\"VKICICIB\",\n" +
            "      \"date\":\"05/05/2020\",\n" +
            "      \"body\":\"Txn of INR 250.00 done on Acct XX399 on 02-AUG-20.Info: IIN*I-Debit*P.Avbl Bal:INR 15,548.71.Call 18002662 for dispute or SMS BLOCK 399 to 9215676766\",\n" +
            "      \"amount\":\"250.00\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"senderName\":\"ADICICIB\",\n" +
            "      \"date\":\"05/05/2020\",\n" +
            "      \"body\":\"Dear Customer, acct XXX545 is credited with Rs.7.00 on 28-Jul-20 from goog-payment@okaxis. UPI Ref no 021021088914\",\n" +
            "      \"amount\":\"7.00\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"senderName\":\"TMICICIB\",\n" +
            "      \"date\":\"05/05/2020\",\n" +
            "      \"body\":\"Dear Customer, stmt for Credit Card XX4005 has been sent to ab96sh@gmail.com. Total amt of Rs. 7100 or Min. amt of Rs. 360 is due by 07-AUG-20.\",\n" +
            "      \"amount\":\"7100\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"senderName\":\"TMICICIB\",\n" +
            "      \"date\":\"03/05/2020\",\n" +
            "      \"body\":\"771877 is OTP for txn of INR 3501.82 at RELIANCE R on ICICI Bank Debit Card XX7190.OTPs are SECRET.DO NOT disclose it to anyone.Bank NEVER asks for OTP.\",\n" +
            "      \"amount\":\"3501.82\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"senderName\":\"JXJIOFBR\",\n" +
            "      \"date\":\"02/05/2020\",\n" +
            "      \"body\":\"We have received your request for JioFiber connection having Order Number NO0000ELKWNF. Kindly make an online payment of Rs.3501.82 to proceed.\\nTo pay, click http:\\/\\/tiny1.jio.com\\/696sSy\\nKindly note, this payment link is valid till 2020-07-25 11:25:37\",\n" +
            "      \"amount\":\"3501.82\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"senderName\":\"BPiPaytm\",\n" +
            "      \"date\":\"01/05/2020\",\n" +
            "      \"body\":\"Paid Rs.51 to Paytm Mobile Bill Payment from Paytm Balance. Updated Balance: Paytm Wallet- Rs 313.77. More Details: https:\\/\\/paytm.me\\/Sj-AgSG\",\n" +
            "      \"amount\":\"51\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"senderName\":\"AXICICIB\",\n" +
            "      \"date\":\"23/04/2020\",\n" +
            "      \"body\":\"066323 is OTP for txn of INR 654.90 at COMWAY MYA on ICICI Bank Debit Card XX6093.OTPs are SECRET.DO NOT disclose it to anyone.Bank NEVER asks for OTP.\",\n" +
            "      \"amount\":\"654.90\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"senderName\":\"JDICICIB\",\n" +
            "      \"date\":\"20/04/2020\",\n" +
            "      \"body\":\"Dear Customer, your Account XX545 has been credited with INR 90,000.00 on 18-Jul-20. Info: INF*INFT*022492043791*Abhish.\",\n" +
            "      \"amount\":\"90,000.00\"\n" +
            "   }\n" +
            "]";
}
