package org.redrock.web.fileupload.controllers;


import com.google.gson.Gson;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.redrock.web.fileupload.Utils.FileUpLoadUtil;
import org.redrock.web.fileupload.Utils.HttpUtil;
import org.redrock.web.fileupload.clazz.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileUpLoadController {


    @RequestMapping(value = "/fileupload",method = RequestMethod.POST)
    public String getfile(@RequestParam("file") MultipartFile file , HttpServletRequest request , HttpServletResponse response) throws IOException {
        String filePath="D:\\OTA";
        System.out.println(filePath);
        String[] file_name=FileUpLoadUtil.upload(filePath,file);
        String result=null;
        if (file_name[0]!=null){
            result = HttpUtil.factory("https://web.everphoto.cn/api/cv/shinkai/web",file_name[0]);
        }
        System.out.println(result);
        Gson gson =new Gson();
        Message message=gson.fromJson(result,Message.class);
        String url=message.getData().getUrl();
        request.setAttribute("oldUrl","http://scplayer.nat300.top/OTA/"+file_name[1]);
        request.setAttribute("newUrl",url);

        return "picture";
    }

    @RequestMapping(value = "/demo")
    @ResponseBody
    public String demo(){
        return "success";
    }
}
