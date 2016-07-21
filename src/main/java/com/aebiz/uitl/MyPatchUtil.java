package com.aebiz.uitl;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于SVN方式的打包工具类  适用于B2B2C新产品
 * 增量更新包
 * @author fengdm
 *
 */
public class MyPatchUtil {
	   
		public static String projectNoDirPath = "/haoxinqing/haoxinqingall";//从SVN更新下来在src目录之前的项目路径，此例为单点系统的路径
	    
	    public static String patchFile="G:/SVN/package/patch.txt";//将SVN更新下来的文件记录保存为patch.txt
	 
	    public static String projectPath="G:/SVN/hxq/haoxinqingall";//项目文件夹路径  
	      
	    public static String webContent="src/main/webapp";//web应用文件夹名  
	      
	    public static String classPath="G:/SVN/hxq/haoxinqingall/target/classes";//class存放路径  
	      
	    public static String desPath="G:/SVN/package";//补丁文件包存放路径  
	      
	    public static String version="2016030718";//补丁版本  

	      
	    /** 
	     * 执行文件复制，打出指定补丁版本的增量更新包 
	     * @param args 
	     * @throws Exception  
	     */  
	    public static void main(String[] args) throws Exception {  
	    	System.out.println("run...............");
	        copyFiles(getPatchFileList());  
	        System.out.println("end...............");
	    }  
	    
	    /*
	     * 
	     * 
	     */
	    public static List<String> getPatchFileList() throws Exception{  
	        List<String> fileList=new ArrayList<String>();  
	        File file = new File(desPath);
	        if (!file.exists()) {
	        	file.mkdir();
	        }
	        try {
	        	 FileInputStream f = new FileInputStream(patchFile);   
	 	        BufferedReader dr=new BufferedReader(new InputStreamReader(f));  
	 	        String line;  
	 	        while((line=dr.readLine())!=null){   
	 	        	
	 	        	//以下注释代码适用于SVN打补丁方式
//	 	            if(line.indexOf("Index:")!=-1){  
//	 	                line=line.replaceAll(" ","");  
//	 	                line=line.substring(line.indexOf(":")+1,line.length());  
//	 	                fileList.add(line);  
//	 	            }  
	 	        	
	 	        	if(line.indexOf(projectNoDirPath)!=-1){  
	 	                line=line.replaceAll(" ","");  
	 	                line=line.substring(projectNoDirPath.length(),line.length());  
	 	                fileList.add(line);  
	 	            }  
	 	        }   
			} catch (Exception ex) {
				System.out.println("打包失败，文件："+patchFile+"不存在，请先用 SVN 打好补丁文件patch.txt后再执行");
			}
	        return fileList;  
	    }  
	    
	    /**
	     * 文件复制
	     * @param list
	     */
	    public static void copyFiles(List<String> list){  
	          
	        for(String fullFileName:list){  
	            if(fullFileName.indexOf("src/main/java")!=-1){//对源文件目录下的文件处理  
	                String fileName=fullFileName.replace("src/main/java","");  
	                fullFileName=classPath+fileName;  
	                if(fileName.endsWith(".java")){  
	                    fileName=fileName.replace(".java",".class");  
	                    fullFileName=fullFileName.replace(".java",".class");  
	                }  
	                String tempDesPath=fileName.substring(0,fileName.lastIndexOf("/"));  
	                String desFilePathStr=desPath+"/"+version+"/ROOT/WEB-INF/classes"+tempDesPath;  
	                String desFileNameStr=desPath+"/"+version+"/ROOT/WEB-INF/classes"+fileName;  
	                File desFilePath=new File(desFilePathStr);  
	                if(!desFilePath.exists()){  
	                    desFilePath.mkdirs();  
	                }  
	                copyFile(fullFileName, desFileNameStr);  
	                System.out.println(fullFileName+"复制完成");  
	            }else{//对普通目录的处理  
	                String desFileName=fullFileName.replaceAll(webContent,"");  
	                
	                if(fullFileName.indexOf("src/main/webapp")!=-1){
	                	desFileName=fullFileName.replaceAll("src/main/webapp","");
	                } else if (fullFileName.indexOf("src/main/resources")!=-1) {
	                	desFileName=fullFileName.replaceAll("src/main/resources","");
	                }
	                
	                fullFileName=projectPath+"/"+fullFileName;//将要复制的文件全路径  
	                
	                 
	                String fullDesFileNameStr=desPath+"/"+version+"/ROOT/"+desFileName;
	               if (fullFileName.indexOf("src/main/resources")!=-1) {
	            	     fullDesFileNameStr=desPath+"/"+version+"/ROOT/WEB-INF/classes"+desFileName;
	                }
	                
	                 
	                String desFilePathStr=fullDesFileNameStr.substring(0,fullDesFileNameStr.lastIndexOf("/"));  
	                File desFilePath=new File(desFilePathStr);  
	                if(!desFilePath.exists()){  
	                    desFilePath.mkdirs();  
	                }  
	                copyFile(fullFileName, fullDesFileNameStr);  
	                System.out.println(fullDesFileNameStr+"复制完成");  
	            }  
	              
	        }  
	          
	    }  
	    
	  /**
	   * 复制文件
	   * @param sourceFileNameStr
	   * @param desFileNameStr
	   */
	    private static void copyFile(String sourceFileNameStr, String desFileNameStr) {  
	        File srcFile=new File(sourceFileNameStr);  
	        File desFile=new File(desFileNameStr);  
	        try {  
	            copyFile(srcFile, desFile);  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	      
	    /**
	     * 复制文件
	     * @param sourceFile
	     * @param targetFile
	     * @throws IOException
	     */
	    public static void copyFile(File sourceFile, File targetFile) throws IOException {  
	        BufferedInputStream inBuff = null;  
	        BufferedOutputStream outBuff = null;  
	        try {  
	            // 新建文件输入流并对它进行缓冲  
	            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));  
	  
	            // 新建文件输出流并对它进行缓冲  
	            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));  
	  
	            // 缓冲数组  
	            byte[] b = new byte[1024 * 5];  
	            int len;  
	            while ((len = inBuff.read(b)) != -1) {  
	                outBuff.write(b, 0, len);  
	            }  
	            // 刷新此缓冲的输出流  
	            outBuff.flush();  
	        } finally {  
	            // 关闭流  
	            if (inBuff != null)  
	                inBuff.close();  
	            if (outBuff != null)  
	                outBuff.close();  
	        }  
	    }  
}
