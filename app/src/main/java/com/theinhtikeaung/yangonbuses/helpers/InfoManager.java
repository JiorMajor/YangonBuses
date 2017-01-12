package com.theinhtikeaung.yangonbuses.helpers;

import android.content.Context;

/**
 * Created by johnmajor on 12/1/17.
 */

public class InfoManager {

    private static InfoManager _info = null;
    private static Context context;
    private SecureProxy security;

    public InfoManager() {
        super();
    }

    public static InfoManager getInstance(Context c) {
        if(_info == null) {
            context = c;
            _info = new InfoManager();
            _info.openSecurity(c);
        }

        return _info;
    }

    private void openSecurity(Context c) {
        CertHelper ch = new CertHelper();
        String str = ch.getSHA1Fingerprint(c.getPackageManager(), c.getPackageName());
        this.security = new SecureProxy(str);
    }

    public String encryptText(String text) {
        return security.encrypt(text);
    }

    public String decryptText(String text) {
        return security.decrypt(text);
    }
}
