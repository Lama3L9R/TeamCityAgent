package icu.lama.tcagent.transform;

import javassist.CtClass;
import javassist.CtMethod;

public class Utils {
    public static void nopMethod(CtMethod method) throws Exception {
        method.setBody("return;");
    }

    public static void nopMethod(CtClass clazz, String method) throws Exception {
        nopMethod(clazz.getDeclaredMethod(method));
    }

}
