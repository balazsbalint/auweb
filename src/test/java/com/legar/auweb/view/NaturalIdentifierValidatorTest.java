package com.legar.auweb.view;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NaturalIdentifierValidatorTest {

    @ParameterizedTest(name = "valid: \"{0}\"")
    @ValueSource(strings = {
            "A",
            "EMPLOYEE",
            "A1",
            "TOTAL2024",
            "EMP-REC",
            "TOTAL-SUM-2024",
            "A0-B1-C2",
    })
    @DisplayName("Valid Natural identifiers should pass")
    void validIdentifiers_shouldPass(String id) {
        assertTrue(Utilities.isValidNaturalIdentifier(id), "Expected valid: " + id);
    }

    @ParameterizedTest(name = "invalid: \"{0}\"")
    @ValueSource(strings = {
            "",                  // empty
            "1A",                // starts with digit
            "-A",                // starts with hyphen
            "A-",                // ends with hyphen
            "A--B",              // double hyphen
            "A_B",               // underscore not allowed
            "A B",               // space not allowed
            "A.B",               // dot not allowed
            "ÁBC",               // non A–Z (depends on your rules; usually invalid)
            "A$B"                // special char
    })
    @DisplayName("Invalid Natural identifiers should fail")
    void invalidIdentifiers_shouldFail(String id) {
        assertFalse(Utilities.isValidNaturalIdentifier(id), "Expected invalid: " + id);
    }

    @Test
    @DisplayName("Null should be invalid (or handled safely)")
    void null_shouldBeInvalid() {
        assertFalse(Utilities.isValidNaturalIdentifier(null));
    }

    @Test
    @DisplayName("Length 32 is valid, length 33 is invalid")
    void lengthBoundary() {
        String len32 = "A" + "B".repeat(31); // total 32 chars
        String len33 = "A" + "B".repeat(32); // total 33 chars

        assertEquals(32, len32.length());
        assertEquals(33, len33.length());

        assertTrue(Utilities.isValidNaturalIdentifier(len32), "Expected valid length 32");
        assertFalse(Utilities.isValidNaturalIdentifier(len33), "Expected invalid length 33");
    }

    @Test
    @DisplayName("Hyphen placement edge cases")
    void hyphenEdgeCases() {
        assertTrue(Utilities.isValidNaturalIdentifier("A-B"));
        assertTrue(Utilities.isValidNaturalIdentifier("A1-B2"));

        assertFalse(Utilities.isValidNaturalIdentifier("A-"));
        assertFalse(Utilities.isValidNaturalIdentifier("-A"));
        assertFalse(Utilities.isValidNaturalIdentifier("A--B"));
        assertFalse(Utilities.isValidNaturalIdentifier("A---B"));
        assertFalse(Utilities.isValidNaturalIdentifier("A-B-")); // trailing hyphen
    }
}
