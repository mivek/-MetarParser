package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class SmallHailSizeCommand implements Command {
    /** Hail size less than. */
    private static final Pattern HAIL_SIZE_LESS_THAN = Pattern.compile("^GR LESS THAN ((\\d )?(\\d/\\d)?)");


    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public SmallHailSizeCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] hailParts = Regex.pregMatch(HAIL_SIZE_LESS_THAN, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Hail.LesserThan", hailParts[1])).append(" ");
        return pRemark.replaceFirst(HAIL_SIZE_LESS_THAN.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(HAIL_SIZE_LESS_THAN, pInput);
    }
}