import React, {useEffect, useState} from "react";
import {TreeViewGraph} from "../cosmos/TreeViewGraph";
import {defaultConfig, VizLink, VizNode} from "../useForceDirectedLayout";

/**
 * Configuration for simulation.
 */
export interface SimulationConfig {
    linkSpring: number;
    linkDistance: number;
    gravity: number;
    repulsion: number;
    friction: number;
    repulsionTheta: number;
    decay: number;

    setLinkSpring: (value: number) => void;
    setLinkDistance: (value: number) => void;
    setGravity: (value: number) => void;
    setFriction: (value: number) => void;
    setRepulsion: (value: number) => void;
    setRepulsionTheta: (value: number) => void;
    setDecay: (value: number) => void;

    resetAll: () => void;
}

/**
 * Hook for managing simulation configuration.
 *
 * @param graphRef Reference to the graph.
 * @param loadingGraph Whether the graph is loading.
 */
export function useSimulationConfig(
    graphRef: React.MutableRefObject<TreeViewGraph<VizNode, VizLink> | undefined>,
    loadingGraph: boolean
): SimulationConfig {
    const [linkSpring, setLinkSpring] = useState(graphRef.current?.config.simulation.linkSpring ?? defaultConfig.simulation!.linkSpring!)
    const [linkDistance, setLinkDistance] = useState(graphRef.current?.config.simulation.linkDistance ?? defaultConfig.simulation!.linkDistance!)
    const [gravity, setGravity] = useState(graphRef.current?.config.simulation.gravity ?? defaultConfig.simulation!.gravity!)
    const [repulsion, setRepulsion] = useState(graphRef.current?.config.simulation.repulsion ?? defaultConfig.simulation!.repulsion!)
    const [friction, setFriction] = useState(graphRef.current?.config.simulation.friction ?? defaultConfig.simulation!.friction!)
    const [repulsionTheta, setRepulsionTheta] = useState(graphRef.current?.config.simulation.repulsionTheta ?? defaultConfig.simulation!.repulsionTheta!)
    const [decay, setDecay] = useState(graphRef.current?.config.simulation.decay ?? defaultConfig.simulation!.decay!)

    useEffect(() => {
        if (loadingGraph)
            return

        setLinkSpring(graphRef.current?.config.simulation.linkSpring ?? defaultConfig.simulation!.linkSpring!)
        setLinkDistance(graphRef.current?.config.simulation.linkDistance ?? defaultConfig.simulation!.linkDistance!)
        setGravity(graphRef.current?.config.simulation.gravity ?? defaultConfig.simulation!.gravity!)
        setRepulsion(graphRef.current?.config.simulation.repulsion ?? defaultConfig.simulation!.repulsion!)
        setFriction(graphRef.current?.config.simulation.friction ?? defaultConfig.simulation!.friction!)
        setRepulsionTheta(graphRef.current?.config.simulation.repulsionTheta ?? defaultConfig.simulation!.repulsionTheta!)
        setDecay(graphRef.current?.config.simulation.decay ?? defaultConfig.simulation!.decay!)
    }, [loadingGraph])

    return {
        linkSpring,
        linkDistance,
        gravity,
        repulsion,
        friction,
        repulsionTheta,
        decay,

        setLinkSpring: (value: number) => {
            setLinkSpring(value)
            graphRef.current?.setConfig({simulation: {linkSpring: value}})
        },
        setLinkDistance: (value: number) => {
            setLinkDistance(value)
            graphRef.current?.setConfig({simulation: {linkDistance: value}})
        },
        setGravity: (value: number) => {
            setGravity(value)
            graphRef.current?.setConfig({simulation: {gravity: value}})
        },
        setFriction: (value: number) => {
            setFriction(value)
            graphRef.current?.setConfig({simulation: {friction: value}})
        },
        setRepulsion: (value: number) => {
            setRepulsion(value)
            graphRef.current?.setConfig({simulation: {repulsion: value}})
        },
        setRepulsionTheta: (value: number) => {
            setRepulsionTheta(value)
            graphRef.current?.setConfig({simulation: {repulsionTheta: value}})
        },
        setDecay: (value: number) => {
            setDecay(value)
            graphRef.current?.setConfig({simulation: {decay: value}})
        },

        resetAll: () => {
            setLinkSpring(defaultConfig.simulation!.linkSpring!)
            setLinkDistance(defaultConfig.simulation!.linkDistance!)
            setGravity(defaultConfig.simulation!.gravity!)
            setRepulsion(defaultConfig.simulation!.repulsion!)
            setFriction(defaultConfig.simulation!.friction!)
            setRepulsionTheta(defaultConfig.simulation!.repulsionTheta!)
            setDecay(defaultConfig.simulation!.decay!)

            graphRef.current?.setConfig({simulation: defaultConfig.simulation!})
        }
    }
}