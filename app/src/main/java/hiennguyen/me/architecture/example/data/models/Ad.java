package hiennguyen.me.architecture.example.data.models;

public class Ad implements Model {
    private String banner;
    private String name;

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean areItemsThemSameWith(Model model) {
        if(model instanceof Ad) {
            return this.name.equals(((Ad) model).name);
        }
        return false;
    }

    @Override
    public boolean areContentsThemSameWith(Model model) {
        if(model instanceof Ad) {
            return ((Ad) model).banner.equals(banner) && ((Ad) model).name.equals(name);
        }
        return false;
    }
}
