db = db.getSiblingDB('phyloviz-web-platform');

db['workflow-templates'].insertMany([
    {
        "name": "compute-distance-matrix",
        "description": "A test workflow for Phylolib",
        "tasks": [
            {
                "taskId": "download",
                "tool": "downloader",
                "action": {
                    "command": "--resource-id=${resourceId} --resource-type=typing-data --out:/phyloviz-web-platform/typingDataset.txt"
                },
                "children": [
                    "hamming"
                ]
            },
            {
                "taskId": "hamming",
                "tool": "phylolib",
                "action": {
                    "command": "distance hamming --dataset=ml:/phyloviz-web-platform/typingDataset.txt --out=symmetric:/phylovizWebPlatform/distanceMatrix.txt"
                },
                "children": [
                    "upload"
                ]
            },
            {
                "taskId": "upload",
                "tool": "uploader",
                "action": {
                    "command": " --in:/phyloviz-web-platform/distanceMatrix.txt --out:${uploadPath} --resourceId=${resourceId} --workflowId=${workflowId} --resourceType=distance-matrix"
                }
            }
        ]
    }
])

db['tool-templates'].insertMany([
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
    }
])