package hiennguyen.me.architecture.example.data.models.realm;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CategoriesRes {

    @SerializedName("results")
    public List<Category> categories;

    public static class Category {

        @SerializedName("categories")
        public String category;
    }

}
