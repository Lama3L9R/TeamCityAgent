package icu.lama.tcagent.transform;

import icu.lama.tcagent.Log;
import javassist.ClassPool;
import javassist.CtClass;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.HashMap;

public class ClassPatchTransformer implements ClassFileTransformer {
    private final HashMap<String, Patch> patches = new HashMap<>();

    public ClassPatchTransformer(Patch... patches) {
        for (Patch patch : patches) {
            patch.getTargetClassName().forEach(className -> {
                this.patches.put(className.replace(".", "/"), patch);
            });
        }
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!patches.containsKey(className)) {
            return classfileBuffer;
        }

        ClassPool classPool = ClassPool.getDefault();
        try {
            CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
            Patch patch = patches.get(className);

            return patch.patch(ctClass);
        } catch (Exception e) {
            Log.error("Failed to patch class " + className, e);
        }

        return classfileBuffer;
    }
}
