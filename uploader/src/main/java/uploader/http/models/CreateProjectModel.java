package uploader.http.models;

public class CreateProjectModel {
    private final String name;
    private final String description;

    public CreateProjectModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
