package exceptions;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 02-03-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: PACKAGE_NAME
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
public class EmailAllreadyRegisteredException extends Exception {

    public EmailAllreadyRegisteredException() { super(); }

    public EmailAllreadyRegisteredException(String message) { super(message); }
}
