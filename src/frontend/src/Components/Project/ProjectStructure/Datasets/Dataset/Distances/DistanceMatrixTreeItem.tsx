import {
    DistanceMatrix
} from "../../../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../../../Utils/StyledTreeItem"
import * as React from "react"
import {useDistanceMatrixTreeItem} from "./useDistanceMatrixTreeItem"
import {DeleteResourceBackdrop} from "../../../../../Shared/DeleteResourceBackdrop"
import {DistanceMatrixIcon} from "../../../../../Shared/Icons";

/**
 * Props for the DistanceMatrixTreeItem component.
 *
 * @property nodeId id of the tree item
 * @property datasetId id of the dataset
 * @property distanceMatrix distance matrix
 */
interface DistanceMatrixTreeItemProps {
    nodeId: string
    datasetId: string
    distanceMatrix: DistanceMatrix
}

/**
 * Tree item for a distance matrix.
 */
export function DistanceMatrixTreeItem({nodeId, datasetId, distanceMatrix}: DistanceMatrixTreeItemProps) {
    const {
        contextMenuItems,
        deleteBackdropOpen,
        handleDeleteBackdropClose,
        handleDelete,
        error,
        clearError
    } = useDistanceMatrixTreeItem(datasetId, distanceMatrix)

    return (<>
        <StyledTreeItem
            nodeId={nodeId}
            labelText={distanceMatrix.name}
            labelIcon={DistanceMatrixIcon}
            contextMenuItems={contextMenuItems}
        />
        <DeleteResourceBackdrop
            open={deleteBackdropOpen}
            title={"Delete Distance Matrix?"}
            subheader={"Are you sure you want to delete this distance matrix? This action cannot be undone."}
            handleClose={handleDeleteBackdropClose}
            handleDelete={handleDelete}
            error={error}
            clearError={clearError}
        />
    </>)
}

