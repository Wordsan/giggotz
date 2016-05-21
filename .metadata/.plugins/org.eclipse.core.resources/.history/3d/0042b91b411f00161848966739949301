package giggotz.server.rpc.nvivo;



import giggotz.client.rpc.nvivo.GigService;
import giggotz.server.resource.nvivo.GigResource;

import giggotz.shared.nvivo.Response;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
@SuppressWarnings("serial")
public class GigServiceImpl extends RemoteServiceServlet implements GigService{

	GigResource gResource=new GigResource();
	
	
	public Response getRespuestaPorCiudad(String ciudad) {
		
		return gResource.getRespuestaPorCiudad(ciudad);
	}
	

	@Override
	public Response getRespuestaPorArtista(String artista) {
		
		return gResource.getRespuestaPorArtista(artista);
	}

}
