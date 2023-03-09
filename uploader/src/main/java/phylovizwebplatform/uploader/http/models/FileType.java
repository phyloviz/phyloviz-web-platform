package phylovizwebplatform.uploader.http.models;

public enum FileType {
    PROFILE,
    FASTA,
    NEWICK,
    AUXILIARY;

    public String getFileExtension() {
        return switch (this) {
            case PROFILE -> ".ml";
            case FASTA -> ".fasta";
            case NEWICK -> ".newick";
            case AUXILIARY -> ".isolate";
        };
    }
}
