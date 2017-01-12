package com.theinhtikeaung.yangonbuses.helpers;

import retrofit.mime.TypedString;

/**
 * Created by johnmajor on 13/1/17.
 */

public class TypedJsonString extends TypedString{

    public TypedJsonString(String string) {
        super(string);
    }

    @Override
    public String mimeType() {
        return "application/json";
    }
}
