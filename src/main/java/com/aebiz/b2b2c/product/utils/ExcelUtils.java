package com.aebiz.b2b2c.product.utils;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.store.storeaccount.vo.StoreAccountModel;
import com.aebiz.b2b2c.store.storecompanyinfo.vo.StoreCompanyInfoModel;
import com.aebiz.b2b2c.store.storecomplex.vo.StoreComplexModel;
import com.aebiz.b2b2c.store.storecontract.vo.StoreContractModel;
import com.aebiz.b2b2c.store.storeemployee.vo.StoreEmployeeModel;
import com.hxq.mobile.entity.common.ActiveCode;

public class ExcelUtils {
	
	/**
	 * 读取Excel
	 * @param path 文件路径
	 * @param baseModel 要添加的Model对象
	 * @param columns 要设值的字段
	 * @return
	 * @throws IOException
	 */
	 public static List readExcel(String path,BaseModel baseModel,Object[] columns) throws IOException {
		 File file = new File(path);
		 String fName = file.getName();
		 String extension = fName.lastIndexOf(".") == -1 ? "" : fName
		 .substring(fName.lastIndexOf(".") + 1);
		 if ("xls".equals(extension)) {
			 // 2003版本
			 return readXls(file,baseModel,columns);
		 } else if ("xlsx".equals(extension)) {
			 // 2007版本
			 return readXlsx(file,baseModel,columns);
		 } else {
			 throw new IOException("不支持的文件类型:" + extension);
			 
		 }
		 
	 }
	 /**
	  * 读取Excel
	  * @param path 文件路径
	  * @param baseModel 要添加的Model对象
	  * @param columns 要设值的字段
	  * @return
	  * @throws IOException
	  */
	 public static List importExcel(MultipartFile file,BaseModel baseModel,Object[] columns) throws IOException {
       
       CommonsMultipartFile cf= (CommonsMultipartFile)file;
       DiskFileItem fi = (DiskFileItem)cf.getFileItem();

       String fName = fi.getName();
		 String extension = fName.lastIndexOf(".") == -1 ? "" : fName
				 .substring(fName.lastIndexOf(".") + 1);
		 if ("xls".equals(extension)) {
			 // 2003版本
			 return readXls1(fi.getInputStream(),baseModel,columns);
		 } else if ("xlsx".equals(extension)) {
			 // 2007版本
			 return readXlsx1(fi.getInputStream(),baseModel,columns);
		 } else {
			 throw new IOException("不支持的文件类型:" + extension);
			 
		 }
		 
	 }
	 
	 public static List importExcel2(MultipartFile file,ActiveCode baseModel,Object[] columns) throws IOException {
	       
	       CommonsMultipartFile cf= (CommonsMultipartFile)file;
	       DiskFileItem fi = (DiskFileItem)cf.getFileItem();

	       String fName = fi.getName();
			 String extension = fName.lastIndexOf(".") == -1 ? "" : fName
					 .substring(fName.lastIndexOf(".") + 1);
			 if ("xls".equals(extension)) {
				 // 2003版本
				 return readXls2(fi.getInputStream(),baseModel,columns);
			 } else if ("xlsx".equals(extension)) {
				 // 2007版本
				 return readXlsx2(fi.getInputStream(),baseModel,columns);
			 } else {
				 throw new IOException("不支持的文件类型:" + extension);
				 
			 }
			 
		 }
	 
	 
	 /**
	  * Read the Excel 2010
	  * @param file path the path of the excel file
	  * @param baseModel
	  * @param columns
	  * @return
	  */
    public static List readXlsx(File file,BaseModel baseModel,Object[] columns){
    	List list = new ArrayList();
    	try{
    		InputStream is = new FileInputStream(file);
 	        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
 	        // Read the Sheet
 	        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
 	            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
 	            if (xssfSheet == null) {
 	                continue;
 	            }
 	            // Read the Row
 	            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
 	                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
 	                if (xssfRow != null) {
 	                	//为对象赋值
						Class<BaseModel> tCls = (Class<BaseModel>) baseModel.getClass();
						String modelName = tCls.getName();
						Class model = Class.forName(modelName); 
						Object objModel = model.newInstance();
						for(int i=0;i<columns.length;i++){
							String fieldName = (String) columns[i]; 
							String setMethodName = "set"+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
							Method setMethod=tCls.getDeclaredMethod(setMethodName, new Class[]{String.class});
							setMethod.invoke(objModel, new Object[]{getXssFcValue(xssfRow.getCell(i))});
						}
						list.add(objModel);
 	                }
 	            }
 	        }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return list;
    }
    /**
     * Read the Excel 2010
     * @param file path the path of the excel file
     * @param baseModel
     * @param columns
     * @return
     */
    public static List readXlsx1(InputStream is,BaseModel baseModel,Object[] columns){
    	List list = new ArrayList();
    	try{
    		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
    		// Read the Sheet
    		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
    			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
    			if (xssfSheet == null) {
    				continue;
    			}
    			// Read the Row
    			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
    				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
    				if (xssfRow != null) {
    					//为对象赋值
    					Class<BaseModel> tCls = (Class<BaseModel>) baseModel.getClass();
    					String modelName = tCls.getName();
    					Class model = Class.forName(modelName); 
    					Object objModel = model.newInstance();
    					for(int i=0;i<columns.length;i++){
    						String fieldName = (String) columns[i]; 
    						String setMethodName = "set"+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    						Method setMethod=tCls.getDeclaredMethod(setMethodName, new Class[]{String.class});
    						setMethod.invoke(objModel, new Object[]{getXssFcValue(xssfRow.getCell(i))});
    					}
    					list.add(objModel);
    				}
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
    }
    public static List readXlsx2(InputStream is,ActiveCode baseModel,Object[] columns){
    	List list = new ArrayList();
    	try{
    		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
    		// Read the Sheet
    		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
    			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
    			if (xssfSheet == null) {
    				continue;
    			}
    			// Read the Row
    			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
    				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
    				if (xssfRow != null) {
    					//为对象赋值
    					Class<ActiveCode> tCls = (Class<ActiveCode>) baseModel.getClass();
    					String modelName = tCls.getName();
    					Class model = Class.forName(modelName); 
    					Object objModel = model.newInstance();
    					for(int i=0;i<columns.length;i++){
    						String fieldName = (String) columns[i]; 
    						String setMethodName = "set"+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    						Method setMethod=tCls.getDeclaredMethod(setMethodName, new Class[]{String.class});
    						setMethod.invoke(objModel, new Object[]{getXssFcValue(xssfRow.getCell(i))});
    					}
    					list.add(objModel);
    				}
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
    }
	    
	    
    /**
     * Read the Excel 2003
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public static List readXls(File file,BaseModel baseModel,Object[] columns){
    		
    	List list = new ArrayList();
    	try{
    		 InputStream is = new FileInputStream(file);
 	        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
 	        // Read the Sheet
 	        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
 	            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
 	            if (hssfSheet == null) {
 	                continue;
 	            }
 	            // Read the Row
 	            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
 	                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
 	                if (hssfRow != null) {
 	                	//为对象赋值
 	                	Class<BaseModel> tCls = (Class<BaseModel>) baseModel.getClass();
						String modelName = tCls.getName();
						Class model = Class.forName(modelName); 
						Object objModel = model.newInstance();
  	                	 for(int i=0;i<columns.length;i++){
  	 	                	String fieldName = (String) columns[i]; 
  	 	                	String setMethodName = "set"+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
  	 	                	Method setMethod=tCls.getDeclaredMethod(setMethodName, new Class[]{String.class});
  	 						setMethod.invoke(objModel, new Object[]{getHssFcValue(hssfRow.getCell(i))});
  	 	                }
  	                 list.add(objModel);
 	                }
 	            }
 	        }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return list;
    }
    
    /**
     * Read the Excel 2003
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public static List readXls1(InputStream is,BaseModel baseModel,Object[] columns){
    	
    	List list = new ArrayList();
    	try{
    		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
    		// Read the Sheet
    		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
    			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
    			if (hssfSheet == null) {
    				continue;
    			}
    			// Read the Row
    			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
    				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
    				if (hssfRow != null) {
    					//为对象赋值
    					Class<BaseModel> tCls = (Class<BaseModel>) baseModel.getClass();
    					String modelName = tCls.getName();
    					Class model = Class.forName(modelName); 
    					Object objModel = model.newInstance();
    					for(int i=0;i<columns.length;i++){
    						String fieldName = (String) columns[i]; 
    						String setMethodName = "set"+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    						Method setMethod=tCls.getDeclaredMethod(setMethodName, new Class[]{String.class});
    						setMethod.invoke(objModel, new Object[]{getHssFcValue(hssfRow.getCell(i))});
    					}
    					list.add(objModel);
    				}
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
    }
    public static List readXls2(InputStream is,ActiveCode baseModel,Object[] columns){
    	
    	List list = new ArrayList();
    	try{
    		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
    		// Read the Sheet
    		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
    			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
    			if (hssfSheet == null) {
    				continue;
    			}
    			// Read the Row
    			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
    				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
    				if (hssfRow != null) {
    					//为对象赋值
    					Class<ActiveCode> tCls = (Class<ActiveCode>) baseModel.getClass();
    					String modelName = tCls.getName();
    					Class model = Class.forName(modelName); 
    					Object objModel = model.newInstance();
    					for(int i=0;i<columns.length;i++){
    						String fieldName = (String) columns[i]; 
    						String setMethodName = "set"+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    						Method setMethod=tCls.getDeclaredMethod(setMethodName, new Class[]{String.class});
    						setMethod.invoke(objModel, new Object[]{getHssFcValue(hssfRow.getCell(i))});
    					}
    					list.add(objModel);
    				}
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
    }
	    
	    
    /**
     * 2007版excel单元格值转换
     * @param xssfRow
     * @return
     */
        @SuppressWarnings("static-access")
        public static String getXssFcValue(XSSFCell xssfRow) {
        	if(xssfRow != null){
        		 if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
 	                return String.valueOf(xssfRow.getBooleanCellValue());
 	            } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
 	            	DecimalFormat df = new DecimalFormat("0");  
 	                String strCell = df.format(xssfRow.getNumericCellValue()); 
 	                return strCell;
 	            } else {
 	                return String.valueOf(xssfRow.getStringCellValue());
 	            }
        	}
           return "" ;
        }
	        
	        
    /**
     * 2003版excel单元格值转换
     * @param hssfCell
     * @return
     */
    @SuppressWarnings("static-access")
    public static String getHssFcValue(HSSFCell hssfCell) {
    	if(hssfCell != null){
    		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
                return String.valueOf(hssfCell.getBooleanCellValue());
            } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            	DecimalFormat df = new DecimalFormat("0");  
                String strCell = df.format(hssfCell.getNumericCellValue()); 
                return strCell;
            } else {
                return String.valueOf(hssfCell.getStringCellValue());
            }
    	}
        return "" ;
    }
	        
	        
    /**
     * 按照整个Model对象导出excel2007
     * @param dataset  要导出的数据
     * @param response
     * @param tableName 导出的excel名称
     * @param headers 表头名称    
     * 				 例：String[] headers = { "商品编号", "商品名称", "创建时间"};
     * @param columns 要导出的字段
     * 				例：String[] columns = { "productNo", "productName", "createTime" },字段必须和对应的VO类中的字段一致;
     * 				如果导出某一字段为boolean类型，则需要new一个新的数组标识一下。如：vo类中“状态”字段为boolean类型,则columns为
     * 				String[] columns = { "productNo", "productName", "createTime"，new String[]{"字段名称","boolean","值为true时显示的值","值为false时显示的值"} }
     * 
     */
	
	public static void writeExcelByObject(Collection<Object> dataset,HttpServletResponse response,String tableName,Object[] headers,Object[] columns){
		
		try{
			// 声明一个工作薄2007
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 生成一个表格
            XSSFSheet sheet = workbook.createSheet(tableName);
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth((short) 15);
            // 生成一个样式
            XSSFCellStyle style = workbook.createCellStyle();
            /**
             * 设置样式规格
             */
            // 前景色(蓝色)
            XSSFColor myColor = new XSSFColor(Color.green);
		    style.setFillForegroundColor(myColor);
            // 设置单元格填充样式
		    style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            // 设置单元格边框
            style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style.setBorderTop(XSSFCellStyle.BORDER_THIN);
            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            // 生成一个字体
            XSSFFont font = workbook.createFont();
		    font.setFontHeightInPoints((short) 12);
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            // 把字体应用到当前的样式
            style.setFont(font);
            /**
             * 设置另一种样式
             */
            XSSFCellStyle style2 = workbook.createCellStyle();
            style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
            style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            // 生成另一个字体
            XSSFFont font2 = workbook.createFont();
            font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
            // 把字体应用到当前的样式
            style2.setFont(font2);
          //产生表格标题行
            XSSFRow row = sheet.createRow(0);
            String[] heads = (String[]) headers;
            if(headers !=null  && headers.length != 0){
            	for (int i = 0; i < heads.length; i++) {
		               XSSFCell cell = row.createCell(i);
		               cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		               cell.setCellStyle(style);
		               XSSFRichTextString text = new XSSFRichTextString(heads[i]);
		               cell.setCellValue(text);
		            }
            }
            
            
            /**
             * 遍历集合数据
             */
            int index = 0;
            Iterator<Object> it = dataset.iterator();
            while(it.hasNext()){
            	index++;
            	row = sheet.createRow(index);
            	Object obj = (Object) it.next();
            	//当字段类型为boolean时，true的显示值
            	String trueValue = "" ;
            	//当字段类型为boolean时，false的显示值
            	String falseValue = "" ;
            	//字段的类型
            	String columnType = "" ;
            	for (int j = 0; j < columns.length; j++){
            		//一个单元格
                	XSSFCell cell = row.createCell(j);
                	cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                	cell.setCellStyle(style2);
            		Object objFieldName = columns[j];  
            		String getType = "get" ;
            		if(objFieldName instanceof String[]){
            			String[] objArray = (String[])objFieldName ;
            			if(objArray.length>1){
        					objFieldName = objArray[0];
        					columnType = objArray[1];
        					if("boolean".equalsIgnoreCase(columnType)){
        						getType = "is" ;
        					}
            				if(objArray.length>2){
            					trueValue = objArray[2];
            				}
            				if(objArray.length>3){
            					falseValue = objArray[3];
            				}
            			}else{
            				objFieldName = objArray[0];
            			}
            		}
            		String fieldName = (String)objFieldName;
                    String getMethodName = getType+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);  
                    Class tCls = obj.getClass();  
                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});  
                    Object value = getMethod.invoke(obj, new Class[]{}); 
                    String objValue = null ;
                    if(value == null){
                    	objValue = "" ;
                    }else if(value instanceof Date){
                    	 Date date = (Date) value;  
                         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                         objValue = sdf.format(date);  
                    }else{
                    	objValue = String.valueOf(value);
                    }
                    if(objValue!= null){  
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");    
                        Matcher matcher = p.matcher(objValue);  
                        if(matcher.matches()){  
                           //是数字当作double处理  
                           cell.setCellValue(Double.parseDouble(objValue));  
                        }else{  
                        	 XSSFRichTextString xssfValue = new XSSFRichTextString(objValue);  
                        	 if("boolean".equalsIgnoreCase(columnType)){
                        		 if("true".equals(String.valueOf(xssfValue))){
                        			 cell.setCellValue(trueValue);
                        		 }else if("false".equals(String.valueOf(xssfValue))){
                        			 cell.setCellValue(falseValue);
                        		 }else{
                        			 cell.setCellValue(xssfValue);
                        		 }
                        	 }else{
                        		 cell.setCellValue(xssfValue);
                        	 }
                        }  
                     }  
            	}
            }
            
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try{  
                workbook.write(os);  
            }catch (Exception e){  
                e.printStackTrace();  
            } 
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((tableName+DateFormatHelper.getTimeStr("yyyy-MM-dd", new Date())+".xlsx").getBytes(), "iso-8859-1")); 
            ServletOutputStream out = response.getOutputStream();

            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            try {

                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);

                byte[] buff = new byte[2048];
                int bytesRead;

                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }

            } catch (final IOException e) {
                throw e;
            } finally {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            }
		}catch(Exception e){
			 e.printStackTrace();  
		}
	}
	    	
	 /**
	  * 按照某些字段进行查询后导出的excel2007
	  * @param dataset 要导出的数据
	  * @param response 
	  * @param tableName 表名 
	  * @param headers 表头
	  * 				例：String[] headers = { "商品编号", "商品名称", "创建时间"};
	  * @param columns 要导出的字段对应的位置
	  * 				例：String[] columns = { "0", "1", "2" },如果该数组为空则默认导出查询出全部字段;
	  */				
	    	 
	public static void writeExcelByArray(Collection<Object> dataset,HttpServletResponse response,String tableName,Object[] headers,Object[] columns){
		
		try{
			// XSSFWorkbook是2007
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 生成一个表格
            XSSFSheet sheet = workbook.createSheet(tableName);
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth((short) 15);
            // 生成一个样式
            XSSFCellStyle style = workbook.createCellStyle();
            /**
             * 设置样式规格
             */
            // 前景色(蓝色)
            XSSFColor myColor = new XSSFColor(Color.BLUE);
		    style.setFillForegroundColor(myColor);
            // 设置单元格填充样式
		    style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            // 设置单元格边框
            style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style.setBorderTop(XSSFCellStyle.BORDER_THIN);
            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            // 生成一个字体
            XSSFFont font = workbook.createFont();
		    font.setFontHeightInPoints((short) 12);
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            // 把字体应用到当前的样式
            style.setFont(font);
            /**
             * 设置另一种样式
             */
            XSSFCellStyle style2 = workbook.createCellStyle();
            style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
            style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            // 生成另一个字体
            XSSFFont font2 = workbook.createFont();
            font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
            // 把字体应用到当前的样式
            style2.setFont(font2);
          //产生表格标题行
            XSSFRow row = sheet.createRow(0);
            if(headers !=null  && headers.length != 0){
            	for (int i = 0; i < headers.length; i++) {
		               XSSFCell cell = row.createCell(i);
		               cell.setCellStyle(style);
		               String headValue = (String)headers[i];
		               XSSFRichTextString text = new XSSFRichTextString(headValue);
		               cell.setCellValue(text);
		            }
            }
            /**
             * 遍历集合数据
             */
            int index = 0;
            Iterator<Object> it = dataset.iterator();
            while(it.hasNext()){
            	index++;
            	row = sheet.createRow(index);
            	Object[] objs = (Object[]) it.next();
            	//当字段类型为boolean时，true的显示值
            	String trueValue = "" ;
            	//当字段类型为boolean时，false的显示值
            	String falseValue = "" ;
            	//字段的类型
            	String columnType = "" ;
            	//如果要导出字段的数组不为空，则到处对应的字段
            	if(columns !=null  && columns.length != 0){
            		for (int j = 0; j < columns.length; j++){
	            		//一个单元格
	                	XSSFCell cell = row.createCell(j);
	                	cell.setCellStyle(style2);
	                	Object colIndex = columns[j] ;
	                	int columnIndex = -1 ;
	                	  if(colIndex instanceof String[]){
	              			String[] objArray = (String[])colIndex ;
	              			if(objArray.length>0){
	              				int indexVal = Integer.valueOf(objArray[0]);
	              				if(objArray.length>1){
	              					columnType = objArray[1];
	              					if("boolean".equalsIgnoreCase(columnType)){
	              						if(objArray.length>2){
	                      					trueValue = objArray[2];
	                      				}
	                      				if(objArray.length>3){
	                      					falseValue = objArray[3];
	                      				}
	              					}
	              				}
	              				columnIndex = indexVal ;
	              			}else{
	              				continue;
	              			}
	              		}else{
	              			columnIndex = Integer.valueOf(String.valueOf(columns[j]));
	              		}
	            	  if(columnIndex <= objs.length){
	            		  Object value = (Object)objs[columnIndex];  
	                      String objValue = null ;
	                      if(value == null){
	                      	objValue = "" ;
	                      }else if(value instanceof Date){
	                      	 Date date = (Date) value;  
	                           SimpleDateFormat sdf = new SimpleDateFormat("");  
	                           objValue = sdf.format(date);  
	                      }else{
	                      	objValue = String.valueOf(value);
	                      }
	                      if(objValue!= null){  
	                          Pattern p = Pattern.compile("^//d+(//.//d+)?$");    
	                          Matcher matcher = p.matcher(objValue);  
	                          if(matcher.matches()){  
	                             //是数字当作double处理  
	                             cell.setCellValue(Double.parseDouble(objValue));  
	                          }else{  
	                          	 XSSFRichTextString xssfValue = new XSSFRichTextString(objValue);  
	                          	 if("boolean".equalsIgnoreCase(columnType)){
	                          		 if("true".equals(String.valueOf(xssfValue))){
	                          			 cell.setCellValue(trueValue);
	                          		 }else if("false".equals(String.valueOf(xssfValue))){
	                          			 cell.setCellValue(falseValue);
	                          		 }else{
	                          			 cell.setCellValue(xssfValue);
	                          		 }
	                          	 }else{
	                          		 cell.setCellValue(xssfValue);
	                          	 }
	                          }  
	                       } 
	            	  	}
	            	}
            	}else{
            	//如果要导出字段的数组为空，则默认导出所有字段
            		for(int k=0;k<objs.length;k++){
            			//一个单元格
	                	XSSFCell cell = row.createCell(k);
	                	cell.setCellStyle(style2);

	            		  Object value = (Object)objs[k];  
	                      String objValue = null ;
	                      if(value == null){
	                      	objValue = "" ;
	                      }else if(value instanceof Date){
	                      	 Date date = (Date) value;  
	                           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	                           objValue = sdf.format(date);  
	                      }else{
	                      	objValue = String.valueOf(value);
	                      }
	                      if(objValue!= null){  
	                          Pattern p = Pattern.compile("^//d+(//.//d+)?$");    
	                          Matcher matcher = p.matcher(objValue);  
	                          if(matcher.matches()){  
	                             //是数字当作double处理  
	                             cell.setCellValue(Double.parseDouble(objValue));  
	                          }else{  
	                          	 XSSFRichTextString xssfValue = new XSSFRichTextString(objValue);  
	                          	 if("boolean".equalsIgnoreCase(columnType)){
	                          		 if("true".equals(String.valueOf(xssfValue))){
	                          			 cell.setCellValue(trueValue);
	                          		 }else if("false".equals(String.valueOf(xssfValue))){
	                          			 cell.setCellValue(falseValue);
	                          		 }else{
	                          			 cell.setCellValue(xssfValue);
	                          		 }
	                          	 }else{
	                          		 cell.setCellValue(xssfValue);
	                          	 }
	                          }  
	                       } 
            		}
            	}
            }
            
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try{  
                workbook.write(os);  
            }catch (Exception e){  
                e.printStackTrace();  
            } 
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + tableName.trim() +DateFormatHelper.getNowTimeStr()+ ".xlsx");
            ServletOutputStream out = response.getOutputStream();

            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            try {

                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);

                byte[] buff = new byte[2048];
                int bytesRead;

                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }

            } catch (final IOException e) {
                throw e;
            } finally {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            }
		}catch(Exception e){
			e.printStackTrace();
		}
    	
	}
	
	/**
	 * 导出商户信息
	 * @author zdx
	 * @date 2015-11-22
	 * @param dataset
	 * @param response
	 * @param tableName
	 * @param headers
	 * @param columns
	 */
	public static void writeStoreByObject(Collection<Object> dataset,HttpServletResponse response,String tableName,Object[] headers,Object[] columns){
		
		try{
			// 声明一个工作薄2007
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 生成一个表格
            XSSFSheet sheet = workbook.createSheet(tableName);
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth((short) 15);
            // 生成一个样式
            XSSFCellStyle style = workbook.createCellStyle();
            /**
             * 设置样式规格
             */
            // 前景色(蓝色)
            XSSFColor myColor = new XSSFColor(Color.BLUE);
		    style.setFillForegroundColor(myColor);
            // 设置单元格填充样式
		    style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            // 设置单元格边框
            style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style.setBorderTop(XSSFCellStyle.BORDER_THIN);
            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            // 生成一个字体
            XSSFFont font = workbook.createFont();
		    font.setFontHeightInPoints((short) 12);
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            // 把字体应用到当前的样式
            style.setFont(font);
            /**
             * 设置另一种样式
             */
            XSSFCellStyle style2 = workbook.createCellStyle();
            style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
            style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            // 生成另一个字体
            XSSFFont font2 = workbook.createFont();
            font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
            // 把字体应用到当前的样式
            style2.setFont(font2);
          //产生表格标题行
            XSSFRow row = sheet.createRow(0);
            String[] heads = (String[]) headers;
            if(headers !=null  && headers.length != 0){
            	for (int i = 0; i < heads.length; i++) {
		               XSSFCell cell = row.createCell(i);
		               cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		               cell.setCellStyle(style);
		               XSSFRichTextString text = new XSSFRichTextString(heads[i]);
		               cell.setCellValue(text);
		            }
            }
            
            
            /**
             * 遍历集合数据
             */
            int index = 0;
            Iterator<Object> it = dataset.iterator();
            while(it.hasNext()){
            	index++;
            	row = sheet.createRow(index);
            	StoreComplexModel obj = (StoreComplexModel) it.next();
            	//当字段类型为boolean时，true的显示值
            	String trueValue = "" ;
            	//当字段类型为boolean时，false的显示值
            	String falseValue = "" ;
            	//字段的类型
            	String columnType = "" ;
            	for (int j = 0; j < columns.length; j++){
            		//一个单元格
                	XSSFCell cell = row.createCell(j);
                	cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                	cell.setCellStyle(style2);
            		Object objFieldName = columns[j];  
            		String getType = "get" ;
            		if(objFieldName instanceof String[]){
            			String[] objArray = (String[])objFieldName ;
            			if(objArray.length>1){
        					objFieldName = objArray[0];
        					columnType = objArray[1];
        					if("boolean".equalsIgnoreCase(columnType)){
        						getType = "is" ;
        					}
            				if(objArray.length>2){
            					trueValue = objArray[2];
            				}
            				if(objArray.length>3){
            					falseValue = objArray[3];
            				}
            			}else{
            				objFieldName = objArray[0];
            			}
            		}
            		String fieldName = (String)objFieldName;
                    String getMethodName = getType+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);  
                    StoreAccountModel storeAccountQueryModel = obj.getStoreAccount();
                    Object value ="" ;
                    try{
                    	//账户信息
                    	Method getMethod = storeAccountQueryModel.getClass().getDeclaredMethod(getMethodName,new Class[0]);  
                    	value = getMethod.invoke(storeAccountQueryModel, new Class[]{});
                    	//冻结状态
                    	if("frozenSate".equals(fieldName)){
                    		if(StoreAccountModel.UNFROZEN.equals(value)){
                    			value = "未冻结";
                    		}else if(StoreAccountModel.FROZEN.equals(value)){
                    			value = "已冻结";
                    		}
                    		//审核状态
                    	}else if("auditStatus".equals(fieldName)){
                    		if(StoreAccountModel.UNDONE.equals(value)){
                    			value = "未审核";
                    		}else if(StoreAccountModel.PASS.equals(value)){
                    			value = "审核通过";
                    		}else if(StoreAccountModel.NO_PASS.equals(value)){
                    			value = "审核未通过";
                    		}
                    	}
                    }catch(Exception e){
                    	try{
                    		//商户登录信息
                        	StoreEmployeeModel storeEmployeeQueryModel = obj.getStoreEmployee();
                        	Method getMethod1 = storeEmployeeQueryModel.getClass().getDeclaredMethod(getMethodName,new Class[0]);
                        	value = getMethod1.invoke(storeEmployeeQueryModel, new Class[]{});
                    	}catch(Exception e1){
                    		try{
                    			//商户企业信息
                    			StoreCompanyInfoModel storeCompanyInfoQueryModel = obj.getStoreCompanyInfo();
                    			Method getMethod2 = storeCompanyInfoQueryModel.getClass().getDeclaredMethod(getMethodName,new Class[0]);
                            	value = getMethod2.invoke(storeCompanyInfoQueryModel, new Class[]{});
                        	}catch(Exception e2){
                        		continue;
                        	}
                    	}
                    }
                   
                    String objValue = null ;
                    if(value == null){
                    	objValue = "" ;
                    }else if(value instanceof Date){
                    	 Date date = (Date) value;  
                         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                         objValue = sdf.format(date);  
                    }else{
                    	objValue = String.valueOf(value);
                    }
                    if(objValue!= null){  
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");    
                        Matcher matcher = p.matcher(objValue);  
                        if(matcher.matches()){  
                           //是数字当作double处理  
                           cell.setCellValue(Double.parseDouble(objValue));  
                        }else{  
                        	 XSSFRichTextString xssfValue = new XSSFRichTextString(objValue);  
                        	 if("boolean".equalsIgnoreCase(columnType)){
                        		 if("true".equals(String.valueOf(xssfValue))){
                        			 cell.setCellValue(trueValue);
                        		 }else if("false".equals(String.valueOf(xssfValue))){
                        			 cell.setCellValue(falseValue);
                        		 }else{
                        			 cell.setCellValue(xssfValue);
                        		 }
                        	 }else{
                        		 cell.setCellValue(xssfValue);
                        	 }
                        }  
                     }  
            	}
            }
            
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try{  
                workbook.write(os);  
            }catch (Exception e){  
                e.printStackTrace();  
            } 
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((tableName+DateFormatHelper.getTimeStr("yyyy-MM-dd", new Date())+".xlsx").getBytes(), "iso-8859-1")); 
            ServletOutputStream out = response.getOutputStream();

            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            try {

                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);

                byte[] buff = new byte[2048];
                int bytesRead;

                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }

            } catch (final IOException e) {
                throw e;
            } finally {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            }
		}catch(Exception e){
			 e.printStackTrace();  
		}
	}
	  
	/**
	 * 导出逮到期商户
	 * @author zdx
	 * @date 2015-11-22
	 * @param dataset
	 * @param response
	 * @param tableName
	 * @param headers
	 * @param columns
	 */
public static void writeExpiredStoreByObject(Collection<Object> dataset,HttpServletResponse response,String tableName,Object[] headers,Object[] columns){
		
		try{
			// 声明一个工作薄2007
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 生成一个表格
            XSSFSheet sheet = workbook.createSheet(tableName);
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth((short) 15);
            // 生成一个样式
            XSSFCellStyle style = workbook.createCellStyle();
            /**
             * 设置样式规格
             */
            // 前景色(蓝色)
            XSSFColor myColor = new XSSFColor(Color.BLUE);
		    style.setFillForegroundColor(myColor);
            // 设置单元格填充样式
		    style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            // 设置单元格边框
            style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style.setBorderTop(XSSFCellStyle.BORDER_THIN);
            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            // 生成一个字体
            XSSFFont font = workbook.createFont();
		    font.setFontHeightInPoints((short) 12);
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            // 把字体应用到当前的样式
            style.setFont(font);
            /**
             * 设置另一种样式
             */
            XSSFCellStyle style2 = workbook.createCellStyle();
            style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
            style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            // 生成另一个字体
            XSSFFont font2 = workbook.createFont();
            font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
            // 把字体应用到当前的样式
            style2.setFont(font2);
          //产生表格标题行
            XSSFRow row = sheet.createRow(0);
            String[] heads = (String[]) headers;
            if(headers !=null  && headers.length != 0){
            	for (int i = 0; i < heads.length; i++) {
		               XSSFCell cell = row.createCell(i);
		               cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		               cell.setCellStyle(style);
		               XSSFRichTextString text = new XSSFRichTextString(heads[i]);
		               cell.setCellValue(text);
		            }
            }
            
            
            /**
             * 遍历集合数据
             */
            int index = 0;
            Iterator<Object> it = dataset.iterator();
            while(it.hasNext()){
            	index++;
            	row = sheet.createRow(index);
            	StoreContractModel obj = (StoreContractModel) it.next();
            	//当字段类型为boolean时，true的显示值
            	String trueValue = "" ;
            	//当字段类型为boolean时，false的显示值
            	String falseValue = "" ;
            	//字段的类型
            	String columnType = "" ;
            	for (int j = 0; j < columns.length; j++){
            		//一个单元格
                	XSSFCell cell = row.createCell(j);
                	cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                	cell.setCellStyle(style2);
            		Object objFieldName = columns[j];  
            		String getType = "get" ;
            		if(objFieldName instanceof String[]){
            			String[] objArray = (String[])objFieldName ;
            			if(objArray.length>1){
        					objFieldName = objArray[0];
        					columnType = objArray[1];
        					if("boolean".equalsIgnoreCase(columnType)){
        						getType = "is" ;
        					}
            				if(objArray.length>2){
            					trueValue = objArray[2];
            				}
            				if(objArray.length>3){
            					falseValue = objArray[3];
            				}
            			}else{
            				objFieldName = objArray[0];
            			}
            		}
            		String fieldName = (String)objFieldName;
                    String getMethodName = getType+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);  
                    Object value ="" ;
                    try{
                    	//商户合同
                    	Method getMethod = obj.getClass().getDeclaredMethod(getMethodName,new Class[0]);  
                    	value = getMethod.invoke(obj, new Class[]{});
                    }catch(Exception e){
                    	try{
                    		//商户企业信息
                    		StoreCompanyInfoModel storeCompanyInfoModel = obj.getCompanyInfo();
                        	Method getMethod1 = storeCompanyInfoModel.getClass().getDeclaredMethod(getMethodName,new Class[0]);
                        	value = getMethod1.invoke(storeCompanyInfoModel, new Class[]{});
                    	}catch(Exception e1){
                    		continue;
                    	}
                    }
                   
                    String objValue = null ;
                    if(value == null){
                    	objValue = "" ;
                    }else if(value instanceof Date){
                    	 Date date = (Date) value;  
                         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                         objValue = sdf.format(date);  
                    }else{
                    	objValue = String.valueOf(value);
                    }
                    if(objValue!= null){  
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");    
                        Matcher matcher = p.matcher(objValue);  
                        if(matcher.matches()){  
                           //是数字当作double处理  
                           cell.setCellValue(Double.parseDouble(objValue));  
                        }else{  
                        	 XSSFRichTextString xssfValue = new XSSFRichTextString(objValue);  
                        	 if("boolean".equalsIgnoreCase(columnType)){
                        		 if("true".equals(String.valueOf(xssfValue))){
                        			 cell.setCellValue(trueValue);
                        		 }else if("false".equals(String.valueOf(xssfValue))){
                        			 cell.setCellValue(falseValue);
                        		 }else{
                        			 cell.setCellValue(xssfValue);
                        		 }
                        	 }else{
                        		 cell.setCellValue(xssfValue);
                        	 }
                        }  
                     }  
            	}
            }
            
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try{  
                workbook.write(os);  
            }catch (Exception e){  
                e.printStackTrace();  
            } 
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((tableName+DateFormatHelper.getTimeStr("yyyy-MM-dd", new Date())+".xlsx").getBytes(), "iso-8859-1")); 
            ServletOutputStream out = response.getOutputStream();

            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            try {

                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);

                byte[] buff = new byte[2048];
                int bytesRead;

                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }

            } catch (final IOException e) {
                throw e;
            } finally {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            }
		}catch(Exception e){
			 e.printStackTrace();  
		}
	}
}
