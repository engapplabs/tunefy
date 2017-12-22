package com.abuarquemf.tunefy.desktopapp.layoutchanger;

import com.abuarquemf.tunefy.desktopapp.models.Music;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.List;

public class TuneListCell extends ListCell<Music> {

    private List<Music> tunes;

    public TuneListCell(List<Music> tunes) {
        this.tunes = tunes;
    }

    @Override
    protected void updateItem(Music tune, boolean empty) {
        ImageView imageView = new ImageView();
        super.updateItem(tune, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            try {
                /*if(tune.getName().equals("Joy"))
                    imageView.setImage(new Image(new File(xObject.getImageResource()).toURI().toURL().toString()));
                else if(tune.getName().equals("Anger"))
                    imageView.setImage(new Image(new File(xObject.getImageResource()).toURI().toURL().toString()));
                else if(tune.getName().equals("Disgust"))
                    imageView.setImage(new Image(new File(xObject.getImageResource()).toURI().toURL().toString()));
                else if(tune.getName().equals("Fear")) */
                    imageView.setImage(new Image(tune.getImageResource()));
                setText(tune.getName());
                setGraphic(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            setGraphic(imageView);
            setText(tune.getName());
        }

    }
}
