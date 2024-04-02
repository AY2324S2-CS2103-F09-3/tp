package seedu.address.ui;

import java.util.Comparator;
import java.util.concurrent.Flow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane subjects;
    @FXML
    private FlowPane level;
    @FXML
    private Label note;
    @FXML
    private VBox appointments;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().isEmpty() ? "-" : person.getPhone().value);
        address.setText(person.getAddress().isEmpty() ? "-" : person.getAddress().value);
        email.setText(person.getEmail().isEmpty() ? "-" : person.getEmail().value);
        note.setText(person.getNote().isEmpty() ? "-" : person.getNote().value);
        level.getChildren().add(new Label(person.getLevel().toString()));
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        person.getSubjects().stream()
                .sorted(Comparator.comparing(subject -> subject.getSubject()))
                .forEach(subject -> subjects.getChildren().add(new Label(subject.getSubject())));
        person.getAppointments().forEach(appointment -> appointments.getChildren().add(new Label(appointment.value)));
    }
}
