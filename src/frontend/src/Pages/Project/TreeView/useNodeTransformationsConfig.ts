import * as React from "react";
import {useState} from "react";
import {defaultConfig, VizLink, VizNode} from "./useTreeView";
import {TreeViewGraph} from "./cosmos/TreeViewGraph";

export interface NodeTransformationsConfig {
    nodeSize: number;
    nodeLabel: boolean;
    nodeLabelSize: number;
    linkWidth: number;
    linkLabel: boolean;
    linkLabelSize: number;
    linkLabelType: string;

    setNodeSize: (value: number) => void;
    setNodeLabel: (value: boolean) => void;
    setNodeLabelSize: (value: number) => void;
    setLinkWidth: (value: number) => void;
    setLinkLabel: (value: boolean) => void;
    setLinkLabelSize: (value: number) => void;
    setLinkLabelType: (value: string) => void;

    resetAll: () => void;
}

const DEFAULT_NODE_LABEL_CHECKED = true
const DEFAULT_NODE_LABEL_SIZE = 0

const DEFAULT_LINK_LABEL_CHECKED = false
const DEFAULT_LINK_LABEL_SIZE = 0
const DEFAULT_LINK_LABEL_TYPE = ""

export function useNodeTransformationsConfig(graphRef: React.MutableRefObject<TreeViewGraph<VizNode, VizLink> | undefined>) {
    const [nodeSize, setNodeSize] = useState<number>(defaultConfig.nodeSize! as number)
    const [nodeLabel, setNodeLabel] = useState(DEFAULT_NODE_LABEL_CHECKED)
    const [nodeLabelSize, setNodeLabelSize] = useState(DEFAULT_NODE_LABEL_SIZE)
    const [linkWidth, setLinkWidth] = useState<number>(defaultConfig.linkWidth! as number)
    const [linkLabel, setLinkLabel] = useState(DEFAULT_LINK_LABEL_CHECKED)
    const [linkLabelSize, setLinkLabelSize] = useState(DEFAULT_LINK_LABEL_SIZE)
    const [linkLabelType, setLinkLabelType] = useState(DEFAULT_LINK_LABEL_TYPE)

    return {
        nodeSize,
        nodeLabel,
        nodeLabelSize,
        linkWidth,
        linkLabel,
        linkLabelSize,
        linkLabelType,

        setNodeSize: (value: number) => {
            setNodeSize(value)
            graphRef.current?.setConfig({nodeSize: value})
        },
        setNodeLabel: (value: boolean) => {
            setNodeLabel(value)
            graphRef.current?.renderNodeLabels(value)
        },
        setNodeLabelSize: (value: number) => {
            // TODO: implement node label size
        },
        setLinkWidth: (value: number) => {
            setLinkWidth(value)
            graphRef.current?.setConfig({linkWidth: value})
        },
        setLinkLabel: (value: boolean) => {
            setLinkLabel(value)
            graphRef.current?.renderLinkLabels(value)
        },
        setLinkLabelSize: (value: number) => {
            // TODO: implement link label size
        },
        setLinkLabelType: async (value: string) => {
            // TODO: implement link label type

        },

        resetAll: () => {
            setNodeSize(defaultConfig.nodeSize as number)
            setNodeLabel(DEFAULT_NODE_LABEL_CHECKED)
            setNodeLabelSize(DEFAULT_NODE_LABEL_SIZE)
            setLinkWidth(defaultConfig.linkWidth as number)
            setLinkLabel(DEFAULT_LINK_LABEL_CHECKED)
            setLinkLabelSize(DEFAULT_LINK_LABEL_SIZE)
            setLinkLabelType(DEFAULT_LINK_LABEL_TYPE)

            graphRef.current?.setConfig({
                nodeSize: defaultConfig.nodeSize as number,
                linkWidth: defaultConfig.linkWidth as number
            })

            graphRef.current?.renderNodeLabels(DEFAULT_NODE_LABEL_CHECKED)
            graphRef.current?.renderLinkLabels(DEFAULT_LINK_LABEL_CHECKED)
        }
    }
}