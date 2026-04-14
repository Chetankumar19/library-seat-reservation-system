package com.libraryseatbooking.enums;

public enum PaymentStatus {
    PENDING,   // booking created, payment not done yet
    PAID,      // payment successful → booking confirmed
    FAILED     // payment failed → seat becomes free again
}