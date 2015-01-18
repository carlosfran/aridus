package br.uern.aridus.ws;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.jaxrs.ext.MessageContext;

import br.uern.aridus.sys.AAuthentication;
import br.uern.aridus.sys.ADocBaseImpl;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/doc")
public class DocBaseManager{
	
	private ADocBaseImpl documentBase;
	public DocBaseManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		documentBase = new ADocBaseImpl();
	}

	@Path("/add")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response create(@FormDataParam("title") String title, @FormDataParam("keywords") String keywords,
			@FormDataParam("abstractText") String abstractText, @FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		
		long id = 0;
		
		String[] creds = AAuthentication.getCredentials();
		if(creds != null){
			id = AAuthentication.checkLogin(creds[0], creds[1]);
		}
		
		if(id > 0){
			String fileName = fileDetail.getFileName();
			System.out.println(fileName);
			if(fileName.endsWith(".PDF") || fileName.endsWith(".pdf")){
				id = documentBase.create(title, keywords, abstractText, id);
				if(id>0){
					String uploadedFileLocation = documentBase.dirBase + "\\"+id+".pdf";
					documentBase.writeToFile(uploadedInputStream, uploadedFileLocation);
					try {
						uploadedInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// String output = "File uploaded to : " + uploadedFileLocation;
				}
			}
		}
		return Response.status(200).entity(String.valueOf(id)).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	public Response getDocument(@PathParam("id") long id){
		
		String title = documentBase.getProp(id, "title");
		String abstractText = documentBase.getProp(id, "abstract");
		String keywords = documentBase.getProp(id, "keywords");
		
		String page = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">" +
				"<html>" +
				"<head>" +
				"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8 <!-- ISO-8859-1 -->\">" +
				"	<title>ARIDUS DocBase - Documento: "+title+"</title>" +
				"</head>" +
				"<body>" +
				"<div id=\"documentBase\">" +
				"<h1 id=\"title\">"+ title +"</h1>" +
				"<p id=\"abstract\">Resumo: "+ abstractText +"</p>" +
				"<p id=\"keywords\">Palavras-chave: "+ keywords +"</p>" +
				// "<p id=\"download\"><a href=\""+id+"/download\" title=\"Baixar documento\" accessKey=\"d\">Download</a>" +
				"</div>" +
				"</body>" +
				"</html>";
		return Response.status(200).entity(page).build();
	}
	
	@GET
	@Path("/{id}/download")
	@Produces("application/pdf")
	public Response getFileDocument(@PathParam("id") long id){
		File file = new File(documentBase.dirBase + "\\" + id + ".pdf");
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename="+id+".pdf");
		return response.status(200).build();
	}
	
	@Path("/{id}")
	@DELETE
	public Response delete(@PathParam("id") long id) {
		Boolean ret = false;
		String[] creds = AAuthentication.getCredentials();
		long userid = 0;
		if(creds != null){
			userid = AAuthentication.checkLogin(creds[0], creds[1]);
		}
		
		if(userid > 0){
			ret = documentBase.delete(id, userid);
		}
		return Response.status(200).entity(ret.toString()).build();
	}
	
	@GET
	@Path("/{id}/{prop}")
	public Response getProp(@PathParam("id") long id, @PathParam("prop") String prop) {
		String ret = documentBase.getProp(id, prop);
		return Response.status(200).entity(ret).build();
	}

	@PUT
	@Path("/{id}/{prop}/{newValue}")
	public Response updateProp(@PathParam("id") long id,
			@PathParam("prop") String prop, @PathParam("newValue") String newValue) {
		
		Boolean ret = false;
		String[] creds = AAuthentication.getCredentials();
		long userid = 0;
		if(creds != null){
			userid = AAuthentication.checkLogin(creds[0], creds[1]);
		}
		
		if(userid > 0){
			ret = documentBase.updateProp(id, userid, prop, newValue);
		}
		return Response.status(200).entity(ret.toString()).build();
	}
}
