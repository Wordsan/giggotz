package giggotz.client;
import java.util.HashMap;
import java.util.Map;





import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;



public class PaginaPrincipalView extends Composite{

	private final TextBox textbox=new TextBox();
	final ListBox tipoBusqueda=new ListBox();
	private final VerticalPanel panel;
	public PaginaPrincipalView(Map<String,Object> params){
		final Map<String,Object> busqueda=new HashMap<String,Object>();
		panel=new VerticalPanel();
		initWidget(panel);
		//Primer panel horizontal.
		textbox.setVisibleLength(40);
		Image imagen=new Image("https://raw.githubusercontent.com/Iriabow/hello-world/master/giggotzYIcono.png");
	    panel.add(imagen);
	    HorizontalPanel hPanel=new HorizontalPanel();
	    hPanel.add(textbox);
	    
	   
	    Button botonBusqueda=new Button("Buscar");
	    botonBusqueda.addClickHandler(new ClickHandler(){
	    	public void onClick(ClickEvent click){
	    		 busqueda.put("busqueda", textbox.getText());
	    		 busqueda.put("tipoBusqueda",(new Integer(tipoBusqueda.getSelectedIndex())).toString());
	    		Giggotz.go("busqueda", busqueda);
	    	}

			
	    });
	    hPanel.add(botonBusqueda);
	    //Fin primer panel horizontal.
	    panel.add(hPanel);
	    panel.setSpacing(20);
	    //Segundo panel horizonta.
	    HorizontalPanel hPanel2=new HorizontalPanel();
	    Label aBuscar=new Label("Buscar conciertos en España por: ");
	    aBuscar.setStyleName("mainText");
	    hPanel2.add(aBuscar);
	    hPanel2.setSpacing(5);
	   
	    tipoBusqueda.addItem("Artista");
	    tipoBusqueda.addItem("Ciudad");
	   
	    hPanel2.add(tipoBusqueda);
	    //Fin segundo panel horizontal.
	   
	   // hPanel.add(new Label((new Integer(tipoBusqueda.getSelectedIndex())).toString()));
	    panel.add(hPanel2);
	   
	    //panel.add(hPanel2);
	    if(params.containsKey("failure")){
	    	Label fallo=new Label((String) params.get("failure"));
	    	fallo.setStyleName("mainText");
	    	panel.add(fallo);
	    	panel.setCellHorizontalAlignment(fallo, HasHorizontalAlignment.ALIGN_CENTER);
	    }
	    if(params.containsKey("busquedaFallida")){
	    	Label falloBusqueda=new Label((String) params.get("busquedaFallida"));
	    	falloBusqueda.setStyleName("mainText");
	    	panel.add(falloBusqueda);
	    	panel.setCellHorizontalAlignment(falloBusqueda, HasHorizontalAlignment.ALIGN_CENTER);
	    }
	    panel.setCellHorizontalAlignment(hPanel, HasHorizontalAlignment.ALIGN_CENTER);
	    panel.setCellHorizontalAlignment(hPanel2, HasHorizontalAlignment.ALIGN_CENTER);
	    panel.setStyleName("box");
	    
	    
	//    panel.setCellHorizontalAlignment(textbox, HasHorizontalAlignment.ALIGN_CENTER);
	    
	    
	   /* 
	    panel.setWidgetLeftRight(textbox, 35, Unit.EM, 35, Unit.EM);     // Center panel
	    panel.setWidgetTopBottom(textbox, 20, Unit.EM, 20, Unit.EM);
	*/	
	}
	
}
