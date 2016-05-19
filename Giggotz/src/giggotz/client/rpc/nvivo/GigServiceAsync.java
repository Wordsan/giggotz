package giggotz.client.rpc.nvivo;



import com.google.gwt.user.client.rpc.AsyncCallback;


import giggotz.shared.nvivo.Response;


/**
 * The async counterpart of <code>GigService</code>.
 */

public interface GigServiceAsync {
	
    void getRespuestaPorCiudad(String ciudad,AsyncCallback<Response> callback);
	
	void getRespuestaPorArtista(String artista,AsyncCallback<Response> callback);

}
