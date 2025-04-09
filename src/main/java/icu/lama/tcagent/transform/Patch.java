package icu.lama.tcagent.transform;

import javassist.CtClass;

import java.util.List;

public interface Patch {
    byte[] patch(CtClass clazz) throws Exception;

    List<String> getTargetClassName();
}
