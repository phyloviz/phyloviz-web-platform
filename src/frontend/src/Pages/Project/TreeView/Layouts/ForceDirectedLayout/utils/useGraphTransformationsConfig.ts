import * as React from "react";
import {useEffect, useState} from "react";
import {TreeViewGraph} from "../cosmos/TreeViewGraph";
import {defaultConfig, VizLink, VizNode} from "../useForceDirectedLayout";

/**
 * Configuration for graph transformations.
 */
export interface GraphTransformationsConfig {
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

export const DEFAULT_NODE_LABEL_CHECKED = true
export const DEFAULT_NODE_LABEL_SIZE = 0

export const DEFAULT_LINK_LABEL_CHECKED = false
export const DEFAULT_LINK_LABEL_SIZE = 0
export const DEFAULT_LINK_LABEL_TYPE = ""

/**
 * Hook for managing graph transformations configuration.
 *
 * @param graphRef Reference to the graph.
 * @param loadingGraph Whether the graph is loading.
 */
export function useGraphTransformationsConfig(
    graphRef: React.MutableRefObject<TreeViewGraph<VizNode, VizLink> | undefined>,
    loadingGraph: boolean
) {
    const [nodeSize, setNodeSize] = useState<number>(graphRef.current?.config.nodeSize ?? defaultConfig.nodeSize! as number)
    const [nodeLabel, setNodeLabel] = useState(graphRef.current?.nodeLabelsRendered() ?? DEFAULT_NODE_LABEL_CHECKED)
    const [nodeLabelSize, setNodeLabelSize] = useState(DEFAULT_NODE_LABEL_SIZE)
    const [linkWidth, setLinkWidth] = useState<number>(graphRef.current?.config.linkWidth ?? defaultConfig.linkWidth! as number)
    const [linkLabel, setLinkLabel] = useState(graphRef.current?.linkLabelsRendered() ?? DEFAULT_LINK_LABEL_CHECKED)
    const [linkLabelSize, setLinkLabelSize] = useState(DEFAULT_LINK_LABEL_SIZE)
    const [linkLabelType, setLinkLabelType] = useState(DEFAULT_LINK_LABEL_TYPE)

    useEffect(() => {
        if (loadingGraph)
            return

        setNodeSize(graphRef.current?.config.nodeSize ?? defaultConfig.nodeSize! as number)
        setNodeLabel(graphRef.current?.nodeLabelsRendered() ?? DEFAULT_NODE_LABEL_CHECKED)
        setLinkWidth(graphRef.current?.config.linkWidth ?? defaultConfig.linkWidth! as number)
        setLinkLabel(graphRef.current?.linkLabelsRendered() ?? DEFAULT_LINK_LABEL_CHECKED)
    }, [loadingGraph])

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
