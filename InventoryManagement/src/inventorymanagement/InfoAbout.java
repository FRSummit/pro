package inventorymanagement;

import javafx.scene.image.Image;

public class InfoAbout {
    private String id;
    private String name;
    private int batch;
    private Image image;

    public InfoAbout(String id, String name, int batch) {
        this.id = id;
        this.name = name;
        this.batch = batch;
        String url = "inventorymanagement/Images/summit.jpg";
        image = new Image(url);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBatch() {
        return batch;
    }
    
    public Image getImage(){
        return image;
    }
}
