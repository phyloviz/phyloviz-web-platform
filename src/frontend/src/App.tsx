import * as React from 'react'
import './App.css'
import {Route, Routes} from 'react-router-dom'
import About from "./Pages/About/About"
import {WebUiUris} from "./Utils/navigation/WebUiUris"
import Dashboard from "./Layouts/Dashboard/Dashboard";
import {Home} from "./Pages/Home/Home";
import News from "./Pages/News/News";
import ApiInfo from "./Pages/ApiInfo/ApiInfo";
import CreateDataset from "./Pages/Project/CreateDataset";
import NewProject from "./Pages/NewProject/NewProject";
import OpenProject from "./Pages/OpenProject/OpenProject";
import Project from "./Pages/Project/Project";
import {NotFoundPage} from "./Pages/NotFoundPage";
import UploadFiles from "./Pages/Project/UploadFiles";
import {useLoggedIn} from "./Session/Session";
import {WebApiUris} from "./Utils/navigation/WebApiUris";
import GoeBURSTConfig from "./Components/Project/ComputeConfigurations/GoeBURSTConfig/GoeBURSTConfig";
import GoeBURSTFullMSTConfig
    from "./Components/Project/ComputeConfigurations/GoeBURSTFullMSTConfig/GoeBURSTFullMSTConfig";
import HierarchicalClusteringConfig
    from "./Components/Project/ComputeConfigurations/HierarchicalClusteringConfig/HierarchicalClusteringConfig";
import NeighborJoiningConfig
    from "./Components/Project/ComputeConfigurations/NeighborJoiningConfig/NeighborJoiningConfig";
import NLVGraphConfig from "./Components/Project/ComputeConfigurations/NLVGraphConfig/NLVGraphConfig";
import HOME = WebUiUris.HOME;
import ABOUT = WebUiUris.ABOUT;
import NEWS = WebUiUris.NEWS;
import API_INFO = WebUiUris.API_INFO;
import LOAD_DATASET = WebUiUris.LOAD_DATASET;
import OPEN_PROJECT = WebUiUris.OPEN_PROJECT;
import PROJECT = WebUiUris.PROJECT;
import TYPING_DATA = WebUiUris.TYPING_DATA;
import ISOLATE_DATA = WebUiUris.ISOLATE_DATA;
import DISTANCE_MATRIX = WebUiUris.DISTANCE_MATRIX;
import TREE_VIEW = WebUiUris.TREE_VIEW;
import UPLOAD_FILES = WebUiUris.UPLOAD_FILES;
import PROFILE = WebUiUris.PROFILE;
import NEW_PROJECT = WebUiUris.NEW_PROJECT;
import COMPUTE_CONFIG_GOEBURST = WebUiUris.COMPUTE_CONFIG_GOEBURST;
import COMPUTE_CONFIG_GOEBURST_FULL_MST = WebUiUris.COMPUTE_CONFIG_GOEBURST_FULL_MST;
import COMPUTE_CONFIG_HIERARCHICAL_CLUSTERING = WebUiUris.COMPUTE_CONFIG_HIERARCHICAL_CLUSTERING;
import COMPUTE_CONFIG_NEIGHBOR_JOINING = WebUiUris.COMPUTE_CONFIG_NEIGHBOR_JOINING;
import COMPUTE_CONFIG_NLV_GRAPH = WebUiUris.COMPUTE_CONFIG_NLV_GRAPH;

/**
 * App component.
 */
export default function App() {

    const loggedIn = useLoggedIn();

    /**
     * Protection route component, redirects to login page if not logged in.
     *
     * @param children the children to render
     */
    function ProtectedRoute({children}: { children: React.ReactElement }) {
        if (!loggedIn) {
            window.location.href = WebApiUris.login;
            return null;
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
                        <Route path={API_INFO} element={<ApiInfo/>}/>

                        <Route path={PROFILE} element={<ProtectedRoute>
                            <div>Profile</div>
                        </ProtectedRoute>}/>

                        <Route path={NEW_PROJECT} element={<ProtectedRoute><NewProject/></ProtectedRoute>}/>
                        <Route path={OPEN_PROJECT} element={<ProtectedRoute><OpenProject/></ProtectedRoute>}/>
                        <Route path={PROJECT} element={<ProtectedRoute><Project/></ProtectedRoute>}>
                            <Route path={LOAD_DATASET} element={<ProtectedRoute><CreateDataset/></ProtectedRoute>}/>
                            <Route path={UPLOAD_FILES} element={<ProtectedRoute><UploadFiles/></ProtectedRoute>}/>
                            <Route
                                path={TYPING_DATA}
                                element={<ProtectedRoute><div>Typing data</div></ProtectedRoute>}
                            />
                            <Route
                                path={ISOLATE_DATA}
                                element={<ProtectedRoute><div>Isolate data</div></ProtectedRoute>}
                            />
                            <Route
                                path={DISTANCE_MATRIX}
                                element={<ProtectedRoute><div>Distance matrix</div></ProtectedRoute>}
                            />
                            <Route
                                path={TREE_VIEW}
                                element={<ProtectedRoute><div>Tree view</div></ProtectedRoute>}
                            />
                            <Route
                                path={COMPUTE_CONFIG_GOEBURST}
                                element={<ProtectedRoute><GoeBURSTConfig/></ProtectedRoute>}
                            />
                            <Route
                                path={COMPUTE_CONFIG_GOEBURST_FULL_MST}
                                element={<ProtectedRoute><GoeBURSTFullMSTConfig/></ProtectedRoute>}
                            />
                            <Route
                                path={COMPUTE_CONFIG_HIERARCHICAL_CLUSTERING}
                                element={<ProtectedRoute><HierarchicalClusteringConfig/></ProtectedRoute>}
                            />
                            <Route
                                path={COMPUTE_CONFIG_NEIGHBOR_JOINING}
                                element={<ProtectedRoute><NeighborJoiningConfig/></ProtectedRoute>}
                            />
                            <Route
                                path={COMPUTE_CONFIG_NLV_GRAPH}
                                element={<ProtectedRoute><NLVGraphConfig/></ProtectedRoute>}/>
                        </Route>

                        <Route path="*" element={<NotFoundPage/>}/>
                    </Routes>
                </Dashboard>
            </div>
        </div>
    )
}
