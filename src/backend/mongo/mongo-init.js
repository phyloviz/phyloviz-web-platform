// Written by MongoDB Shell (mongosh)'s standards: https://www.mongodb.com/docs/mongodb-shell/write-scripts/
// require method did not work, so cat is used to read the json files

db = db.getSiblingDB('phyloviz-web-platform')

db.createCollection('workflow-templates', {capped: false});

//WORKFLOW_TEMPLATES
db['tool-templates'].insertMany(
    [
        {
            "general": {
                "name": "uploader",
                "description": "The uploader script"
            },
            "access": {
                "_type": "library",
                "details": {
                    "address": "localhost",
                    "dockerUrl": "unix://var/run/docker.sock",
                    "dockerImage": "localhost:5000/uploader",
                    "dockerAutoRemove": "never",
                    "dockerNetworkMode": "bridge",
                    "dockerApiVersion": "auto",
                    "dockerVolumes": [
                        {
                            "source": "phyloviz-web-platform_${projectId}_${workflowId}",
                            "target": "/phyloviz-web-platform",
                            "_type": "volume"
                        }
                    ]
                }
            },
            "library": []
        },
        {
            "general": {
                "name": "downloader",
                "description": "The downloader script"
            },
            "access": {
                "_type": "library",
                "details": {
                    "address": "localhost",
                    "dockerUrl": "unix://var/run/docker.sock",
                    "dockerImage": "localhost:5000/downloader",
                    "dockerAutoRemove": "never",
                    "dockerNetworkMode": "bridge",
                    "dockerApiVersion": "auto",
                    "dockerVolumes": [
                        {
                            "source": "phyloviz-web-platform_${projectId}_${workflowId}",
                            "target": "/phyloviz-web-platform",
                            "_type": "volume"
                        }
                    ]
                }
            },
            "library": []
        },
        {
            "general": {
                "name": "phylolib",
                "description": "The phylolib library"
            },
            "access": {
                "_type": "library",
                "details": {
                    "address": "localhost",
                    "dockerUrl": "unix://var/run/docker.sock",
                    "dockerImage": "localhost:5000/phylolib",
                    "dockerAutoRemove": "never",
                    "dockerNetworkMode": "bridge",
                    "dockerApiVersion": "auto",
                    "dockerVolumes": [
                        {
                            "source": "phyloviz-web-platform_${projectId}_${workflowId}",
                            "target": "/phyloviz-web-platform",
                            "_type": "volume"
                        }
                    ]
                }
            },
            "library": []
        },
        {
            "general": {
                "name": "tree_indexer",
                "description": "The Tree Indexer script"
            },
            "access": {
                "_type": "library",
                "details": {
                    "address": "localhost",
                    "dockerUrl": "unix://var/run/docker.sock",
                    "dockerImage": "localhost:5000/tree-indexer",
                    "dockerAutoRemove": "never",
                    "dockerNetworkMode": "bridge",
                    "dockerApiVersion": "auto",
                    "dockerVolumes": [
                        {
                            "source": "phyloviz-web-platform_${projectId}_${workflowId}",
                            "target": "/phyloviz-web-platform",
                            "_type": "volume"
                        }
                    ]
                }
            },
            "library": []
        },
        {
            "general": {
                "name": "typing_data_indexer",
                "description": "The Typing Data Indexer script"
            },
            "access": {
                "_type": "library",
                "details": {
                    "address": "localhost",
                    "dockerUrl": "unix://var/run/docker.sock",
                    "dockerImage": "localhost:5000/typing-data-indexer",
                    "dockerAutoRemove": "never",
                    "dockerNetworkMode": "bridge",
                    "dockerApiVersion": "auto",
                    "dockerVolumes": [
                        {
                            "source": "phyloviz-web-platform_${projectId}_${workflowId}",
                            "target": "/phyloviz-web-platform",
                            "_type": "volume"
                        }
                    ]
                }
            },
            "library": []
        },
        {
            "general": {
                "name": "isolate_data_indexer",
                "description": "The Isolate Data Indexer script"
            },
            "access": {
                "_type": "library",
                "details": {
                    "address": "localhost",
                    "dockerUrl": "unix://var/run/docker.sock",
                    "dockerImage": "localhost:5000/isolate-data-indexer",
                    "dockerAutoRemove": "never",
                    "dockerNetworkMode": "bridge",
                    "dockerApiVersion": "auto",
                    "dockerVolumes": [
                        {
                            "source": "phyloviz-web-platform_${projectId}_${workflowId}",
                            "target": "/phyloviz-web-platform",
                            "_type": "volume"
                        }
                    ]
                }
            },
            "library": []
        },
        {
            "general": {
                "name": "compute_tree_view",
                "description": "The Compute Tree View script"
            },
            "access": {
                "_type": "library",
                "details": {
                    "address": "localhost",
                    "dockerUrl": "unix://var/run/docker.sock",
                    "dockerImage": "localhost:5000/compute-tree-view",
                    "dockerAutoRemove": "never",
                    "dockerNetworkMode": "bridge",
                    "dockerApiVersion": "auto",
                    "dockerVolumes": [
                        {
                            "source": "phyloviz-web-platform_${projectId}_${workflowId}",
                            "target": "/phyloviz-web-platform",
                            "_type": "volume"
                        }
                    ]
                }
            },
            "library": []
        },
        {
            "general": {
                "name": "rclone",
                "description": "RClone"
            },
            "access": {
                "_type": "library",
                "details": {
                    "address": "localhost",
                    "dockerUrl": "unix://var/run/docker.sock",
                    "dockerImage": "localhost:5000/rclone",
                    "dockerAutoRemove": "never",
                    "dockerNetworkMode": "bridge",
                    "dockerApiVersion": "auto",
                    "dockerVolumes": [
                        {
                            "source": "phyloviz-web-platform_${projectId}_${workflowId}",
                            "target": "/phyloviz-web-platform",
                            "_type": "volume"
                        }
                    ]
                }
            },
            "library": []
        },
        {
            "general": {
                "name": "metadata_uploader",
                "description": "Metadata uploader"
            },
            "access": {
                "_type": "library",
                "details": {
                    "address": "localhost",
                    "dockerUrl": "unix://var/run/docker.sock",
                    "dockerImage": "localhost:5000/metadata_uploader",
                    "dockerAutoRemove": "never",
                    "dockerNetworkMode": "bridge",
                    "dockerApiVersion": "auto",
                    "dockerVolumes": [
                        {
                            "source": "phyloviz-web-platform_${projectId}_${workflowId}",
                            "target": "/phyloviz-web-platform",
                            "_type": "volume"
                        }
                    ]
                }
            },
            "library": []
        }
    ]
)
//TOOL_TEMPLATES
db['workflow-templates'].insertMany(
    [
        {
            "type": "compute-distance-matrix",
            "name": "Compute Distance Matrix",
            "description": "Computes a distance matrix using the typing data file in the dataset, given the distance calculation function.",
            "arguments": {
                "datasetId": {
                    "type": "datasetId"
                },
                "function": {
                    "type": "string",
                    "allowed-values": [
                        "hamming",
                        "grapetree",
                        "kimura"
                    ]
                }
            },
            "tasks": [
                {
                    "taskId": "download",
                    "tool": "downloader",
                    "action": {
                        "command": "--project-id=${projectId} --dataset-id=${datasetId} --resource-type=typing-data --workflow-id=${workflowId} --out=/phyloviz-web-platform/typing_dataset.txt"
                    },
                    "children": [
                        "distanceCalculation"
                    ]
                },
                {
                    "taskId": "distanceCalculation",
                    "tool": "phylolib",
                    "action": {
                        "command": "distance ${function} --dataset=ml:/phyloviz-web-platform/typing_dataset.txt --out=symmetric:/phyloviz-web-platform/distance_matrix.txt"
                    },
                    "children": [
                        "upload"
                    ]
                },
                {
                    "taskId": "upload",
                    "tool": "uploader",
                    "action": {
                        "command": " --file-path=/phyloviz-web-platform/distance_matrix.txt --project-id=${projectId} --dataset-id=${datasetId} --workflow-id=${workflowId} --resource-type=distance-matrix --source-type=function --function=${function}"
                    }
                }
            ]
        },
        {
            "type": "compute-tree",
            "name": "Compute Tree",
            "description": "Computes a tree, given an existing distance matrix of the dataset and the tree calculation algorithm.",
            "arguments": {
                "datasetId": {
                    "type": "datasetId"
                },
                "distanceMatrixId": {
                    "type": "distanceMatrixId"
                },
                "algorithm": {
                    "type": "string",
                    "allowed-values": [
                        "goeburst",
                        "edmonds",
                        "sl",
                        "cl",
                        "upgma",
                        "upgmc",
                        "wpgma",
                        "wpgmc",
                        "saitounei",
                        "studierkepler",
                        "unj"
                    ]
                }
            },
            "tasks": [
                {
                    "taskId": "download",
                    "tool": "downloader",
                    "action": {
                        "command": "--project-id=${projectId} --dataset-id=${datasetId} --resource-id=${distanceMatrixId} --resource-type=distance-matrix  --workflow-id=${workflowId} --out=/phyloviz-web-platform/distance_matrix.txt"
                    },
                    "children": [
                        "treeCalculation"
                    ]
                },
                {
                    "taskId": "treeCalculation",
                    "tool": "phylolib",
                    "action": {
                        "command": "algorithm ${algorithm} --matrix=symmetric:/phyloviz-web-platform/distance_matrix.txt --out=newick:/phyloviz-web-platform/tree.txt"
                    },
                    "children": [
                        "upload"
                    ]
                },
                {
                    "taskId": "upload",
                    "tool": "uploader",
                    "action": {
                        "command": " --file-path=/phyloviz-web-platform/tree.txt --project-id=${projectId} --dataset-id=${datasetId} --workflow-id=${workflowId} --resource-type=tree --source-type=algorithm-distance-matrix --algorithm=${algorithm} --distance-matrix-id=${distanceMatrixId} --parameters={}"
                    }
                }
            ]
        },
        {
            "type": "compute-tree-without-distance-matrix",
            "name": "Compute Tree Without Distance Matrix",
            "description": "Computes a tree from the typing data file of the dataset (distance matrix internally calculated), given the distance calculation function and the tree calculation algorithm.",
            "arguments": {
                "datasetId": {
                    "type": "datasetId"
                },
                "function": {
                    "type": "string",
                    "allowed-values": [
                        "hamming",
                        "grapetree",
                        "kimura"
                    ]
                },
                "algorithm": {
                    "type": "string",
                    "allowed-values": [
                        "goeburst",
                        "edmonds",
                        "sl",
                        "cl",
                        "upgma",
                        "upgmc",
                        "wpgma",
                        "wpgmc",
                        "saitounei",
                        "studierkepler",
                        "unj"
                    ]
                }
            },
            "tasks": [
                {
                    "taskId": "download",
                    "tool": "downloader",
                    "action": {
                        "command": "--project-id=${projectId} --dataset-id=${datasetId} --resource-type=typing-data --workflow-id=${workflowId} --out=/phyloviz-web-platform/typing_dataset.txt"
                    },
                    "children": [
                        "treeCalculation"
                    ]
                },
                {
                    "taskId": "treeCalculation",
                    "tool": "phylolib",
                    "action": {
                        "command": "algorithm ${algorithm} --out=newick:/phyloviz-web-platform/tree.txt : distance ${function} --dataset=ml:/phyloviz-web-platform/typing_dataset.txt"
                    },
                    "children": [
                        "upload"
                    ]
                },
                {
                    "taskId": "upload",
                    "tool": "uploader",
                    "action": {
                        "command": " --file-path=/phyloviz-web-platform/tree.txt --project-id=${projectId} --dataset-id=${datasetId} --workflow-id=${workflowId} --resource-type=tree --source-type=algorithm-typing-data --algorithm=${algorithm} --parameters={}"
                    }
                }
            ]
        },
        {
            "type": "compute-distance-matrix-and-tree",
            "name": "Compute Distance Matrix and Tree",
            "description": "Computes a distance matrix and a tree from the typing data file of the dataset, given the distance calculation function and the tree calculation algorithm.",
            "arguments": {
                "datasetId": {
                    "type": "datasetId"
                },
                "function": {
                    "type": "string",
                    "allowed-values": [
                        "hamming",
                        "grapetree",
                        "kimura"
                    ]
                },
                "algorithm": {
                    "type": "string",
                    "allowed-values": [
                        "goeburst",
                        "edmonds",
                        "sl",
                        "cl",
                        "upgma",
                        "upgmc",
                        "wpgma",
                        "wpgmc",
                        "saitounei",
                        "studierkepler",
                        "unj"
                    ]
                }
            },
            "tasks": [
                {
                    "taskId": "download",
                    "tool": "downloader",
                    "action": {
                        "command": "--project-id=${projectId} --dataset-id=${datasetId} --resource-type=typing-data --workflow-id=${workflowId} --out=/phyloviz-web-platform/typing_dataset.txt"
                    },
                    "children": [
                        "distanceCalculation"
                    ]
                },
                {
                    "taskId": "distanceCalculation",
                    "tool": "phylolib",
                    "action": {
                        "command": "distance ${function} --dataset=ml:/phyloviz-web-platform/typing_dataset.txt --out=symmetric:/phyloviz-web-platform/distance_matrix.txt"
                    },
                    "children": [
                        "upload_distance_matrix"
                    ]
                },
                {
                    "taskId": "upload_distance_matrix",
                    "tool": "uploader",
                    "action": {
                        "command": " --file-path=/phyloviz-web-platform/distance_matrix.txt --project-id=${projectId} --dataset-id=${datasetId} --workflow-id=${workflowId} --resource-type=distance-matrix --source-type=function --function=${function}"
                    },
                    "children": [
                        "treeCalculation"
                    ]
                },
                {
                    "taskId": "treeCalculation",
                    "tool": "phylolib",
                    "action": {
                        "command": "algorithm ${algorithm} --matrix=symmetric:/phyloviz-web-platform/distance_matrix.txt --out=newick:/phyloviz-web-platform/tree.txt"
                    },
                    "children": [
                        "upload_tree"
                    ]
                },
                {
                    "taskId": "upload_tree",
                    "tool": "uploader",
                    "action": {
                        "command": " --file-path=/phyloviz-web-platform/tree.txt --project-id=${projectId} --dataset-id=${datasetId} --workflow-id=${workflowId} --resource-type=tree --source-type=algorithm-distance-matrix --algorithm=${algorithm} --parameters={}"
                    }
                }
            ]
        },
        {
            "type": "compute-tree-and-index",
            "name": "Compute Tree And Index",
            "description": "Computes a tree and indexes it in the database, given an existing distance matrix of the dataset and the tree calculation algorithm.",
            "arguments": {
                "datasetId": {
                    "type": "datasetId"
                },
                "distanceMatrixId": {
                    "type": "distanceMatrixId"
                },
                "algorithm": {
                    "type": "string",
                    "allowed-values": [
                        "goeburst",
                        "edmonds",
                        "sl",
                        "cl",
                        "upgma",
                        "upgmc",
                        "wpgma",
                        "wpgmc",
                        "saitounei",
                        "studierkepler",
                        "unj"
                    ]
                }
            },
            "tasks": [
                {
                    "taskId": "download",
                    "tool": "downloader",
                    "action": {
                        "command": "--project-id=${projectId} --dataset-id=${datasetId} --resource-id=${distanceMatrixId} --resource-type=distance-matrix  --workflow-id=${workflowId} --out=/phyloviz-web-platform/distance_matrix.txt"
                    },
                    "children": [
                        "treeCalculation"
                    ]
                },
                {
                    "taskId": "treeCalculation",
                    "tool": "phylolib",
                    "action": {
                        "command": "algorithm ${algorithm} --matrix=symmetric:/phyloviz-web-platform/distance_matrix.txt --out=newick:/phyloviz-web-platform/tree.txt"
                    },
                    "children": [
                        "treeIndexing"
                    ]
                },
                {
                    "taskId": "treeIndexing",
                    "tool": "tree_indexer",
                    "action": {
                        "command": "--tree-file-path=/phyloviz-web-platform/tree.txt --project-id=${projectId} --dataset-id=${datasetId} --workflow-id=${workflowId} --source-type=algorithm-distance-matrix --algorithm=${algorithm} --distance-matrix-id=${distanceMatrixId} --parameters={}"
                    },
                    "children": [
                        "upload"
                    ]
                },
                {
                    "taskId": "upload",
                    "tool": "uploader",
                    "action": {
                        "command": " --file-path=/phyloviz-web-platform/tree.txt --project-id=${projectId} --dataset-id=${datasetId} --workflow-id=${workflowId} --resource-type=tree"
                    }
                }
            ]
        },
        {
            "type": "index-typing-data",
            "name": "Index Typing Data",
            "description": "Indexes the typing data of a dataset in the database.",
            "arguments": {
                "datasetId": {
                    "type": "datasetId"
                }
            },
            "tasks": [
                {
                    "taskId": "download",
                    "tool": "downloader",
                    "action": {
                        "command": "--project-id=${projectId} --dataset-id=${datasetId} --resource-type=typing-data  --workflow-id=${workflowId} --out=/phyloviz-web-platform/typing_dataset.txt"
                    },
                    "children": [
                        "typingDataIndexing"
                    ]
                },
                {
                    "taskId": "typingDataIndexing",
                    "tool": "typing_data_indexer",
                    "action": {
                        "command": "--typing-data-file-path=/phyloviz-web-platform/typing_dataset.txt --project-id=${projectId} --dataset-id=${datasetId} --workflow-id=${workflowId}"
                    }
                }
            ]
        },
        {
            "type": "index-isolate-data",
            "name": "Index Isolate Data",
            "description": "Indexes the isolate data of a dataset in the database.",
            "arguments": {
                "datasetId": {
                    "type": "datasetId"
                }
            },
            "tasks": [
                {
                    "taskId": "download",
                    "tool": "downloader",
                    "action": {
                        "command": "--project-id=${projectId} --dataset-id=${datasetId} --resource-type=isolate-data --workflow-id=${workflowId} --out=/phyloviz-web-platform/isolate_data.txt"
                    },
                    "children": [
                        "isolateDataIndexing"
                    ]
                },
                {
                    "taskId": "isolateDataIndexing",
                    "tool": "isolate_data_indexer",
                    "action": {
                        "command": "--isolate-data-file-path=/phyloviz-web-platform/isolate_data.txt --project-id=${projectId} --dataset-id=${datasetId} --workflow-id=${workflowId}"
                    }
                }
            ]
        },
        {
            "type": "index-typing-and-isolate-data",
            "name": "Index Typing and Isolate Data",
            "description": "Indexes the typing and isolate data of a dataset in the database.",
            "arguments": {
                "datasetId": {
                    "type": "datasetId"
                }
            },
            "tasks": [
                {
                    "taskId": "downloadTyping",
                    "tool": "downloader",
                    "action": {
                        "command": "--project-id=${projectId} --dataset-id=${datasetId} --resource-type=typing-data  --workflow-id=${workflowId} --out=/phyloviz-web-platform/typing_dataset.txt"
                    },
                    "children": [
                        "typingDataIndexing"
                    ]
                },
                {
                    "taskId": "typingDataIndexing",
                    "tool": "typing_data_indexer",
                    "action": {
                        "command": "--typing-data-file-path=/phyloviz-web-platform/typing_dataset.txt --project-id=${projectId} --dataset-id=${datasetId} --workflow-id=${workflowId}"
                    },
                    "children": [
                        "downloadIsolate"
                    ]
                },
                {
                    "taskId": "downloadIsolate",
                    "tool": "downloader",
                    "action": {
                        "command": "--project-id=${projectId} --dataset-id=${datasetId} --resource-type=isolate-data --workflow-id=${workflowId} --out=/phyloviz-web-platform/isolate_data.txt"
                    },
                    "children": [
                        "isolateDataIndexing"
                    ]
                },
                {
                    "taskId": "isolateDataIndexing",
                    "tool": "isolate_data_indexer",
                    "action": {
                        "command": "--isolate-data-file-path=/phyloviz-web-platform/isolate_data.txt --project-id=${projectId} --dataset-id=${datasetId} --workflow-id=${workflowId}"
                    }
                }
            ]
        },
        {
            "type": "compute-tree-view",
            "name": "Compute Tree View",
            "description": "Computes the tree view of a tree, given a tree visualization layout.",
            "arguments": {
                "datasetId": {
                    "type": "datasetId"
                },
                "treeId": {
                    "type": "treeId"
                },
                "layout": {
                    "type": "string",
                    "allowed-values": [
                        "force-directed",
                        "radial"
                    ]
                }
            },
            "tasks": [
                {
                    "taskId": "treeViewCompute",
                    "tool": "compute_tree_view",
                    "action": {
                        "command": "--project-id=${projectId} --dataset-id=${datasetId} --tree-id=${treeId} --workflow-id=${workflowId} --layout=${layout}"
                    }
                }
            ]
        },
        {
            "type": "file-upload-by-url",
            "name": "File Upload by URL",
            "description": "Uploads a file to the project using an URL source.",
            "arguments": {
                "url": {
                    "type": "regex",
                    "pattern": "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$"
                },
                "file_type": {
                    "type": "string",
                    "allowed-values": [
                        "typing_data",
                        "isolate_data"
                    ]
                }
            },
            "tasks": [
                {
                    "taskId": "upload",
                    "tool": "rclone",
                    "action": {
                        "command": "copyurl ${url} phyloviz:phyloviz-web-platform/projects/${projectId}/${workflowId}"
                    },
                    "children": [
                        "createMetadata"
                    ]
                },
                {
                    "taskId": "createMetadata",
                    "tool": "metadata_uploader",
                    "action": {
                        "command": "--original_url=${url} --url=http://localhost:9444/phyloviz-web-platform/projects/${projectId}/${workflowId} --type=${file_type} --project-id=${projectId} --workflow-id=${workflowId}"
                    }
                }
            ]
        }
    ]
)