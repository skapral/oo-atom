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
package com.pragmaticobjects.oo.atom.codegen.bytebuddy.validator;

import io.vavr.collection.List;
import net.bytebuddy.description.type.TypeDescription;

/**
 * A combined validator. Passes if all {@link Validator} instances are passed, combines
 * issues from all {@link Validator} instances if at least one of them failed.
 *
 * @author Kapralov Sergey
 */
public class ValComplex implements Validator {
    private final List<Validator> validators;

    /**
     * Ctor.
     *
     * @param validators a list of validators to combine
     */
    public ValComplex(List<Validator> validators) {
        this.validators = validators;
    }

    /**
     * Ctor.
     *
     * @param validators a list of validators to combine
     */
    public ValComplex(Validator... validators) {
        this(
            List.of(validators)
        );
    }

    @Override
    public final List<String> errors(TypeDescription type) {
        return validators.flatMap(v -> v.errors(type));
    }
}