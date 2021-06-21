package com.futurefix.wallsnakano;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Save {

    private Context TheThis;
    Bitmap ImageToSave;

    public void SaveImage(Context context, BitmapDrawable image) {


        ImageToSave = image.getBitmap();
        TheThis = context;
        String nameOfFolder = "/NakanosWallpapers";
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + nameOfFolder;
        String CurrentDateAndTime = getCurrentDateAndTime();
        File dir = new File(file_path);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String nameOfFile = "Wallpaper";
        File file = new File(dir, nameOfFile + CurrentDateAndTime + ".jpg");

        try {
            FileOutputStream fOut = new FileOutputStream(file);// Aqui esta el pedo
            ImageToSave.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();
            MakeSureFileWasCreatedThenMakeAvabile(file);
            AbleToSave();
        } catch(IOException e) {
            UnableToSave();
        }

    }

    private void MakeSureFileWasCreatedThenMakeAvabile(File file){
        MediaScannerConnection.scanFile(TheThis,
                new String[] { file.toString() } , null,
                (path, uri) -> {
                });
    }

    private String getCurrentDateAndTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-­ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    private void UnableToSave() {
        Toast.makeText(TheThis, "¡No se ha podido guardar la imagen!", Toast.LENGTH_SHORT).show();
    }

    private void AbleToSave() {
        Toast.makeText(TheThis, "Imagen guardada en la galería.", Toast.LENGTH_SHORT).show();
    }
}
