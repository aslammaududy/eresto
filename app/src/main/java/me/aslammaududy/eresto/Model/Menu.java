package me.aslammaududy.eresto.Model;

import com.google.gson.annotations.SerializedName;

public class Menu {
    @SerializedName("nama_menu")
    private String name;
    @SerializedName("harga_menu")
    private String price;
    @SerializedName("diskon")
    private String discount;
    @SerializedName("gambar_menu")
    private String image;
    private int imageResId;

    public Menu(String name, String discount, int imageResId) {
        this.name = name;
        this.price = "";
        this.image = "";
        this.discount = discount;
        this.imageResId = imageResId;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
