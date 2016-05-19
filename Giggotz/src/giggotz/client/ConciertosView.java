package giggotz.client;

import giggotz.client.rpc.nvivo.GigService;
import giggotz.client.rpc.nvivo.GigServiceAsync;
import giggotz.shared.nvivo.Artist;
import giggotz.shared.nvivo.Gig;
import giggotz.shared.nvivo.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;














import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ConciertosView extends Composite{
    private final GigServiceAsync gigService=GWT.create(GigService.class);
	private final VerticalPanel panel;
	private final Label b=new Label("sfgsrgsrg");
	
	public ConciertosView(Map<String,Object> params){
		panel=new VerticalPanel();
		initWidget(panel);
		panel.setSize("500px", "1000px");
		//panel.add(b);
		//panel.add(new Label("sfadas"));
		ponConciertos(params);
		
	}
	
	private void ponConciertos(Map<String,Object> params){
		if(params.get("tipoBusqueda").equals("0")){
			gigService.getRespuestaPorArtista((String) params.get("busqueda"), new AsyncCallback<Response>(){

				
				public void onFailure(Throwable cosa) {
					 Window.alert("Error:"+cosa.getMessage());							
				}

				public void onSuccess(Response response) {
					List<Gig> conciertos=response.getGigs();
					conciertos.remove(conciertos.size()-1);
				 for(Gig g:conciertos){
					 panel.add(getPanelConcierto(g));
				 }
					
				}	
				});
		}else if(params.get("tipoBusqueda").equals("1")){
			gigService.getRespuestaPorCiudad((String) params.get("busqueda"), new AsyncCallback<Response>(){

				
				public void onFailure(Throwable cosa) {
					 Window.alert("Error:"+cosa.getMessage());							
				}

				public void onSuccess(Response response) {
					List<Gig> conciertos=response.getGigs();
					conciertos.remove(conciertos.size()-1);
				 for(Gig g:conciertos){
					 panel.add(getPanelConcierto(g));
				 }
					
				}	
				});
		}
	}
	
	private DecoratorPanel getPanelConcierto(final Gig concierto){
		DecoratorPanel decPanel = new DecoratorPanel();
		HorizontalPanel hPanelPrincipal=new HorizontalPanel();
		String imageUrl=concierto.getImages().getMedium();
		Image foto;
		if(imageUrl.contains("http") || imageUrl.contains("https")){
		  foto=new Image(imageUrl);
		}else{
		  foto=new Image("https://raw.githubusercontent.com/Iriabow/hello-world/master/simboloNoImagen.png");
		}
		hPanelPrincipal.add(foto);
		hPanelPrincipal.setSpacing(7);
		VerticalPanel texto=new VerticalPanel();
		
		texto.add(new Label("Concierto: "+concierto.getName()+"."));
		texto.add(new Label("Lugar: "+concierto.getVenue().getName()+"."));
		texto.add(new Label("Ciudad: "+concierto.getVenue().getLocation().getCity()+"."));
		texto.add(new Label("Dirección: "+concierto.getVenue().getLocation().getStreet()+"."));
		texto.add(new Label("Fecha: "+concierto.getStartDate()+"."));
		HorizontalPanel artistas=new HorizontalPanel();
		
		Double maximo=concierto.getTicketPrice().getMax();
		Double minimo=concierto.getTicketPrice().getMin();
		if(!(maximo.equals(minimo))){
		texto.add(new Label("Precio entrada: entre "+minimo+" y "
				                              +maximo+" €."));
		}else{
			texto.add(new Label("Precio entrada: "+maximo+" €."));
		}
		artistas.add(new Label("Artista:"));
		final ListBox listBoxArtistas=new ListBox();
		final List<Artist> listaArtistas=  concierto.getArtists();
		for(Artist a:listaArtistas){
			listBoxArtistas.addItem(a.getName());
		}
		artistas.setSpacing(6);
		artistas.add(listBoxArtistas);
        texto.add(artistas);
        texto.setWidth("400px");
		hPanelPrincipal.add(texto);
		 Button botonComprar=new Button("¡Comprar entrada!");
		    botonComprar.addClickHandler(new ClickHandler(){
		    	public void onClick(ClickEvent click){
		    		Window.open(concierto.getUrl(), "_blank", "");
		    	}

				
		    });
		hPanelPrincipal.add(botonComprar);    
		
		decPanel.add(hPanelPrincipal);
		decPanel.setWidth("650px");
		decPanel.sinkEvents(Event.ONCLICK);
		decPanel.addDomHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("artista",listaArtistas.get(listBoxArtistas.getSelectedIndex()).getName());
				Giggotz.go("spotifyWikipedia",params);
			}
			
		},ClickEvent.getType() );
		return decPanel;
		
	}
	
}
