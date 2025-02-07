package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.client.appointment.Appointment;
import seedu.address.model.client.policy.Policy;
import seedu.address.model.client.policy.UniquePolicyList;
import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client implements Comparable<Client> {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final UniquePolicyList policyList;
    private final FilteredList<Policy> filteredPolicies;

    private final Appointment appointment;

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Address address, Set<Tag> tags, UniquePolicyList policyList,
                  Appointment appointment) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.policyList = policyList; // TODO: @pangrwa Need to read from Storage
        this.appointment = appointment;
        filteredPolicies = new FilteredList<>(this.policyList.asUnmodifiableObservableList());
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public UniquePolicyList getPolicyList() {
        return policyList;
    }

    public ObservableList<Policy> getFilteredPolicyList() {
        return filteredPolicies;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return otherClient.getName().equals(getName())
                && otherClient.getPhone().equals(getPhone())
                && otherClient.getEmail().equals(getEmail())
                && otherClient.getAddress().equals(getAddress())
                && otherClient.getTags().equals(getTags())
                && otherClient.getPolicyList().equals(getPolicyList())
                && otherClient.getAppointment().equals(getAppointment());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, policyList);
    }

    @Override
    public String toString() {
        return this.getName().toString();
    }

    /**
     * Clone a {@code Client} with the details of {@code clientToEdit}
     * Make sure it is pass by value not by reference
     * @return
     */
    public Client cloneClient() {
        assert this != null;

        Name updatedName = this.getName();
        Phone updatedPhone = this.getPhone();
        Email updatedEmail = this.getEmail();
        Address updatedAddress = this.getAddress();
        //UniquePolicyList updatedPolicyList = editClientDescriptor.getPolicyList().orElse(clientToEdit.getAddress());
        Set<Tag> updatedTags = this.getTags();

        UniquePolicyList policyList = this.getPolicyList().clone(); // To change policyList you must use EditPolicy
        Appointment appointment = this.getAppointment();
        return new Client(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, policyList,
                appointment);
    }

    @Override
    public int compareTo(Client o) {
        return this.toString().compareTo(o.toString());
    }

    public String getPhoneNumber() {
        return this.getPhoneNumber().toString();
    }

}
