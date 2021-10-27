package me.korpusovmax.sann;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class ExampleTest {

    @BeforeEach
    public void beforeEach() {
        System.out.println("runs before each");
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("runs before all");
    }

    @Test
    public void exampleSuccessPass() {
        int val1 = 1;
        int val2 = 10;
        assert val1 != val2;
    }

    @Test
    public void exampleFailPass() {
        int val1 = 1;
        int val2 = 10;
        assert val1 == val2;
    }
}
