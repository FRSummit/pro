package inventorymanagement;

import javafx.scene.image.Image;

public class SubmitedTo {
    private String name;
    private String title;
    private Image image2;

    public SubmitedTo(String name, String title) {
        this.name = name;
        this.title = title;
        String url2 = "inventorymanagement/Images/tomalSir.jpg";
        image2 = new Image(url2);
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Image getImages() {
        return image2;
    }
}
