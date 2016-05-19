package giggotz.server.resource.nvivo;

import giggotz.shared.nvivo.Artist;

import giggotz.shared.nvivo.Gig;
import giggotz.shared.nvivo.Images;
import giggotz.shared.nvivo.Location;
import giggotz.shared.nvivo.Response;
import giggotz.shared.nvivo.TicketPrice;
import giggotz.shared.nvivo.Venue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LectorJson {

	private URL url;
	//private Response response;
	private List<Gig> gigs=new ArrayList<Gig>();
	private List<List<Artist>> listasArtistas=new ArrayList<List<Artist>>();
	private List<Venue> venues=new ArrayList<Venue>();
	private List<Images> images=new ArrayList<Images>();
	private List<TicketPrice> prices=new ArrayList<TicketPrice>();
	//private Integer conciertos=0;
	private int i=0;
	private int llaveAbierta=0;
	private int llaveCerrada=0;
	private Integer indiceLista=0;
	private String json;
	private String cumuloDatos;
	
	
	
	
	
	public LectorJson(String url){
		try {
			this.url=new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			url=null;
		}
		try {
			this.json=leeJson();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	public Response getResponse(){
		Response r=new Response();
		r.setGigs(gigs);
		r.setIndiceLista(indiceLista-1);
		return r;
	}
	public List<Gig> getGigs(){
		return gigs;
	}
	public List<List<Artist>> getListaArtistas(){
		return listasArtistas;
	}
	public List<Venue> getVenues(){
		return venues;
	}
	public List<Images> getImages(){
		return images;
	}
	public List<TicketPrice> getPrices(){
		return prices;
	}
	
	private String leeJson() throws IOException{
		BufferedReader cosa=null;
		try{
			cosa=new BufferedReader(new InputStreamReader(url.openStream()));
		}
		catch(Throwable t){	
		}
		String inputText="";
		String inputLine;
	    while ((inputLine = cosa.readLine()) != null) {
	         inputText = inputText + inputLine;
	    }
	    return inputText;
		
	}
	public void parse(){
		cumuloDatos="";
		indiceLista=0;
		Boolean cerradura=true;
		for(i=0;i<json.length();i++){
			Character c=json.charAt(i);
			if(c.equals('{')){
				llaveAbierta++;
			}
			if(c.equals('}')){
				llaveCerrada++;
			}
			if(cerradura){
				Gig concierto=new Gig();
				gigs.add(concierto);
				cerradura=false;
			}
			if(llaveAbierta-2==llaveCerrada && llaveCerrada!=0 && c==('}')){
				Gig concierto=new Gig();
				gigs.add(concierto);
				indiceLista=indiceLista+1;
			}
			cumuloDatos=cumuloDatos+c;
			//System.out.println(cumuloDatos);
			if(cumuloDatos.endsWith("\"name\":")){
				String s= recorreObjeto();
			//	System.out.println(s);
		    gigs.get(indiceLista).setName(s);
		    
		    
			}
		
			if(cumuloDatos.endsWith("\"artists\":")){
				//System.out.println("artist");
				List<Artist> l=recorreArtistas();
				l.remove(l.size()-1);
				listasArtistas.add(l);
				
				
				gigs.get(indiceLista).setArtists(listasArtistas.get(indiceLista));
				
			}
			if(cumuloDatos.endsWith("\"venue\":")){
				Venue l=recorreVenue();
				venues.add(l);
				gigs.get(indiceLista).setVenue(l);
			}
			if(cumuloDatos.endsWith("\"tickets_url\":")){
				gigs.get(indiceLista).setTicketsUrl(recorreObjeto());
			}
			
			if(cumuloDatos.endsWith("\"ticket_price\":")){
				TicketPrice p=recorreTickectPrice();
				prices.add(p);
				gigs.get(indiceLista).setTicketPrice(p);
			}
			if(cumuloDatos.endsWith("startDate\":")){
				System.out.println("weee");
				gigs.get(indiceLista).setStartDate(recorreObjeto());
			}
			if(cumuloDatos.endsWith("\"modDate\":")){
				System.out.println("modDate");
				gigs.get(indiceLista).setModDate(recorreObjeto());
			}
			if(cumuloDatos.endsWith("\"images\":")){
				//System.out.println("weee");
				Images i=recorreImages();
				images.add(i);
				gigs.get(indiceLista).setImages(i);
			}
			if(cumuloDatos.endsWith("\"url\":")){
				//System.out.println("url");
				gigs.get(indiceLista).setUrl(recorreObjeto());
			}
		    
			
		}
		
		
		
	}
	private String recorreObjeto(){
		i++;
		i++;
		char a=json.charAt(i);
		String objeto="";
		int comillas=0;
		
		while(comillas<2){
			if(a==('{')){
				llaveAbierta++;
			}
			if(a==('}')){
				llaveCerrada++;
			}
		
			if(a=='"'){
				comillas++;
			}
			objeto=objeto+a;
			i++;
			a=json.charAt(i);
		}
		i--;
		objeto.trim();
		String objeto2="";
		
		for(int w=0;w<objeto.length();w++){
			char u=objeto.charAt(w);
			//Character.isAlphabetic(u) || u==(' ') || u==('/') || u==('/') || u==('/')
			if(u!=('"')){
				objeto2=objeto2+u;
			}
		}
		return objeto2;
	}
	
	private List<Artist> recorreArtistas(){
		i++;
		char a=json.charAt(i);
		List<Artist> artistas=new ArrayList<Artist>();
		int corchetes=0;
		Boolean cerradura=true;
		int llaveAbierta=0;
		int llaveCerrada=0;
		int indiceArtistas=0;
		while(corchetes<2){
			if(a==('{')){
				this.llaveAbierta++;
				llaveAbierta++;
			}
			if(a==('}')){
				this.llaveCerrada++;
				llaveCerrada++;
			}
		 
			if((a=='[') || (a==']')){
				corchetes++;
			}
			if(cerradura){
				Artist artista=new Artist();
				artistas.add(artista);
				cerradura=false;
			}
			if(llaveAbierta==llaveCerrada && llaveCerrada!=0 && a==('}')){
				Artist artista=new Artist();
				artistas.add(artista);
				indiceArtistas=indiceArtistas+1;
				//System.out.println("Creando artista");
			}
			cumuloDatos=cumuloDatos+a;
			if(cumuloDatos.endsWith("\"name\":")){
				artistas.get(indiceArtistas).setName(recorreObjeto());
			}
			if(cumuloDatos.endsWith("\"url\":")){
				artistas.get(indiceArtistas).setUrl(recorreObjeto());
			}
			if(cumuloDatos.endsWith("\"art_logo\":")){
				artistas.get(indiceArtistas).setArtLogo(recorreObjeto());
			}
			i++;
			a=json.charAt(i);
			
		}
		i--;
		
		return artistas;
	}
	
	private Venue recorreVenue(){
		i++;
		char a=json.charAt(i);
		Venue venue=new Venue();
		int llaveAbierta=0;
		int llaveCerrada=0;
		while((llaveAbierta+llaveCerrada)!=2){
			if(a==('{')){
				llaveAbierta++;
				this.llaveAbierta++;
			}
				
			
			if(a==('}')){
				llaveCerrada++;
				this.llaveCerrada++;
			}
		
			cumuloDatos=cumuloDatos+a;
			if(cumuloDatos.endsWith("\"name\":")){
				venue.setName(recorreObjeto());
			}
			if(cumuloDatos.endsWith("\"location\":")){
				venue.setLocation(recorreLocation());
			}
			if(cumuloDatos.endsWith("\"url\":")){
				venue.setUrl(recorreObjeto());
			}
			i++;
			a=json.charAt(i);
		}
		i--;
		return venue;
		
	}
	private Location recorreLocation(){
		i++;
		char a=json.charAt(i);
		Location location=new Location();
		int llaveAbierta=0;
		int llaveCerrada=0;
		while((llaveAbierta+llaveCerrada)!=2){
			if(a==('{')){
				llaveAbierta++;
				this.llaveAbierta++;
			}
				
			
			if(a==('}')){
				llaveCerrada++;
				this.llaveCerrada++;
			}
			cumuloDatos=cumuloDatos+a;
			if(cumuloDatos.endsWith("\"city\":")){
				
				location.setCity(recorreObjeto());
			}
			if(cumuloDatos.endsWith("\"country\":")){
				location.setCountry(recorreObjeto());
			}
			if(cumuloDatos.endsWith("\"street\":")){
				location.setStreet(recorreObjeto());;
			}
			if(cumuloDatos.endsWith("\"postalcode\":")){
				location.setPostalcode(recorreObjeto());;
			}
			if(cumuloDatos.endsWith("\"latitude\":")){
				location.setLatitude(recorreObjeto());;
			}
			if(cumuloDatos.endsWith("\"longitude\":")){
				location.setLongitude(recorreObjeto());;
			}
			i++;
			a=json.charAt(i);
	    }
		i--;
		return location;
	}
	
	private TicketPrice recorreTickectPrice(){
		i++;
		char a=json.charAt(i);
		TicketPrice price=new TicketPrice();
		int llaveAbierta=0;
		int llaveCerrada=0;
		while((llaveAbierta+llaveCerrada)!=2){
			if(a==('{')){
				llaveAbierta++;
				this.llaveAbierta++;
			}
				
			
			if(a==('}')){
				llaveCerrada++;
				this.llaveCerrada++;
			}
			cumuloDatos=cumuloDatos+a;
			if(cumuloDatos.endsWith("\"min\":")){
				String min=recorreNumero();
				String numero="";
				for(int i=0;i<min.length();i++){
					if(Character.isDigit((min.charAt(i))) || (min.charAt(i)=='.')){
						numero=numero+min.charAt(i);
					}
					
				}
				price.setMin(new Double(numero));
			}
			if(cumuloDatos.endsWith("\"max\":")){
				String max=recorreNumero();
				String numero="";
				for(int i=0;i<max.length();i++){
					if(Character.isDigit((max.charAt(i))) || (max.charAt(i)=='.')){
						numero=numero+max.charAt(i);
					}
					
				}
				price.setMax(new Double(numero));
			}
			i++;
			a=json.charAt(i);
	    }
		i--;
		return price;
	}
	private Images recorreImages(){
		i++;
		char a=json.charAt(i);
		Images images=new Images();
		int llaveAbierta=0;
		int llaveCerrada=0;
		while((llaveAbierta+llaveCerrada)!=2){
			if(a==('{')){
				llaveAbierta++;
				this.llaveAbierta++;
			}
				
			
			if(a==('}')){
				llaveCerrada++;
				this.llaveCerrada++;
			}
			cumuloDatos=cumuloDatos+a;
			if(cumuloDatos.endsWith("\"small\":")){
				String s=recorreObjeto();
				System.out.println("Prueba"+s);
				images.setSmall(s);
			}
			if(cumuloDatos.endsWith("\"medium\":")){
				images.setMedium(recorreObjeto());	
			}
			if(cumuloDatos.endsWith("\"large\":")){
				images.setLarge(recorreObjeto());
			}
			i++;
			a=json.charAt(i);
	    }
		i--;
		return images;
	}
	private String recorreNumero(){
		i++;
		char a=json.charAt(i);
		String objeto="";
		while(Character.isDigit(a) || a==(' ') || a==('.')){
			if(a==('{')){
				llaveAbierta++;
			}
			if(a==('}')){
				llaveCerrada++;
			}
			
			
			objeto=objeto+a;
			i++;
			a=json.charAt(i);
			
		}
	    i--;
		objeto.trim();
		return objeto;
	}
	
}
