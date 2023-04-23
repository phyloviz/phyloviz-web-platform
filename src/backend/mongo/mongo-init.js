db = db.getSiblingDB('phyloviz-web-platform')

db['workflow-templates'].insertMany(
    [
        {
            "name": "compute-distance-matrix",
            "description": "Compute Distance Matrix Workflow",
            "arguments": {
                "datasetId": {
                    "type": "objectid",
                    "special": {
                        "dataset": {
                            "obtain-extra": [
                                "typingDataId"
                            ]
                        }
                    }
                },
                "function": {
                    "type": "string"
                },
                "extra": {
                    "projectId": {
                        "special": "project"
                    }
                }
            },
            "tasks": [
                {
                    "taskId": "download",
                    "tool": "downloader",
                    "action": {
                        "command": "--resource-id=${typingDataId} --resource-type=typing-data --out=/phyloviz-web-platform/typing_dataset.txt"
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
                        "command": " --file-path=/phyloviz-web-platform/distance_matrix.txt --project-id=${projectId} --dataset-id=${datasetId} --resource-id=${typingDataId} --workflow-id=${workflowId} --resource-type=distance-matrix"
                    }
                }
            ]
        },
        {
            "name": "compute-tree",
            "description": "Compute Tree Workflow",
            "arguments": {
                "datasetId": {
                    "type": "objectid",
                    "special": "dataset"
                },
                "distanceMatrixId": {
                    "type": "uuid",
                    "special": "distanceMatrix"
                },
                "algorithm": {
                    "type": "string"
                },
                "extra": {
                    "projectId": {
                        "special": "project"
                    }
                }
            },
            "tasks": [
                {
                    "taskId": "download",
                    "tool": "downloader",
                    "action": {
                        "command": "--resource-id=${distanceMatrixId} --resource-type=distance-matrix --out=/phyloviz-web-platform/distance_matrix.txt"
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
                        "command": " --file-path=/phyloviz-web-platform/tree.txt --project-id=${projectId} --dataset-id=${datasetId} --workflow-id=${workflowId} --resource-type=tree"
                    }
                }
            ]
        },
        {
            "name": "index-typing-data",
            "description": "Index Typing Data Workflow",
            "arguments": {
                "datasetId": {
                    "type": "objectid",
                    "special": {
                        "dataset": {
                            "obtain-extra": [
                                "typingDataId"
                            ]
                        }
                    }
                },
                "extra": {
                    "projectId": {
                        "special": "project"
                    }
                }
            },
            "tasks": [
                {
                    "taskId": "download",
                    "tool": "downloader",
                    "action": {
                        "command": "--resource-id=${typingDataId} --resource-type=typing-data --out=/phyloviz-web-platform/typing_dataset.txt"
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
            "name": "index-tree",
            "description": "Index Tree Workflow",
            "arguments": {
                "datasetId": {
                    "type": "objectid",
                    "special": "dataset"
                },
                "treeId": {
                    "type": "uuid",
                    "special": "tree"
                },
                "extra": {
                    "projectId": {
                        "special": "project"
                    }
                }
            },
            "tasks": [
                {
                    "taskId": "download",
                    "tool": "downloader",
                    "action": {
                        "command": "--resource-id=${treeId} --resource-type=tree --out=/phyloviz-web-platform/tree.txt"
                    },
                    "children": [
                        "treeIndexing"
                    ]
                },
                {
                    "taskId": "treeIndexing",
                    "tool": "tree_indexer",
                    "action": {
                        "command": "--tree-file-path=/phyloviz-web-platform/tree.txt --project-id=${projectId} --dataset-id=${datasetId} --tree-id=${treeId} --workflow-id=${workflowId}"
                    }
                }
            ]
        },
        {
            "name": "compute-tree-view",
            "description": "Compute Tree View Workflow",
            "arguments": {
                "datasetId": {
                    "type": "objectid",
                    "special": "dataset"
                },
                "treeId": {
                    "type": "uuid",
                    "special": "tree"
                },
                "extra": {
                    "projectId": {
                        "special": "project"
                    }
                }
            },
            "tasks": [
                {
                    "taskId": "treeViewCompute",
                    "tool": "compute_tree_view",
                    "action": {
                        "command": "--project-id=${projectId} --dataset-id=${datasetId} --tree-id=${treeId} --workflow-id=${workflowId}"
                    }
                }
            ]
        }
    ]
)

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
                            "source": "/mnt/phyloviz-web-platform/${projectId}/${workflowId}/",
                            "target": "/phyloviz-web-platform",
                            "_type": "bind"
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
                            "source": "/mnt/phyloviz-web-platform/${projectId}/${workflowId}/",
                            "target": "/phyloviz-web-platform",
                            "_type": "bind"
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
                    "dockerImage": "luanab/phylolib",
                    "dockerAutoRemove": "never",
                    "dockerNetworkMode": "bridge",
                    "dockerApiVersion": "auto",
                    "dockerVolumes": [
                        {
                            "source": "/mnt/phyloviz-web-platform/${projectId}/${workflowId}/",
                            "target": "/phyloviz-web-platform",
                            "_type": "bind"
                        }
                    ]
                }
            },
            "library": [
                {
                    "name": "Arguments",
                    "order": 0,
                    "invocation": "-args",
                    "allowCommandRep": false,
                    "commands": [
                        {
                            "name": "help",
                            "invocation": [
                                "help"
                            ]
                        },
                        {
                            "name": "distance",
                            "invocation": [
                                "distance"
                            ],
                            "allowedValues": [
                                "hamming",
                                "grapetree",
                                "kimura"
                            ],
                            "allowedCommandSets": [
                                "Options"
                            ]
                        },
                        {
                            "name": "correction",
                            "invocation": [
                                "correction"
                            ],
                            "allowedValues": [
                                "jukescantor"
                            ],
                            "allowedCommandSets": [
                                "Options"
                            ]
                        },
                        {
                            "name": "algorithm",
                            "invocation": [
                                "algorithm"
                            ],
                            "allowedValues": [
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
                            ],
                            "allowedCommandSets": [
                                "Options"
                            ]
                        },
                        {
                            "name": "optimization",
                            "invocation": [
                                "optimization"
                            ],
                            "allowedValues": [
                                "lbr"
                            ],
                            "allowedCommandSets": [
                                "Options"
                            ]
                        }
                    ]
                },
                {
                    "name": "Options",
                    "order": 1,
                    "allowCommandRep": true,
                    "commands": [
                        {
                            "name": "File Output",
                            "description": "Output file as <format>:<location> with format being (asymmetric|symmetric|newick|nexus)",
                            "invocation": [
                                "-o",
                                "--out"
                            ],
                            "allowedValues": [
                                "file"
                            ]
                        },
                        {
                            "name": "Dataset Input",
                            "description": "Input dataset file as <format>:<location> with format being (fasta|ml|snp)",
                            "invocation": [
                                "-d",
                                "--dataset"
                            ],
                            "allowedValues": [
                                "file"
                            ]
                        },
                        {
                            "name": "Distance Matrix Input",
                            "description": "Input distance matrix file as <format>:<location> with format being (asymmetric|symmetric)",
                            "invocation": [
                                "-m",
                                "--matrix"
                            ],
                            "allowedValues": [
                                "file"
                            ]
                        },
                        {
                            "name": "Phylogenetic Tree Input",
                            "description": "Input phylogenetic tree file as <format>:<location> with format being (newick|nexus)",
                            "invocation": [
                                "-m",
                                "--matrix"
                            ],
                            "allowedValues": [
                                "file"
                            ]
                        },
                        {
                            "name": "Limit of focus variants",
                            "description": "Limit of locus variants to consider using goeBURST algorithm [default: 3]",
                            "invocation": [
                                "-l",
                                "--lvs"
                            ],
                            "allowedValues": [
                                "file"
                            ]
                        }
                    ]
                }
            ]
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
                            "source": "/mnt/phyloviz-web-platform/${projectId}/${workflowId}/",
                            "target": "/phyloviz-web-platform",
                            "_type": "bind"
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
                            "source": "/mnt/phyloviz-web-platform/${projectId}/${workflowId}/",
                            "target": "/phyloviz-web-platform",
                            "_type": "bind"
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
                            "source": "/mnt/phyloviz-web-platform/${projectId}/${workflowId}/",
                            "target": "/phyloviz-web-platform",
                            "_type": "bind"
                        }
                    ]
                }
            },
            "library": []
        }
    ]
)