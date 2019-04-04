package com.lysoft.mvptemplete.ext;


import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

@GsonTypeAdapterFactory
public abstract class BaseTypeAdapterFactory implements TypeAdapterFactory {

    public static TypeAdapterFactory create(){
        return new AutoValueGson_BaseTypeAdapterFactory();
    }
}
