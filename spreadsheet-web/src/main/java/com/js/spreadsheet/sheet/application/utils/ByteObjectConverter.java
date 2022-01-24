package com.js.spreadsheet.sheet.application.utils;

import java.io.*;
import java.util.function.Function;

public class ByteObjectConverter {

    public static final Function<Object, byte[]> toByte = object -> {
        byte[] objectByte = null;

        try(ByteArrayOutputStream byteOs = new ByteArrayOutputStream();
            ObjectOutputStream objectOs = new ObjectOutputStream(byteOs)){
            objectOs.writeObject(object);
            objectByte = byteOs.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
        }

        return objectByte;
    };

    public static final Function<byte[], Object> toObject = bytes -> {
        Object object = null;
        try(ByteArrayInputStream byteIs = new ByteArrayInputStream(bytes); ObjectInputStream objectIs = new ObjectInputStream(byteIs)){
            object = objectIs.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return object;
    };
}
