package com.example.ticket_flight;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AvatarService {
    public void saveImagePNGfromUri(Context context, Uri uri, String fileName) {
        ContentResolver contentResolver = context.getContentResolver();
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            // Open InputStream from the URI
            inputStream = contentResolver.openInputStream(uri);
            if (inputStream != null) {
                // Decode InputStream to Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                // Get the correct rotation
                int rotation = getExifRotation(contentResolver, uri);
                if (rotation != 0) {
                    // Rotate the bitmap
                    bitmap = rotateBitmap(bitmap, rotation);
                }

                // Create or open the file in internal storage
                File file = new File(context.getFilesDir(), fileName);
                fileOutputStream = new FileOutputStream(file);

                // Compress and save the bitmap to the file in PNG format
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                System.out.println("Image saved successfully to internal storage.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int getExifRotation(ContentResolver contentResolver, Uri uri) {
        int rotation = 0;
        try {
            InputStream inputStream = contentResolver.openInputStream(uri);
            if (inputStream != null) {
                ExifInterface exif = new ExifInterface(inputStream);
                int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        rotation = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        rotation = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        rotation = 270;
                        break;
                }
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotation;
    }

    private Bitmap rotateBitmap(Bitmap bitmap, int rotation) {
        Matrix matrix = new Matrix();
        matrix.postRotate(rotation);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    // Method to load image from internal storage
    public Bitmap loadImageFromInternalStorage(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        if (file.exists()) {
            return BitmapFactory.decodeFile(file.getAbsolutePath());
        }
        return null;
    }

    // Method to copy image from one file to another
    public void copyImage(Context context, String sourceFileName, String destinationFileName) {
        File sourceFile = new File(context.getFilesDir(), sourceFileName);
        File destinationFile = new File(context.getFilesDir(), destinationFileName);

        if (sourceFile.exists()) {
            FileInputStream fileInputStream = null;
            FileOutputStream fileOutputStream = null;

            try {
                // Create input and output streams
                fileInputStream = new FileInputStream(sourceFile);
                fileOutputStream = new FileOutputStream(destinationFile);

                // Copy the file content
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fileInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, length);
                }

                System.out.println("Image copied successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Source file does not exist.");
        }
    }
}