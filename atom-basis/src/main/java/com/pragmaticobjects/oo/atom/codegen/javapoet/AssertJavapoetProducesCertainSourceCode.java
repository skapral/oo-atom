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
package com.pragmaticobjects.oo.atom.codegen.javapoet;

import com.pragmaticobjects.oo.atom.tests.Assertion;
import org.assertj.core.api.Assertions;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Checks that a {@link JavapoetSource} produces certain source file with certain content.
 *
 * @author Kapralov Sergey
 */
public class AssertJavapoetProducesCertainSourceCode implements Assertion {
    private final JavapoetSource source;
    private final Path expectedModule;
    private final String expectedCode;

    /**
     * Ctor.
     *
     * @param source {@link JavapoetSource} under test.
     * @param expectedModule expected source code module.
     * @param expectedCode expected source code.
     */
    public AssertJavapoetProducesCertainSourceCode(JavapoetSource source, Path expectedModule, String expectedCode) {
        this.source = source;
        this.expectedModule = expectedModule;
        this.expectedCode = expectedCode;
    }

    @Override
    public final void check() throws Exception {
        Path tempDirectory = Files.createTempDirectory("javapoetsource-test");
        source.saveIn(tempDirectory);
        Assertions.assertThat(tempDirectory.resolve(expectedModule)).isRegularFile();
        Assertions.assertThat(tempDirectory.resolve(expectedModule)).hasContent(expectedCode);
    }
}
