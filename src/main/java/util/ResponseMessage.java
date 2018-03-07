package util;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 26-02-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: util
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
public class ResponseMessage {

    /**
     * General error response messages
     */
    public final static String PARAM_MISSING = "Not all required parameters are provided";

    /**
     * Error response messages for user API
     */

    public final static String UNABLE_TO_CREATE_ACCOUNT = "Unable to create new account";
    public final static String EMAIL_ALLREADY_REGISTERED = "The provided email address is allready registered for an account";
    public final static String ACCOUNT_NOT_FOUND_WITH_PROVIDED_EMAIL = "No account has been found with the provided email address";
    public final static String ACCOUNT_NOT_FOUND_WITH_PROVIDED_ID = "No account has been found with the provided ID";

    /**
     * Error response messages for Kwet API
     */

    public final static String UNABLE_TO_CREATE_KWET = "Unable to create new Kwet";
    public final static String KWET_NOT_FOUND_WITH_PROVIDED_ID = "Couldn't find kwet with provided id";
}
