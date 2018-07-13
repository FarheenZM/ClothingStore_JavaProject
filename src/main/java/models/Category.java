package models;

public enum Category {

    NEW_IN("New In"),
    CLOTHES("Clothes"),
    SHOES("Shoes"),
    ACCESSORIES("Accessories");

    private String description;

    Category(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

