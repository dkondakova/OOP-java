package ru.nsu.lab2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FactoryTest extends Assert {
    Factory factory;
    @Before
    public void setUp() {
        factory = Factory.getInstance();
    }

    @Test
    public void myTest0() {
        factory.makeCommand("DEFINE A 4".split(" ")).execute();
        factory.makeCommand("PUSH A".split(" ")).execute();
        factory.makeCommand("SQRT".split(" ")).execute();

        assertEquals(2, factory.getStack().pop(), 0.01);
    }

    @Test
    public void myTest1() {
        factory.makeCommand("PUSH 2".split(" ")).execute();// 2
        factory.makeCommand("PUSH 8".split(" ")).execute();// 2 8
        factory.makeCommand("MUL".split(" ")).execute();// 16
        factory.makeCommand("PUSH -1".split(" ")).execute();// 16 -1
        factory.makeCommand("PUSH -9".split(" ")).execute();// 16 -1 -9
        factory.makeCommand("DIVIDE".split(" ")).execute();// 16 9
        factory.makeCommand("PLUS".split(" ")).execute();// 25
        factory.makeCommand("SQRT".split(" ")).execute();// 5
        factory.makeCommand("DEFINE A -13".split(" ")).execute();
        factory.makeCommand("PUSH A".split(" ")).execute();// 5 -13
        factory.makeCommand("MINUS".split(" ")).execute();// -18

        assertEquals(-18, factory.getStack().pop(), 0.01);
    }
}