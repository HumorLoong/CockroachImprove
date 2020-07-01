package com.talkweb.lxl.cockroachlib.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author : LongXiaolin
 * @date : 2020/6/24
 * Email   :528953828@qq.com
 * description :
 */
public class FileUtil {
    /**
     * 读取文件转str
     * @param strFilePath
     * @param packageName
     * @return
     */
    public static String readFile2Str(String strFilePath, String packageName) {
        StringBuilder content = new StringBuilder("");
        File file = new File(strFilePath);
        //如果path是传递过来的参数，可以做一个非目录的判断
        try {
            InputStream inStream = new FileInputStream(file);
            InputStreamReader inputReader = new InputStreamReader(inStream);
            BufferedReader buffReader = new BufferedReader(inputReader);
            String line;
            //分行读取
            while ((line = buffReader.readLine()) != null) {
                //字体加粗红色标识
                if (line.contains(packageName)){
                    content.append("<font color='#FF0000'><b>");
                    content.append(line);
                    content.append("</b></font>");
                    content.append("\n");
                }else {
                    content.append(line);
                    content.append("\n");
                }
            }
            inStream.close();
            inputReader.close();
            buffReader.close();
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
            Log.d("FileUtil", "The File doesn't not exist.");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("FileUtil", "" + e.getMessage());
        }
        return content.toString();
    }
}
