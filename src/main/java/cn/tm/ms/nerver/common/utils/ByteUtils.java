package cn.tm.ms.nerver.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteUtils {

	public static final int BUFFER_SIZE = 4096;
	
	/**
	 * 将InputStream转换成byte数组  
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static byte[] inputStream2byte(InputStream in) throws IOException {  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] data = new byte[BUFFER_SIZE];  
        int count = -1;  
        while ((count = in.read(data, 0, BUFFER_SIZE))!=-1){
        	outStream.write(data, 0, count);  
        }
        data = null;  
        return outStream.toByteArray();  
    }
}
