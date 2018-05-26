package org.redrock.web.fileupload.Utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class FileUpLoadUtil {
    public static String[] upload(String file_path,MultipartFile file){
        String file_name=file.getOriginalFilename();
        long time=new Date().getTime();
        String strTime=String.valueOf(time);
        String shortTime=strTime.substring(4);
        file_name=shortTime+file_name;
        String file_whole_name=file_path+File.separator+file_name;
        try{
            File target_path=new File(file_path);
            if (!target_path.exists()){
                target_path.mkdirs();
            }
            FileOutputStream fileOutputStream=new FileOutputStream(file_whole_name);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String[] strings=new String[2];
        strings[0]=file_whole_name;
        strings[1]=file_name;
        return strings;
    }
}
