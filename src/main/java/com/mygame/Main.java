package com.mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;
import com.simsilica.lemur.*;
import com.simsilica.lemur.style.BaseStyles;

public class Main extends SimpleApplication {

    public static void main(String... args) {

        Main main = new Main();

        AppSettings appSettings = new AppSettings(true);
        appSettings.setResolution(800, 600);

        main.setSettings(appSettings);
        main.setShowSettings(true);
        main.start();
    }

    @Override
    public void simpleInitApp() {

        GuiGlobals.initialize(this);
        BaseStyles.loadGlassStyle();
        GuiGlobals.getInstance().getStyles().setDefaultStyle(BaseStyles.GLASS);

        // Lemur Elements are drawn from the top-left corner.
        // In the GUI Node, 0,0 is bottom left.

        Button button = new Button("Click Me");
        button.setLocalTranslation(10, cam.getHeight() - 10, 0);

        button.addClickCommands(source -> {

            final Container popupContainer = new Container();
            popupContainer.addChild(new Label("You Clicked Me."));

            Button closeButton = popupContainer.addChild(new Button("Close Popup"));
            closeButton.addClickCommands(popupSource -> {
               GuiGlobals.getInstance().getPopupState().closePopup(popupContainer);
            });

            // center the popup
            popupContainer.setLocalTranslation(
                    cam.getWidth() * 0.5f - popupContainer.getPreferredSize().x * 0.5f,
                    cam.getHeight() * 0.5f + popupContainer.getPreferredSize().y * 0.5f,
                    1
            );

            GuiGlobals.getInstance().getPopupState().showModalPopup(popupContainer);

        });

        guiNode.attachChild(button);

    }

}
