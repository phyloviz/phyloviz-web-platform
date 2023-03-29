/**
 * The type of dataset.
 */
export enum DatasetType {
    MLST = "Multi-Locus Sequence Typing (MLST)",
    MLVA = "Multi-Locus Variable Number Tandem Repeat Analysis (MLVA)",
    FASTA = "Aligned Sequences (FASTA)",
    ALIGNED_SEQUENCES = "Aligned Sequences",
    SNP = "Single Nucleotide Polymorphism (SNP)",
    SNP_WITHOUT_EXPLICIT_ID = "Single Nucleotide Polymorphism (SNP) (without explicit ID)",
}