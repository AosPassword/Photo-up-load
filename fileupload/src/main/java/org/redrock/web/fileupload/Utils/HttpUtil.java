package org.redrock.web.fileupload.Utils;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {
    public static String factory(String target_url,String file_name) throws IOException {
        final String newLine = "\r\n";
        final String boundaryPrefix = "--";
        String param="media";
        // 定义数据分隔线
        String BOUNDARY = "----WebKitFormBoundaryVlXnBiHY4AAd1wGE";
        // 服务器的域名
        URL url = new URL(target_url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置为POST情
        conn.setRequestMethod("POST");
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        // 设置请求头参数
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
        conn.setRequestProperty("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");

        OutputStream out = new DataOutputStream(conn.getOutputStream());
        System.out.println("file_name------>"+file_name);
        File file = new File(file_name);
        StringBuilder sb = new StringBuilder();
        sb.append(boundaryPrefix);
        sb.append(BOUNDARY);
        sb.append(newLine);
        sb.append("Content-Disposition: form-data;name=\""+param+"\";filename=\"" + file_name + "\"" + newLine);
        // 参数头设置完以后需要两个换行，然后才是参数内容
        String contentType=null;
        if (file_name.endsWith(".jpg")){
            contentType="image/jpeg";
        }else if (file_name.endsWith(".png")){
            contentType="image/png";
        }
        if (contentType == null || contentType.equals("")) {
            contentType = "application/octet-stream";
        }
        sb.append("Content-Type: "+contentType);
        sb.append(newLine);
        sb.append(newLine);
        // 将参数头的数据写入到输出流中
        out.write(sb.toString().getBytes());


        // 数据输入流,用于读取文件数据
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        byte[] bufferOut = new byte[1024];
        int bytes = 0;
        // 每次读1KB数据,并且将文件数据写入到输出流中
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        // 最后添加换行
        out.write(newLine.getBytes());
        in.close();


        // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
        byte[] end_data = (newLine + boundaryPrefix + BOUNDARY + boundaryPrefix + newLine).getBytes();
        out.write(end_data);
        out.flush();
        out.close();
        int responseCode = conn.getResponseCode();
        if (responseCode==200){
            return changeInputStream(conn.getInputStream());
        }else {
            return "false";
        }
    }
    private static String changeInputStream(InputStream inputStream) {

        // 内存流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        String result = null;
        if (inputStream != null) {
            try {
                while ((len = inputStream.read(data)) != -1) {
                    byteArrayOutputStream.write(data, 0, len);
                }
                result = new String(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
