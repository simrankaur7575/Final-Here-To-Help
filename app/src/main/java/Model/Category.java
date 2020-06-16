package Model;

public class Category {
    private String Name;
    private String Image;
    private String Description;

    public Category() {
    }

    public Category(String name, String image, String description) {
        Name = name;
        Image = image;
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description){
        Description= description;
    }
}
