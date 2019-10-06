package de.lukaskoerfer.simplepnml;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransitionTest {

    static Stream<String> ids() {
        return Stream.of(null, "", "   ", "test");
    }

    @ParameterizedTest
    @MethodSource("ids")
    void createAssignsValidId(String id) {
        Transition transition = Transition.create(id);
        assertNotNull(transition.getId());
        assertTrue(transition.getId().length() > 0);
        assertTrue(transition.getId().trim().length() > 0);
    }

}