package com.goertek.commonlib.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtil {
    private static final String TAG = "FileUtil";
    private static final int BUF_SIZE = 1024;
    private static final String SHA256_NAME = "SHA-256";

    /**
     * inputStream中的内容写入到file中
     *
     * @param inputStream 输入到文件的内容流
     * @param file        目标文件
     * @return true 写入成功 false 写入失败
     */
    public static boolean writeToFile(InputStream inputStream, File file) {
        boolean isSuccess = false;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(inputStream);
            bos = new BufferedOutputStream(new FileOutputStream(file));

            int readLen = -1;
            byte[] buff = new byte[BUF_SIZE];
            while ((readLen = bis.read(buff)) != -1) {
                bos.write(buff, 0, readLen);
            }
            bos.flush();
            isSuccess = true;
        } catch (IOException e) {
            isSuccess = false;
            LogUtil.e(TAG, "writeToFile error!filepath:" + file.getAbsolutePath());
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }

    public static boolean deleteFile(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        LogUtil.i(TAG, "deleteFile() path=" + file.getAbsolutePath());
        if (file.isFile()) {
            return file.delete();
        }
        for (File listFile : file.listFiles()) {
            if (listFile.isFile()) {
                listFile.delete();
            } else if (listFile.isDirectory()) {
                deleteFile(listFile);
            } else {
                LogUtil.e(TAG, "deleteFile() unknown file type for " + listFile);
            }
        }
        return file.delete();
    }

    public static byte[] getFileContent(File file) {
        if (file == null || !file.exists()) {
            return new byte[0];
        }
        LogUtil.i(TAG, "getFileContent() path=" + file.getAbsolutePath());
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] bArr = new byte[fis.available()];
            fis.read(bArr);
            closeStream(fis);
            return bArr;
        } catch (IOException e) {
            LogUtil.e(TAG, "FileNotFoundException:" + e.getMessage());
        } finally {
            closeStream(fis);
        }
        return new byte[0];
    }

    public static String sha256File(String str) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(str);
            MessageDigest instance = MessageDigest.getInstance(SHA256_NAME);
            byte[] bArr = new byte[BUF_SIZE];
            int readLen = -1;
            while ((readLen = fis.read(bArr)) != -1) {
                instance.update(bArr, 0, readLen);
            }
            return HEXUtils.byteToHex(instance.digest());
        } catch (NoSuchAlgorithmException suchException) {
            LogUtil.e(TAG, "MessageDigest not support:" + suchException.getMessage());
        } catch (IOException ioException) {
            LogUtil.e(TAG, "calc IOException:" + ioException.getMessage());
        } finally {
            closeStream(fis);
        }
        return "";
    }

    private static void closeStream(FileInputStream fileInputStream) {
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                LogUtil.e(TAG, "closeStream() IOException:" + e.getMessage());
            }
        }
    }
}
