package DAO;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ResourceDAO {
	public static void UploadSingleFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		final String Address = context.getRealPath("/resource");
		final int MaxMemorySize = 1024 * 1024 * 3;
		final int MaxRequestSize = 1024 * 1024 * 50;
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			request.setAttribute("msg", "not have enctypr: multipart/form-data");
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MaxRequestSize);
		factory.setRepository(new java.io.File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(MaxRequestSize);
		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();

				if (!item.isFormField()) {
					String fileName = item.getName();
					String pathFile = Address + File.separator + fileName;

					File uploadFile = new File(pathFile);
					boolean kt = uploadFile.exists();
					System.out.println("pathFile : " + pathFile + " File Name : " + fileName);
					System.out.println("address : " + Address + " File.separator : " + File.separator);
					try {
						if (kt) {
							request.setAttribute("msg", "File exitst");
						} else {
							item.write(uploadFile);
							request.setAttribute("msg", "Up load success");
						}
					} catch (Exception e) {
						request.setAttribute("msg", e.getMessage());
					}
				} else {
					request.setAttribute("msg", "Up load file failed");
				}
			}
		} catch (FileUploadException e) {
			request.setAttribute("msg", e.getMessage());
		}
		response.sendRedirect("AdminHomeController");
//		RequestDispatcher rd = request.getRequestDispatcher("AdminHomeController");
//		rd.forward(request, response);
	}
}
