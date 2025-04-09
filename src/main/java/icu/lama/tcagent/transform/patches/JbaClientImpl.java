package icu.lama.tcagent.transform.patches;

import icu.lama.tcagent.Log;
import icu.lama.tcagent.transform.Patch;
import javassist.CtClass;
import javassist.CtMethod;

import java.util.List;

public class JbaClientImpl implements Patch {
    @Override
    public byte[] patch(CtClass clazz) throws Exception {
        CtMethod doOnlineVerification = clazz.getMethod("c", "(Ljetbrains/buildServer/a/d;)Ljava/util/Optional;");
        doOnlineVerification.setBody("return java.util.Optional.empty();");

        Log.info("JbaClientImpl patched");
        return clazz.toBytecode();
    }

    @Override
    public List<String> getTargetClassName() {
        return List.of("jetbrains.buildServer.a.a.d");
    }
}
