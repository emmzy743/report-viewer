package org.cargotel.reports.resources;

import java.io.InputStream;

import org.restlet.representation.InputRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class DefaultResource extends ServerResource {

  @Get
  public InputRepresentation execute() {
    String path = getRequest().getResourceRef().getPath();
    if(path.contains("/list-reports/")) {
      String reportName = path.substring(path.indexOf("/list-reports/") + 14);
      // TO DO
      InputStream resource = null;//ActivitiUtil.getRepositoryService().getResourceAsStream(deploymentId, resourceName);
      if (resource != null) {
        InputRepresentation output = new InputRepresentation(resource);
        return output;
      } else {
        throw new RuntimeException("There is no report with name '" + reportName+ ".");
      }
    } else {
      throw new RuntimeException("No router defined");
    }
  }

}
