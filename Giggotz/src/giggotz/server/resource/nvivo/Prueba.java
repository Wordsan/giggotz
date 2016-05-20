package giggotz.server.resource.nvivo;

import giggotz.client.rpc.nvivo.GigService;
import giggotz.client.rpc.nvivo.GigServiceAsync;
import giggotz.shared.nvivo.Example;
import giggotz.shared.nvivo.Gig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;

public class Prueba {

	
	public static void main(String[] args) {
		
				
			
			  LectorJson prueba=new LectorJson("http://www.nvivo.es/api/request.php?api_key=62747d997af89fcaef4eb0d44e202fb4&method=city.getEvents&city=Sevilla&format=json");
		  
			  prueba.parse();
			  
			   System.out.println("Bien hecho"+prueba.getGigs().get(0).getArtists().get(0).getName());
			   System.out.println("Bien hecho 2"+prueba.getGigs().get(0).getVenue().getLocation().getCountry());
			   System.out.println("Evento"+prueba.getGigs().get(0).getName());
			   System.out.println("Ciudad"+prueba.getVenues().get(0).getLocation().getCity());
			   System.out.println("Precio"+prueba.getPrices().get(0).getMax());
			   System.out.println("Foto"+prueba.getImages().get(1).getMedium());
			   System.out.println("Response"+prueba.getResponse().getIndiceLista());
			   System.out.println("Response"+prueba.getResponse().getGigs().get(9).getName());
			   System.out.println("Status: "+prueba.getResponse().getStatus());
			   int i=0;
			   List<Gig> res=prueba.getResponse().getGigs();
			   res.remove(res.size()-1);
			   for(Gig g:res){
				   i++;
				   System.out.print(i+g.getName());
				   System.out.println("calle"+g.getVenue().getLocation().getStreet());
				   //System.out.println(g.getImages().getMedium());
			   }
			  
			   ClientResource rConcierto=null;
			   try{
					rConcierto = new ClientResource
							//(uri+"api_key="+key+"&method=artist.getEvents&artist="+artista+formato);
					
				("http://www.nvivo.es/api/request.php?api_key=62747d997af89fcaef4eb0d44e202fb4&method=artist.getEvents&artist=Iron Maiden&format=json");
					
				//	r = rConcierto.get(Example.class);
					
					//		conciertos=r.getResponse().getGigs();
				}
				catch(ResourceException re){
					System.err.println("Error al buscar el evento: " + rConcierto.getResponse().getStatus());
				}Example r=null;
			System.out.println("El json:"+rConcierto.get().toString());
	
		}

		



	

}
