package seedu.address.model.client.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PolicyTest {

    private PolicyName name = new PolicyName("Health");
    private CustomDate date = new CustomDate("01.02.2023");
    private Premium premium = new Premium("200");
    private Frequency frequency = new Frequency("quarterly");
    private Policy policy = new Policy(name, date, premium, frequency);

    @Test
    void getPolicyName() {
        PolicyName name = policy.getPolicyName();
        assertEquals(name.toString(), "Health");
    }

    @Test
    void getStartDate() {
        CustomDate date = policy.getStartDate();
        assertNotEquals(date.toString(), "01/02/2023");
    }

    @Test
    void getPremium() {
        Premium premium = policy.getPremium();
        assertEquals(premium.toString(), "200");
    }

    @Test
    void getFrequency() {
        Frequency frequency = policy.getFrequency();
        assertEquals(frequency.toString(), "quarterly");
    }

    @Test
    void isSamePolicy() {
        PolicyName name1 = new PolicyName("Health");
        CustomDate date1 = new CustomDate("01.02.2023");
        Premium premium1 = new Premium("200");
        Frequency frequency1 = new Frequency("quarterly");
        Policy policy1 = new Policy(name1, date1, premium1, frequency1);

        assertTrue(policy.equals(policy1));
    }
}

