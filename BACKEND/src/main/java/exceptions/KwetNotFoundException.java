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
public class KwetNotFoundException extends Exception {

    public KwetNotFoundException() { super(); }

    public KwetNotFoundException(String message) { super(message); }
}
