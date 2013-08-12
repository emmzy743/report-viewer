package org.cargotel.reports.resources;

import java.io.File;
import java.io.FileFilter;
import java.net.URISyntaxException;
import java.net.URL;

import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class ListReports extends ServerResource {

	@Get
	public Response getAll() {
		
		Response response = super.getResponse();
		URL url = ListReports.class.getClassLoader().getResource("yms/");
		File dir;
		try {
			dir = new File(url.toURI());
			File[] reports = dir.listFiles(new FileFilter() {

				@Override
				public boolean accept(File file) {
					if (file.getName().endsWith(".rptdesign"))
						return true;
					return false;
				}
			});

            
			StringBuilder builder = new StringBuilder("<html><body><center>");
			builder.append("<table border='1'><tr><th bgcolor='#CCCCCC'>Report Name</th></tr>");
			for (File rep : reports) {
				builder.append("<tr><td>");
				builder.append("<a href='").append("../run?__report=WEB-INF/classes/yms/");
				builder.append(rep.getName()).append("'>");
				builder.append(rep.getName()).append("</a>");
				builder.append("</td><tr>");
			}
			builder.append("</table>");
			builder.append("</center></body></html>");
			response.setEntity(builder.toString(),MediaType.TEXT_HTML);
			return response;
		} catch (URISyntaxException e) {
			throw new RuntimeException();
		}
	}
}
