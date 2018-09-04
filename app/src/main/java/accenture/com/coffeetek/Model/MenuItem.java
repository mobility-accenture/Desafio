package accenture.com.coffeetek.Model;

import android.widget.ImageView;

public class MenuItem {

    private String Photo;
    private String Name;

    public MenuItem() {
    }

    public MenuItem(String photo, String name) {
        Photo = photo;
        Name = name;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
