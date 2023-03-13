package org.isel.phylovizwebplatform.gateway.repository.project;

public class File {
    private final String id;
    private final String type;

    public File(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
