/*
 * The MIT License
 *
 * Copyright 2017 Kapralov Sergey.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.pragmaticobjects.oo.atom.codegen.bytebuddy.matchers;

import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * Tests suite for {@link Extending}
 *
 * @author Kapralov Sergey
 */
public class ExtendingTest extends TestsSuite {
    /**
     * Ctor.
     */
    public ExtendingTest() {
        super(
            new TestCase(
                "matches types inherited from atom", 
                new AssertThatTypeMatches(
                    new TypeDescription.ForLoadedType(
                        Bar.class
                    ), 
                    new Extending(
                        ElementMatchers.anyOf(Foo.class)
                    )
                )
            ),
            new TestCase(
                "mismatch atoms",
                new AssertThatTypeDoesNotMatch(
                    new TypeDescription.ForLoadedType(
                        Foo.class
                    ), 
                    new Extending(
                        ElementMatchers.anyOf(Foo.class)
                    )
                )
            )
        );
    }

    //CHECKSTYLE:OFF
    private static class Foo {
    }

    private static class Bar extends Foo {
    }
}
