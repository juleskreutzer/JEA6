package exceptions;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 02-03-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: exceptions
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
public class AccountNotFoundException extends Exception {
    public AccountNotFoundException() { super(); }

    public AccountNotFoundException(String message) { super(message); }
}
