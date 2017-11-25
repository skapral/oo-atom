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

package oo.atom.codegen.javassist.plugin;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.bytecode.AccessFlag;
import oo.atom.codegen.javassist.templates.Atoms;

public class InlineTemplates implements Plugin {
    @Override
    public final void operateOn(final CtClass clazz, final ClassPool classPool) {
        try {
            if(clazz.isAnnotation() || clazz.isInterface() || clazz.getName().equals(Atoms.class.getName())) {
                return;
            }
            // @todo #78 Of course inlined methods are not bridge. It's workaroung for the issue https://github.com/hcoles/pitest/issues/420. To remove it after resolution.
            {
                final CtMethod equal = classPool.get(Atoms.class.getName()).getDeclaredMethod("atom$equal");
                CtMethod m = CtNewMethod.copy(equal, "atom$equal", clazz, null);
                m.setModifiers(AccessFlag.STATIC | AccessFlag.PRIVATE | AccessFlag.SYNTHETIC | AccessFlag.BRIDGE);
                clazz.addMethod(m);
            }
            {
                final CtMethod hashCode = classPool.get(Atoms.class.getName()).getDeclaredMethod("atom$hashCode");
                CtMethod m = CtNewMethod.copy(hashCode, "atom$hashCode", clazz, null);
                m.setModifiers(AccessFlag.STATIC | AccessFlag.PRIVATE | AccessFlag.SYNTHETIC | AccessFlag.BRIDGE);
                clazz.addMethod(m);
            }
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
