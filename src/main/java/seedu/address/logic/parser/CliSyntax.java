package seedu.address.logic.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_NOTE = new Prefix("nt/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_APPOINTMENT = new Prefix("ap/");

    /*
       Incorrect but acceptable prefixes. We may add more as required.
     */
    public static final HashMap<Prefix, List<String>> INCORRECT_PREFIX_MAP = new HashMap<>();

    static {
        // Populate the map with common incorrect prefixes
        INCORRECT_PREFIX_MAP.put(PREFIX_NAME, Arrays.asList("name/", "nae/", "nam/"));
        INCORRECT_PREFIX_MAP.put(PREFIX_PHONE, Arrays.asList("phone/", "phon/", "pho/", "ph/"));
        INCORRECT_PREFIX_MAP.put(PREFIX_EMAIL, Arrays.asList("email/", "emai/", "eml/", "em/", "ema/"));
        INCORRECT_PREFIX_MAP.put(PREFIX_ADDRESS, Arrays.asList("address/", "addr/", "add/",
                "ad/", "addres/", "adress/"));
        INCORRECT_PREFIX_MAP.put(PREFIX_NOTE, Arrays.asList("note/", "not/", "nt/"));
        INCORRECT_PREFIX_MAP.put(PREFIX_TAG, Arrays.asList("tag/", "ta/", "tg/"));
        INCORRECT_PREFIX_MAP.put(PREFIX_APPOINTMENT, Arrays.asList("appointment/", "appt/", "apt/",
                "appoint/", "app/"));
    }


}
