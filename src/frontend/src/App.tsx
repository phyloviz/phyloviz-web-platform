import * as React from 'react'
import './App.css'
import {Route, Routes} from 'react-router-dom'
import About from "./Pages/About/About"
import {WebUiUris} from "./Pages/WebUiUris"
import Dashboard from "./Layouts/Dashboard/Dashboard"
import {Home} from "./Pages/Home/Home"
import News from "./Pages/News/News"
import APIInfo from "./Pages/APIInfo/APIInfo"
import CreateDataset from "./Pages/Project/CreateDataset/CreateDataset"
import NewProject from "./Pages/NewProject/NewProject"
import OpenProject from "./Pages/OpenProject/OpenProject"
import Project from "./Pages/Project/Project"
import {NotFoundPage} from "./Pages/NotFoundPage"
import UploadFiles from "./Pages/Project/UploadFiles/UploadFiles"
import {useLoggedIn} from "./Session/Session"
import GoeBURSTConfig from "./Pages/Project/Compute/Tree/GoeBURSTConfig/GoeBURSTConfig"
import GoeBURSTFullMSTConfig from "./Pages/Project/Compute/Tree/GoeBURSTFullMSTConfig/GoeBURSTFullMSTConfig"
import HierarchicalClusteringConfig
    from "./Pages/Project/Compute/Tree/HierarchicalClusteringConfig/HierarchicalClusteringConfig"
import NeighborJoiningConfig from "./Pages/Project/Compute/Tree/NeighborJoiningConfig/NeighborJoiningConfig"
import NLVGraphConfig from "./Pages/Project/Compute/Tree/NLVGraphConfig/NLVGraphConfig"
import Profile from "./Pages/Profile/Profile"
import TreeView from "./Pages/Project/TreeView/TreeView"
import DistanceMatrix from "./Pages/Project/DistanceMatrix/DistanceMatrix"
import IsolateData from "./Pages/Project/IsolateData/IsolateData"
import TypingData from "./Pages/Project/TypingData/TypingData"
import Tree from "./Pages/Project/Tree/Tree"
import DatasetDetails from "./Pages/Project/DatasetDetails/DatasetDetails"
import Report from "./Pages/Project/Report/Report"
import ComputeHammingDistance
    from "./Pages/Project/Compute/DistanceMatrix/ComputeHammingDistance/ComputeHammingDistance"
import EditProject from "./Pages/Project/EditProject/EditProject";
import EditDataset from "./Pages/Project/EditDataset/EditDataset";
import HOME = WebUiUris.HOME;
import ABOUT = WebUiUris.ABOUT;
import NEWS = WebUiUris.NEWS;
import API_INFO = WebUiUris.API_INFO;
import CREATE_DATASET = WebUiUris.CREATE_DATASET;
import OPEN_PROJECT = WebUiUris.OPEN_PROJECT;
import PROJECT = WebUiUris.PROJECT;
import TYPING_DATA = WebUiUris.TYPING_DATA;
import ISOLATE_DATA = WebUiUris.ISOLATE_DATA;
import DISTANCE_MATRIX = WebUiUris.DISTANCE_MATRIX;
import TREE_VIEW = WebUiUris.TREE_VIEW;
import UPLOAD_FILES = WebUiUris.UPLOAD_FILES;
import PROFILE = WebUiUris.PROFILE;
import NEW_PROJECT = WebUiUris.NEW_PROJECT;
import COMPUTE_GOEBURST = WebUiUris.COMPUTE_GOEBURST;
import COMPUTE_GOEBURST_FULL_MST = WebUiUris.COMPUTE_GOEBURST_FULL_MST;
import COMPUTE_HIERARCHICAL_CLUSTERING = WebUiUris.COMPUTE_HIERARCHICAL_CLUSTERING;
import COMPUTE_NEIGHBOR_JOINING = WebUiUris.COMPUTE_NEIGHBOR_JOINING;
import COMPUTE_NLV_GRAPH = WebUiUris.COMPUTE_NLV_GRAPH;
import TREE = WebUiUris.TREE;
import DATASET = WebUiUris.DATASET;
import REPORT = WebUiUris.REPORT;
import COMPUTE_HAMMING_DISTANCE = WebUiUris.COMPUTE_HAMMING_DISTANCE;
import EDIT_PROJECT = WebUiUris.EDIT_PROJECT;
import EDIT_DATASET = WebUiUris.EDIT_DATASET;

/**
 * App component.
 */
export default function App() {

    const loggedIn = useLoggedIn()

    /**
     * Protection route component, redirects to login page if not logged in.
     *
     * @param children the children to render
     */
    function ProtectedRoute({children}: { children: React.ReactElement }) {
        if (!loggedIn) {
            window.location.href = WebUiUris.LOGIN
            return null
        }

        return children
    }

    return (
        <div className="App">
            <div className="App-content">
                <Dashboard>
                    <Routes>
                        <Route path={HOME} element={<Home/>}/>
                        <Route path={ABOUT} element={<About/>}/>
                        <Route path={NEWS} element={<News/>}/>
                        <Route path={API_INFO} element={<APIInfo/>}/>
                        <Route path={PROFILE} element={<ProtectedRoute><Profile/></ProtectedRoute>}/>

                        <Route path={NEW_PROJECT} element={<ProtectedRoute><NewProject/></ProtectedRoute>}/>
                        <Route path={OPEN_PROJECT} element={<ProtectedRoute><OpenProject/></ProtectedRoute>}/>
                        <Route path={PROJECT} element={<ProtectedRoute><Project/></ProtectedRoute>}>
                            <Route path={CREATE_DATASET} element={<ProtectedRoute><CreateDataset/></ProtectedRoute>}/>
                            <Route path={UPLOAD_FILES} element={<ProtectedRoute><UploadFiles/></ProtectedRoute>}/>
                            <Route path={EDIT_PROJECT} element={<ProtectedRoute><EditProject/></ProtectedRoute>}/>
                            <Route
                                path={TYPING_DATA}
                                element={<ProtectedRoute><TypingData/></ProtectedRoute>}
                            />
                            <Route
                                path={ISOLATE_DATA}
                                element={<ProtectedRoute><IsolateData/></ProtectedRoute>}
                            />
                            <Route
                                path={DATASET}
                                element={<ProtectedRoute><DatasetDetails/></ProtectedRoute>}
                            />
                            <Route
                                path={EDIT_DATASET}
                                element={<ProtectedRoute><EditDataset/></ProtectedRoute>}
                            />
                            <Route
                                path={DISTANCE_MATRIX}
                                element={<ProtectedRoute><DistanceMatrix/></ProtectedRoute>}
                            />
                            <Route
                                path={TREE}
                                element={<ProtectedRoute><Tree/></ProtectedRoute>}
                            />
                            <Route
                                path={TREE_VIEW}
                                element={<ProtectedRoute><TreeView/></ProtectedRoute>}
                            />
                            <Route
                                path={REPORT}
                                element={<ProtectedRoute><Report/></ProtectedRoute>}
                            />
                            <Route
                                path={COMPUTE_HAMMING_DISTANCE}
                                element={<ProtectedRoute><ComputeHammingDistance/></ProtectedRoute>}
                            />
                            <Route
                                path={COMPUTE_GOEBURST}
                                element={<ProtectedRoute><GoeBURSTConfig/></ProtectedRoute>}
                            />
                            <Route
                                path={COMPUTE_GOEBURST_FULL_MST}
                                element={<ProtectedRoute><GoeBURSTFullMSTConfig/></ProtectedRoute>}
                            />
                            <Route
                                path={COMPUTE_HIERARCHICAL_CLUSTERING}
                                element={<ProtectedRoute><HierarchicalClusteringConfig/></ProtectedRoute>}
                            />
                            <Route
                                path={COMPUTE_NEIGHBOR_JOINING}
                                element={<ProtectedRoute><NeighborJoiningConfig/></ProtectedRoute>}
                            />
                            <Route
                                path={COMPUTE_NLV_GRAPH}
                                element={<ProtectedRoute><NLVGraphConfig/></ProtectedRoute>}/>
                        </Route>

                        <Route path="*" element={<NotFoundPage/>}/>
                    </Routes>
                </Dashboard>
            </div>
        </div>
    )
}
