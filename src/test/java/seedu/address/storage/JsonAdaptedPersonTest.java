package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BENSON_NO_ADDRESS;
import static seedu.address.testutil.TypicalPersons.BENSON_NO_EMAIL;
import static seedu.address.testutil.TypicalPersons.BENSON_NO_NOTE;
import static seedu.address.testutil.TypicalPersons.BENSON_NO_PHONE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_APPOINTMENT = "aaaaaaaaaa";
    private static final String INVALID_SUBJECT = "dddddddd";
    private static final String INVALID_LEVEL = "eeeeeeee";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_NOTE = BENSON.getNote().toString();
    private static final String VALID_LEVEL = BENSON.getLevel().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedAppointment> VALID_APPOINTMENTS = BENSON.getAppointments()
            .asUnmodifiableObservableList()
            .stream()
            .map(JsonAdaptedAppointment::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedSubject> VALID_SUBJECTS = BENSON.getSubjects().stream()
            .map(JsonAdaptedSubject::new)
            .collect(Collectors.toList());


    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_NOTE, VALID_TAGS,
                        VALID_APPOINTMENTS, VALID_SUBJECTS, VALID_LEVEL);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_NOTE, VALID_TAGS,
                    VALID_APPOINTMENTS, VALID_SUBJECTS, VALID_LEVEL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_NOTE, VALID_TAGS,
                        VALID_APPOINTMENTS, VALID_SUBJECTS, VALID_LEVEL);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone() throws Exception {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_NOTE, VALID_TAGS,
                    VALID_APPOINTMENTS, VALID_SUBJECTS, VALID_LEVEL);
        assertEquals(BENSON_NO_PHONE, person.toModelType());
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_NOTE, VALID_TAGS,
                        VALID_APPOINTMENTS, VALID_SUBJECTS, VALID_LEVEL);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail() throws Exception {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_NOTE, VALID_TAGS,
                    VALID_APPOINTMENTS, VALID_SUBJECTS, VALID_LEVEL);
        assertEquals(BENSON_NO_EMAIL, person.toModelType());
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_NOTE, VALID_TAGS,
                        VALID_APPOINTMENTS, VALID_SUBJECTS, VALID_LEVEL);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress() throws IllegalValueException {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_NOTE, VALID_TAGS,
                    VALID_APPOINTMENTS, VALID_SUBJECTS, VALID_LEVEL);
        assertEquals(BENSON_NO_ADDRESS, person.toModelType());
    }

    @Test
    public void toModelType_nullNote() throws IllegalValueException {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null, VALID_TAGS,
                        VALID_APPOINTMENTS, VALID_SUBJECTS, VALID_LEVEL);
        assertEquals(BENSON_NO_NOTE, person.toModelType());
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_NOTE, invalidTags,
                        VALID_APPOINTMENTS, VALID_SUBJECTS, VALID_LEVEL);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidAppointments_throwsIllegalValueException() {
        List<JsonAdaptedAppointment> invalidAppointments = new ArrayList<>(VALID_APPOINTMENTS);
        invalidAppointments.add(new JsonAdaptedAppointment(INVALID_APPOINTMENT));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_NOTE, VALID_TAGS,
                        invalidAppointments, VALID_SUBJECTS, VALID_LEVEL);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
