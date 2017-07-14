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
package oo.atom.codegen.bytebuddy.task.equals;

import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import oo.atom.anno.api.task.TInferred;
import oo.atom.anno.api.task.Task;
import oo.atom.anno.api.task.TaskInference;

class SmtBoxFieldInference implements TaskInference<StackManipulation> {

    private final FieldDescription field;

    public SmtBoxFieldInference(FieldDescription field) {
        this.field = field;
    }

    @Override
    public final Task<StackManipulation> task() {
        TypeDescription type = field.getType().asErasure();
        if (type.isPrimitive()) {
            return new SmtBox(type);
        } else {
            return new SmtDoNothing();
        }
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class SmtBoxField extends TInferred<StackManipulation> implements Task<StackManipulation> {

    public SmtBoxField(FieldDescription field) {
        super(new SmtBoxFieldInference(field));
    }
}
