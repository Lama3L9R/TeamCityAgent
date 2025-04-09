package icu.lama.tcagent.transform.patches;

import icu.lama.tcagent.Log;
import icu.lama.tcagent.transform.Patch;
import icu.lama.tcagent.transform.Utils;
import javassist.CtClass;
import javassist.CtMethod;

import java.util.List;

public class JwtLicenseDecoder implements Patch {

    @Override
    public byte[] patch(CtClass clazz) throws Exception {
        Utils.nopMethod(clazz, "verifyExpirationDate");
        Utils.nopMethod(clazz, "verifyCertificateChain");
        Utils.nopMethod(clazz, "verifySignature");

        CtMethod getChainFromHeader = clazz.getDeclaredMethod("getChainFromHeader");
        getChainFromHeader.setBody("{ java.util.ArrayList list = new java.util.ArrayList(); list.add(null); return list; }");

        Log.info("JwtLicenseDecoder patched");
        return clazz.toBytecode();
    }

    @Override
    public List<String> getTargetClassName() {
        return List.of("jetbrains.buildServer.license.decoder.JwtLicenseDecoder");
    }
}
