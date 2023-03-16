package org.phyloviz.pwp.uploader.http.controllers.models;

/**
 * Types of files that can be uploaded to the server.
 */
public enum FileType {
    PROFILE,
    FASTA,
    NEWICK,
    AUXILIARY;

    /**
     * Returns the file extension of the file type.
     *
     * @return the file extension of the file type
     */
    public String getFileExtension() {
        return switch (this) {
            case PROFILE -> ".ml";
            case FASTA -> ".fasta";
            case NEWICK -> ".newick";
            case AUXILIARY -> ".isolate";
        };
    }
}
