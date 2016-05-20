package giggotz.client;

import java.util.Map;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class OnFailureView extends Composite{

	private final HorizontalPanel panel;
	public OnFailureView(Map<String,Object> params){
		panel=new HorizontalPanel();
		initWidget(panel);
		Label failure=new Label((String) params.get("failure"));
	    failure.setStyleName("failure");
	    panel.add(failure);
	}
}
