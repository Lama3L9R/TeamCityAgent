package icu.lama.tcagent;

import icu.lama.tcagent.transform.ClassPatchTransformer;
import icu.lama.tcagent.transform.patches.JbaClientImpl;
import icu.lama.tcagent.transform.patches.JwtLicenseDecoder;
import icu.lama.tcagent.transform.patches.LicenseKeysManagerImpl;

import java.lang.instrument.Instrumentation;

public class Main {
    public static void premain(String args, Instrumentation inst) {
        Log.println("TeamCity Agent loaded!");
        Log.println("NOTE: Opensource under Anti-996 License");
        Log.println("NOTE: No commercial use is allowed! This is for educational purposes only.");
        Log.println("NOTE: Any consequences of using this agent shall be borne by the user.");
        Log.println("");

        inst.addTransformer(new ClassPatchTransformer(
                new JbaClientImpl(), // bypass online verification
                new JwtLicenseDecoder(), // bypass license verification
                new LicenseKeysManagerImpl() // bypass even more license verification
        ));
    }
}
