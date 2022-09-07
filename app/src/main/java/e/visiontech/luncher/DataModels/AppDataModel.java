package e.visiontech.luncher.DataModels;

import android.graphics.drawable.Drawable;

public class AppDataModel
{
    String name ,packageName;
    Drawable image;

    public AppDataModel(String name, String packageName, Drawable image)
    {
        this.name = name;
        this.packageName = packageName;
        this.image = image;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
