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
package oo.atom.codegen.bytebuddy.task.utils;

import java.util.function.Consumer;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;
import oo.atom.anno.api.task.Task;
import org.mockito.Mockito;

/**
 *
 * @author Kapralov Sergey
 */
public class SmtAssumeTaskToGenerateBytecode {

    private final Task<StackManipulation> task;
    private final Consumer<MethodVisitor> methodVisitorScenario;

    public SmtAssumeTaskToGenerateBytecode(Task<StackManipulation> task, Consumer<MethodVisitor> methodVisitorScenario) {
        this.task = task;
        this.methodVisitorScenario = methodVisitorScenario;
    }

    public final void check() throws Exception {
        MethodVisitor methodVisitor = Mockito.mock(MethodVisitor.class);
        Implementation.Context implementationContext = Mockito.mock(Implementation.Context.class);

        task.result().peek(sm -> sm.apply(methodVisitor, implementationContext));
        methodVisitorScenario.accept(methodVisitor);
        Mockito.verifyNoMoreInteractions(methodVisitor);
    }
}
