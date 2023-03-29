import Typography from "@mui/material/Typography";
import {FormControl, InputLabel, MenuItem, Select} from "@mui/material";
import * as React from "react";

enum HierarchicalClusteringMethod {
    COMPLETE_LINKAGE = "Complete-Linkeage",
    SINGLE_LINKAGE = "Single-Linkage",
    UPGMA = "UPGMA",
    WPGMA = "WPGMA",
}

/**
 * Card for the method step in the HierarchicalClusteringConfig page.
 */
export function HierarchicalClusteringConfigMethodStep() {
    return (
        <>
            <Typography variant="caption" align={"justify"} sx={{mb: 1, width: "100%"}}>
                This list contains only the methods compatible with the current dataset:
            </Typography>
            <FormControl sx={{width: "100%", mb: 1}}>
                <InputLabel id="method">Method</InputLabel>
                <Select
                    labelId="Method"
                    //value={}
                    label="Method"
                >
                    {
                        Object.values(HierarchicalClusteringMethod).map((method) => (
                            <MenuItem key={method} value={method}>{method}</MenuItem>
                        ))
                    }
                </Select>
            </FormControl>

            <Typography display="inline" variant="caption" align={"left"} sx={{width: "100%", whiteSpace: "pre-wrap"}}>
                {methodDescriptions[HierarchicalClusteringMethod.COMPLETE_LINKAGE]}
            </Typography>
        </>
    );
}

const methodDescriptions = {
    [HierarchicalClusteringMethod.COMPLETE_LINKAGE]: "CL (Complete - Linkage) is a simple agglomerative (bottom-up) hierarchical clustering method. It is also known as farthest neighbour clustering\n" +
    "CL is used for the creation of phenetic trees (phenograms) and in a phylogenetic context, it assumes a constant rate of evolution (molecular clock hypothesis), and is not a well-regarded method for inferring relationships unless this assumption has been tested and justified for the data set being used.\n" +
    "This algorithm constructs a rooted tree (dendrogram) that reflects the structure present in a pairwise dissimilarity matrix. At each step, the nearest two clusters are combined into a higher-level cluster. The distance between any two clusters A and B is the farthest distance between elements of each cluster.\n" +
    "The method is attributed to D. Defays.",

    [HierarchicalClusteringMethod.SINGLE_LINKAGE]: "SL (Single - Linkage) is a simple agglomerative (bottom-up) hierarchical clustering method. It is also known as nearest neighbour clustering\n" +
    "SL is used for the creation of phenetic trees (phenograms) and in a phylogenetic context, it assumes a constant rate of evolution (molecular clock hypothesis), and is not a well-regarded method for inferring relationships unless this assumption has been tested and justified for the data set being used.\n" +
    "This algorithm constructs a rooted tree (dendrogram) that reflects the structure present in a pairwise dissimilarity matrix. At each step, the nearest two clusters are combined into a higher-level cluster. The distance between any two clusters A and B is the shortest distance between elements of each cluster.\n" +
    "The method is attributed to R. Sibson.",

    [HierarchicalClusteringMethod.UPGMA]: "UPGMA (Unweighted Pair Group Method with Arithmetic mean) is a simple agglomerative (bottom-up) hierarchical clustering method. It is one of the most popular methods in ecology for the classification of sampling units (such as vegetation plots) on the basis of their pairwise similarities in relevant descriptor variables (such as species composition).\n" +
    "UPGMA is used for the creation of phenetic trees (phenograms) and in a phylogenetic context, it assumes a constant rate of evolution (molecular clock hypothesis), and is not a well-regarded method for inferring relationships unless this assumption has been tested and justified for the data set being used.\n" +
    "This algorithm constructs a rooted tree (dendrogram) that reflects the structure present in a pairwise dissimilarity matrix. At each step, the nearest two clusters are combined into a higher-level cluster. The distance between any two clusters A and B is the mean distance between elements of each cluster.\n" +
    "The method is attributed to Robert R. Sokal and Charles D. Michener.",

    [HierarchicalClusteringMethod.WPGMA]: "WPGMA (Weighted Pair Group Method with Arithmetic mean)"
}