package giggotz.client;

import java.util.Map;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UnionView extends Composite{

	private static AbsolutePanel panel=new AbsolutePanel();
	private static Integer u=0;

	public UnionView(Map<String,Object> params){
	
		initWidget(panel);
		//panel.setStyleName("unionView");

		DecoratorPanel decPanel = new DecoratorPanel();
		HorizontalPanel hPanelPrincipal=new HorizontalPanel();
		//Image foto=new Image(concierto.getImages().getMedium());
		//hPanelPrincipal.add(foto);
		VerticalPanel texto=new VerticalPanel();
		texto.add(new Label("0:Aquí,en esta tabla va lo vuestro"));
		texto.add(new Label("Artista seleccionado:"+params.get("artista")));
		
		hPanelPrincipal.add(texto);
		decPanel.add(hPanelPrincipal);
		decPanel.setWidth("500px");
		
		
		panel.add(decPanel);
	}

	public static void actualizaPanel(Map<String,Object> params){
		panel.clear();
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setStyleName("unionPanel");
		HorizontalPanel hPanelPrincipal=new HorizontalPanel();
		
		//Image foto=new Image(concierto.getImages().getMedium());
		//hPanelPrincipal.add(foto);
		VerticalPanel texto=new VerticalPanel();
		u++;
		texto.add(new Label(u.toString()+":Aquí,en esta tabla va lo vuestro"));
		texto.add(new Label("Artista seleccionado:"+params.get("artista")));
		
		
		hPanelPrincipal.add(texto);
		decPanel.add(hPanelPrincipal);
		decPanel.setWidth("500px");
		
		
		panel.add(decPanel);
	}
	
}
