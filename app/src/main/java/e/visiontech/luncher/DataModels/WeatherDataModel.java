package e.visiontech.luncher.DataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherDataModel
{
    @SerializedName("city")
    @Expose
    String city;

    @SerializedName("country")
    @Expose
    String country;

    @SerializedName("temperature")
    @Expose
    String Temperature;

    @SerializedName("description")
    @Expose
    String description;


    public String getCity()
    {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
