package com.abheisenberg.messagereader.utils;

import android.util.Log;

import com.abheisenberg.messagereader.sms.SMS;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    public static final String TAG = "Utility";

    /*
    This function takes in 3 parameters: sender name, msg body, epoch timestamp,
    and returns a SMS object ONLY if the SMS is not a personal one, is a transactional one,
    and has an amount in it.
    Otherwise returns null.
     */
    public static SMS parseCommercialSMSOnly(String sender, String body, String epoch){
        if(isMessagePersonal(sender))
            return null;

        body = body.trim();
        if(!isMessageTransactional(body))
            return null;


        String amountInSMS = parseAmountFromMessage(body);

        if(amountInSMS == null)
            return null;


        String dateOfSMS = convertEpochToTime(Long.parseLong(epoch));

        return new SMS(sender, body, dateOfSMS, amountInSMS);
    }

    /*
    Process the sms only if it is not personal.
    To check this, we will check if it is coming from senders named like AK-ICICI
    or from senders having 6 digit numbers, ex- 681198

    Both of these are only used for commercial purposes, hence these are not personal messages.
     */
    public static boolean isMessagePersonal(String sender){

        //Pattern for getting out the merchants name
        //ex: AK-ICICI
        Pattern bankName = Pattern.compile("^[a-zA-Z0-9]{2}-?[a-zA-Z0-9]{4,8}$");

        //ex: 681198
        Pattern sixDigitName = Pattern.compile("^[0-9]{6}$");

        Matcher bankMatcher = bankName.matcher(sender);
        Matcher sixDigitMatcher = sixDigitName.matcher(sender);

        return !(bankMatcher.matches() || sixDigitMatcher.matches());
    }


    /* Check if it is a transactional message by checking whether it contains
    words like "amount", "added", "credited", "debited", "paid", "received" etc

    Check if the message contains words like "pay now", "loan" etc, it means
    that the message might be promotional.
    */
    public static boolean isMessageTransactional(String body){
        Pattern isTransactional = Pattern.compile("(?i)(\\bamount\\b|\\bcredit(ed)?\\b|\\bdebit(ed)?\\b|\\bpaid\\b|\\breceived\\b)");
        Matcher isTransacMatcher = isTransactional.matcher(body);

        Pattern exludeWords = Pattern.compile("(?i)(\\bloan\\b|\\bpay now\\b|\\bapply\\b)");
        Matcher excludeMatcher = exludeWords.matcher(body);

        return isTransacMatcher.find() && !excludeMatcher.find();
    }

    /* Pattern for parsing out the amount in the SMS
    ex: Rs. 1900, Rs 2300, INR 4566 etc.
     */
    public static String parseAmountFromMessage(String body){
        Pattern quote = Pattern.compile("(?i)(?:(?:RS|RS.|INR|MRP)\\.?\\s?)(\\d+(:?\\,\\d+)?(\\,\\d+)?(\\.\\d{1,2})?)");
        Matcher quoteMatcher = quote.matcher(body);

        if(quoteMatcher.find()){
            String withCurrency = quoteMatcher.group();

            Pattern amountP = Pattern.compile("(\\d+(:?\\,\\d+)?(\\,\\d+)?(\\.\\d{1,2})?)");
            Matcher amountMatcher = amountP.matcher(withCurrency);

            if(amountMatcher.find()){
                return amountMatcher.group();
            }
        }
        return null;
    }

    /*
    Converts the epoch time received in the sms to date formatted string.
     */
    public static String convertEpochToTime(long epoch){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        Date date = new Date(epoch);
        return sdf.format(date);
    }

}
