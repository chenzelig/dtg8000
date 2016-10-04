package com.daktel.MobileAlertCommunicator;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;   
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;   
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;   
import java.io.InputStream;   
import java.io.OutputStream;   
import java.nio.channels.FileChannel;

import android.os.Environment;

public class FileUtils {
    public static final String SDPATH = Environment.getExternalStorageDirectory() + "/";   
    
    private int FILESIZE = 4 * 1024;    
       
    public String getSDPATH(){   
        return SDPATH;   
    }   
       
    public FileUtils(){    
    }   
       
    /**   
     * 在SD卡上创建文件   
     * @param fileName   
     * @return   
     * @throws IOException   
     */   
    public static File createSDFile(String fileName) throws IOException{   
        File file = new File(SDPATH + fileName);   
        file.createNewFile();   
        return file;   
    }   
       
    /**   
     * 在SD卡上创建目录   
     * @param dirName   
     * @return   
     */   
    public static boolean createDir(String dirPath){   
    	if(isSDWriteable()){
	        File dir = new File(dirPath);
	        if(dir.exists()){
	        	return true;
	        }
	        try{
	        	return dir.mkdirs();
	        }catch(java.lang.Exception exp){
	        	exp.printStackTrace();
	        	return false;
	        }
    	}else{
    		return false;
    	}
    }   
       
	
	/**
	 * 获取SD卡是否可用，且为可写
	 * @return
	 */
	public static boolean isSDWriteable(){
		return Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())
				&& !Environment.MEDIA_MOUNTED_READ_ONLY
						.equals(Environment.getExternalStorageState());
	}	
	
    /**   
     * 判断SD卡上的文件夹是否存在   
     * @param fileName   
     * @return   
     */   
    public static boolean isFileExist(String filePath){   
        File file = new File(filePath);   
        return file.exists();   
    }   
    
	
    /**   
     * 获取文件长度
     * @param fileName   
     * @return   
     */   
    public static long getLength(String filePath){   
        File file = new File(filePath);   
        if(file.exists() && file.isFile()){
        	return file.length();
        }else{
        	return 0;
        }
    } 
    
    /**   
     * 获取文件名称
     * @param fileName   
     * @return   
     */   
    public static String getFileName(String filePath){   
        File file = new File(filePath);   
        return file.getName();
    } 
    
    /**
	 * 获取文件扩展名
	 * @param filename
	 * @param defExt
	 * @return
	 */
	public static String getExtension(String filename, String defExt) {
        if (filename.length() > 0) {
            int p = filename.lastIndexOf(".");

            if (p >= 0){
            	String extName = filename.substring(p);
            	int ap = extName.indexOf("?");
            	if(ap > 0){
            		extName = extName.substring(0, ap);
            	}
                return extName;
            }
        }
        return defExt;
    }
    
    public static boolean delFile(String filePath){   
        File file = new File(filePath);   
        try{
        	if(file.exists())return file.delete();
        }catch(java.lang.Exception exp){
        	exp.printStackTrace();
        	return false;
        }
        return true;
    }  
       
    /**   
     * 将一个InputStream里面的数据写入到SD卡中   
     * @param path   
     * @param fileName   
     * @param input   
     * @return   
     */   
    public File write2SDFromInput(String path,String fileName,InputStream input){   
        File file = null;   
        OutputStream output = null;   
        try {   
            createDir(path);   
            file = createSDFile(path + fileName);   
            output = new FileOutputStream(file);   
            byte[] buffer = new byte[256];  
            while(true){
            	 int numRead = input.read(buffer);
            	 if (numRead <= 0) {
                     break;
	             } else {
	            	 output.write(buffer, 0, numRead);
	             }

            }
           // while((input.read(buffer)) != -1){   
            //    output.write(buffer);   
            //}   
            output.flush();   
        }    
        catch (Exception e) {   
            Log.e("fileutils", e.toString());   
        }   
        finally{   
            try {   
               if(output!=null) output.close();   
            } catch (IOException e) {   
                Log.e("fileutils", e.toString());   
            }   
        }   
        return file;   
    }   
    
    /**
     * 获取扩展名
     * @param filePath
     * @return
     */
    public static String getExtensionName(String filePath){
    	int p = filePath.lastIndexOf(".");
    	if(p > 0){
    		return filePath.substring(p, filePath.length());
    	}else{
    		return "";
    	}
    }
    
    /**
     * 向文件写文本内容
     * @param filePath
     * @param text
     * @param append
     */
    public static void writeTextToFile(String filePath, String text, boolean append){
    	try {
    		createDir(filePath.substring(0,filePath.lastIndexOf("/")));
			FileWriter writer = new FileWriter(filePath, append);
			writer.write(text);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
    public static String readTextFromFile(String filePath){
    	try {
    		BufferedReader reader = new BufferedReader(new FileReader(filePath));
    		StringBuilder result = new StringBuilder();
    		String data = null;
    		while((data = reader.readLine()) != null){
    			result.append(data);
    			result.append("\r\n");
    		}
    		reader.close();
    		return result.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e("fileutils", e.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("fileutils", e.toString());
		}
		
		return null;
    	
    }
    
    /**
     * 文件复制
     * @param source
     * @param destination
     * @throws IOException
     */
	public static void copyFile(String source, String destination)
			throws IOException {

		FileChannel in = null, out = null;
		try {
			in = new FileInputStream(source).getChannel();
			File outFile = new File(destination);
			out = new FileOutputStream(outFile).getChannel();
			in.transferTo(0, in.size(), out);
		} catch (IOException e) {
			Log.e("fileutils", e.toString());
		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}

	}
	
	/**
	 * 清空指定目录下所有文件
	 * @param filepath
	 * @throws IOException
	 */
	public static void clearFiles(String filepath) throws IOException {
		File f = new File(filepath);// 定义文件路径
		if (f.exists() && f.isDirectory()) {// 判断是文件还是目录
			File files[] = f.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					files[i].delete();// 删除文件
				}
			}

		}
	}
  
	/**
	 * Move the file in oldLocation to newLocation.
	 */
	public static void moveFile(String oldLocation, String newLocation) throws IOException {
		File oldFile = new File(oldLocation);
		File newFile = new File(newLocation);
		boolean success = false;
		if (oldFile.exists()) {
			BufferedInputStream reader = new BufferedInputStream(
					new FileInputStream(oldLocation));
			BufferedOutputStream writer = new BufferedOutputStream(
					new FileOutputStream(newLocation, false));
			try {
				byte[] buff = new byte[8192];
				int count;
				while ((count = reader.read(buff, 0, buff.length)) != -1) {
					writer.write(buff, 0, count);
				}
				success = true;
			} catch (IOException ex) {
				throw new IOException("IOException when transferring "
						+ oldFile.getPath() + " to "
						+ newFile.getPath());
			} finally {
				try {
					if (reader != null) {
						writer.close();
						reader.close();
						if(success){
							oldFile.delete();
						}
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		} else {
			throw new IOException(
					"Old location does not exist when transferring "
							+ oldFile.getPath() + " to "
							+ newFile.getPath());
		}
	}
	
	
}
