package org.cargotel.reports.rest;

import java.net.URL;
import java.util.logging.Level;

import org.cargotel.reports.resources.ListReports;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.data.Reference;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

public class ReportsRestApplication extends Application {

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());

		// Guard guard = new Guard(getContext(), ChallengeScheme.HTTP_BASIC,
		// "Birt Reports");
		// guard.getSecrets().put("admin", "admin".toCharArray());
		// router.attach("/reports", guard);

		URL url = ReportsRestApplication.class.getClassLoader().getResource("yms/");

		// Create a directory able to expose a hierarchy of files
		Component component = new Component();
        component.getClients().add(Protocol.FILE);

        Directory directory;
		String baseURI = url.toExternalForm();
		directory = new Directory(component.getContext().createChildContext(), baseURI);
		directory.setListingAllowed(true);
		directory.getLogger().setLevel(Level.ALL);
		Reference rootRef = directory.getRootRef();
        directory.getLogger().info(rootRef.getPath());

		// guard.setNext(directory);
		router.attach("/reports", directory);
		router.attach("/list", ListReports.class);

		// router.attach("/path/{id}", MyServerResource.class);
		return router;
	}
}
