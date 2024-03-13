package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Remark;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses the remark command
 * Adapted from: @see <a href="https://nus-cs2103-ay2324s2.github.io/tp/tutorials/AddRemark.html">AddRemark tutorial</a>
 */
public class RemarkCommandParser implements Parser {
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    @Override
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            RemarkCommand.MESSAGE_USAGE), ive);
        }

        Remark remark = new Remark(argMultimap.getValue(PREFIX_REMARK).orElse(""));

        return new RemarkCommand(index, remark);
    }
}
