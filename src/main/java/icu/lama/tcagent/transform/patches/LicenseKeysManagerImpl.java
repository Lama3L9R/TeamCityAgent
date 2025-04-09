package icu.lama.tcagent.transform.patches;

import icu.lama.tcagent.Log;
import icu.lama.tcagent.transform.Patch;
import javassist.CtClass;
import javassist.CtMethod;

import java.util.List;

public class LicenseKeysManagerImpl implements Patch {
    @Override
    public byte[] patch(CtClass clazz) throws Exception {
        CtMethod applyJwtLicense = clazz.getMethod("b", "(Ljava/lang/String;)Ljetbrains/buildServer/a/d;");
        applyJwtLicense.setBody("return new jetbrains.buildServer.a.d($1, $0.y);");

        Log.info("LicenseKeysManagerImpl patched");

        return clazz.toBytecode();
    }

    @Override
    public List<String> getTargetClassName() {
        return List.of("jetbrains.buildServer.a.o");
    }
}
