package giggotz.client.rpc.nvivo;




import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


import giggotz.shared.nvivo.Response;

@RemoteServiceRelativePath("gig")
public interface GigService extends RemoteService{

	public Response getRespuestaPorCiudad(String ciudad);
	
	public Response getRespuestaPorArtista(String artista);
	
}
