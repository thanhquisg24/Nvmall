package vn.vmall.AdminController;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="ad/UploadController")
public class UploadController {

	
	@RequestMapping(value = "upload_image_category",method=RequestMethod.POST)
	@ResponseBody
	public String  upload_image_category(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.print("AAA");
		String jsontext = "";		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		ServletContext servl  = request.getServletContext();
		//String savePath =servl.getRealPath(File.separator+"Upload"+File.separator+"Feature_Image");
		String folder_save = servl.getInitParameter("save_image_cate");
		String savePath =servl.getRealPath(folder_save);
		
		//String savePath = servl.getContextPath()+File.separator+"Upload"+File.separator+"Feature_Image";
		//String savePath2 = request.getServletContext().getInitParameter("savepath2");
		////System.out.println(savePath);
		String filename = "";
		if (isMultipart) {			
			FileItemFactory factory = new DiskFileItemFactory();						
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request
				List<org.apache.commons.fileupload.FileItem> multiparts = upload.parseRequest( request);
				for (org.apache.commons.fileupload.FileItem item : multiparts) {
					if (!item.isFormField()) {
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Calendar cal = Calendar.getInstance();
						////System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22
						String name =dateFormat.format(cal.getTime())+"_"+ new File(item.getName()).getName();	
						filename+=name;					
						item.write(new File(savePath , name));
						/*
						 * String name = new File(item.getName()).getName();	
						filename+=name+";";						
						item.write(new File(savePath2 + "\\" + name));
						 */ 
					}
				}				
			} 
			catch (Exception e) 
			{
			  e.printStackTrace();
			}
		}
		////System.out.println(filename);
		jsontext = filename;
		return jsontext;
	}
	

	@RequestMapping(value = "upload_image_normal_withparam",method=RequestMethod.POST)
	@ResponseBody
	public String  upload_image_normal_withparam(@RequestParam(value ="folder",required = true) String foldersett,HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.print("sddd");
		String jsontext = "";		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		ServletContext servl  = request.getServletContext();
		//String savePath =servl.getRealPath(File.separator+"Upload"+File.separator+"Feature_Image");
		String folder_save = servl.getInitParameter(foldersett);
		String savePath =servl.getRealPath(folder_save);
	
		//String savePath = servl.getContextPath()+File.separator+"Upload"+File.separator+"Feature_Image";
		//String savePath2 = request.getServletContext().getInitParameter("savepath2");
	System.out.println(savePath);
		String filename = "";
		if (isMultipart) {			
			FileItemFactory factory = new DiskFileItemFactory();						
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request
				List<org.apache.commons.fileupload.FileItem> multiparts = upload.parseRequest( request);
				for (org.apache.commons.fileupload.FileItem item : multiparts) {
					if (!item.isFormField()) {
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Calendar cal = Calendar.getInstance();
						////System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22
						String name =dateFormat.format(cal.getTime())+"_"+ new File(item.getName()).getName();	
						filename+=name;					
						item.write(new File(savePath , name));
						/*
						 * String name = new File(item.getName()).getName();	
						filename+=name+";";						
						item.write(new File(savePath2 + "\\" + name));
						 */ 
					}
				}				
			} 
			catch (Exception e) 
			{
			  e.printStackTrace();
			}
		}
		////System.out.println(filename);
		jsontext = filename;
		return jsontext;
	}
	
}
