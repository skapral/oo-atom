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
package oo.atom.codegen.bytebuddy.smt;

import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import oo.atom.r.RInferred;
import oo.atom.r.RSuccess;
import oo.atom.r.Result;

/**
 * {@link SmtReturnInteger} inference.
 *
 * @author Kapralov Sergey
 */
class SmtReturnIntegerInference implements Result.Inference<StackManipulation> {
    private final Integer returnValue;

    /**
     * Ctor.
     * @param returnValue return value.
     */
    public SmtReturnIntegerInference(Integer returnValue) {
        this.returnValue = returnValue;
    }

    @Override
    public final Result<StackManipulation> result() {
        return new RSuccess<>(
            new StackManipulation.Compound(
                IntegerConstant.forValue(returnValue),
                MethodReturn.INTEGER
            )
        );
    }
    
}

/**
 * Generates RETURN statement.
 *
 * @author Kapralov Sergey
 */
public class SmtReturnInteger extends RInferred<StackManipulation> implements StackManipulationToken {
    /**
     * Ctor.
     *
     * @param returnValue return value.
     */
    public SmtReturnInteger(Integer returnValue) {
        super(
            new SmtReturnIntegerInference(returnValue)
        );
    }
}
