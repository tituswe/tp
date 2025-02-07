package seedu.address.model.client.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * A class that represents the meetup date of an {@code Appointment} in the format of dd.mm.yyyy.
 */
public class MeetupDate {
    public static final String MESSAGE_CONSTRAINTS =
            "date should only contain numbers, in the format of dd.mm.yyyy";

    public static final String VALIDATION_REGEX = "^\\d{2}\\.\\d{2}.\\d{4}$";

    public static final String MESSAGE_PAST_DATE = "Appointment dates have to be scheduled in advanced."
            + " Today's date is " + LocalDate.now();

    public final LocalDate value;

    /**
     * Constructs an empty {@code MeetupDate}.
     */
    public MeetupDate() {
        value = null;
    }

    /**
     * Constructs a {@code MeetupDate}
     * @param value The meetup date of the appointment.
     */
    public MeetupDate(String value) {
        requireNonNull(value);
        checkArgument(isValidDate(value), MESSAGE_CONSTRAINTS);
        checkArgument(!isFutureDate(value), MESSAGE_PAST_DATE);
        this.value = stringToDate(value);
    }


    /**
     * Converts a string into a LocalDate object.
     * String has to be in the format of dd.MM.yyyy.
     *
     * @param date String to be converted into LocalDate object.
     * @return LocalDate object.
     */
    public static LocalDate stringToDate(String date) {
        DateTimeFormatter sf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.from(sf.parse(date));
        return localDate;
    }

    /**
     * Returns true if the given date is after the current date.
     * @param date The appointment date.
     */
    public static boolean isFutureDate(String date) {
        LocalDate localDate = stringToDate(date);
        LocalDate currentDate = LocalDate.now();
        return currentDate.isAfter(localDate);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String date) {
        boolean valid = true;
        try {
            LocalDate localDate = stringToDate(date);
        } catch (DateTimeParseException e) {
            valid = false;
        }
        return (date.matches(VALIDATION_REGEX) && valid);
    }

    public String getDisplayString() {
        if (value == null) {
            return null;
        }
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
        return outputFormat.format(value);
    }

    @Override
    public String toString() {
        if (value == null) {
            return null;
        }
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return outputFormat.format(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof MeetupDate)) {
            return false;
        }

        MeetupDate md = (MeetupDate) other;
        if (md.value == null && value == null) {
            return true;
        }
        if (md.value == null || value == null) {
            return false;
        }
        return value.equals(md.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
