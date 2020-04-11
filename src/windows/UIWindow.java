package windows;

import java.util.List;

import ui.UIElement;
import ui.UIHandler;
import ui.UIMaster;

public class UIWindow {
	private List<UIElement> window; 
	private String id;
	private boolean active;
	
	public UIWindow(List<UIElement> window, String id, boolean active) {
		this.window = window;
		this.id = id;
		this.active = active;
	}

	public List<UIElement> getWindow() {
		return window;
	}

	public void setWindow(List<UIElement> window) {
		this.window = window;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		if(active) {
			//UIHandler.openWindow(this);
			UIMaster.activeWindows.add(this);
		}
		if(active == false) {
			//UIHandler.closeWindow(this);
			UIMaster.activeWindows.remove(this);
		}
		this.active = active;
	}
	
	
	
}
