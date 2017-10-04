package com.rai.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rai.jeteater.AdService;
import com.rai.jeteater.jeteater;

public class DesktopLauncher implements AdService {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		new LwjglApplication(new jeteater(), config);
	}

	@Override
	public void ShowInterstetial() {

	}

	@Override
	public void toggleBanner() {

	}
}
