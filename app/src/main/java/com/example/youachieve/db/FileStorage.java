package com.example.youachieve.db;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileStorage {

    static public int readFile(String fileName, Context context, byte[] data) {
        if (fileName == null)
            return -1;

        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            int count = fileInputStream.read(data);
            Log.d("YouAchieve", "file " + fileName + " loaded");
            fileInputStream.close();
            return count;
        }
        catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    static public void writeFile(String fileName, Context context, byte[] data) {
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(data);
            outputStream.close();
            Log.d("YouAchieve", "file " + fileName + " saved");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public void deleteFile(String fileName, Context context) {
        if (fileName == null)
            return;

        context.deleteFile(fileName);
        Log.d("YouAchieve", "file " + fileName + " deleted");
    }

}
