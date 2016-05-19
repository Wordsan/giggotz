package giggotz.client;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import giggotz.client.rpc.nvivo.GigService;
import giggotz.client.rpc.nvivo.GigServiceAsync;
import giggotz.shared.nvivo.Gig;
import giggotz.shared.nvivo.Response;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
 //comentario.

 //holaaaa!

 //prueba2

public class Giggotz implements EntryPoint {
	private static boolean unionViewCreada=false;
	private static Panel p=RootPanel.get();
	
	public void onModuleLoad() {
		 go("init",new HashMap<String,Object>());

	}
	public static void go(String token, Map<String,Object> params){
		Panel p=RootPanel.get();
		
		//Panel p2=RootLayoutPanel.get();
		if(token.equals("init")){
			p.add(new PaginaPrincipalView(params));
			//p2.add(new PaginaPrincipalView(params));
		}
		if(token.equals("busqueda")){
			p.clear();
			p.add(new ConciertosView(params));
		}
		if(token.equals("spotifyWikipedia")){
			if(!unionViewCreada){
			p.add(new UnionView(params));
			unionViewCreada=true;
			}else{
			UnionView.actualizaPanel(params);	
			}
		}
	}  
   
}
