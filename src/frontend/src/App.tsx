import * as React from 'react'
import './App.css'
import {Route, Routes} from 'react-router-dom'
import About from "./Pages/About/About"
import {WebUiUris} from "./Utils/navigation/WebUiUris"
import Dashboard from "./Layouts/Dashboard/Dashboard";
import {Home} from "./Pages/Home/Home";
import Register from "./Pages/Register/Register";
import News from "./Pages/News/News";
import ApiInfo from "./Pages/ApiInfo/ApiInfo";
import CreateDataset from "./Pages/Project/CreateDataset";
import NewProject from "./Pages/NewProject/NewProject";
import OpenProject from "./Pages/OpenProject/OpenProject";
import Project from "./Pages/Project/Project";
import {NotFoundPage} from "./Pages/NotFoundPage";
import Login from "./Pages/Login/Login";
import UploadFiles from "./Pages/Project/UploadFiles";
import HOME = WebUiUris.HOME;
import ABOUT = WebUiUris.ABOUT;
import LOGIN = WebUiUris.LOGIN;
import REGISTER = WebUiUris.REGISTER;
import NEWS = WebUiUris.NEWS;
import API_INFO = WebUiUris.API_INFO;
import LOAD_DATASET = WebUiUris.LOAD_DATASET;
import NEW_PROJECT = WebUiUris.NEW_PROJECT;
import OPEN_PROJECT = WebUiUris.OPEN_PROJECT;
import PROJECT = WebUiUris.PROJECT;
import TYPING_DATA = WebUiUris.TYPING_DATA;
import ISOLATE_DATA = WebUiUris.ISOLATE_DATA;
import DISTANCE_MATRIX = WebUiUris.DISTANCE_MATRIX;
import TREE_VIEW = WebUiUris.TREE_VIEW;
import UPLOAD_FILES = WebUiUris.UPLOAD_FILES;

/**
 * App component.
 */
export default function App() {

    return (
        <div className="App">
            <div className="App-content">
                <Dashboard>
                    <Routes>
                        <Route path={HOME} element={<Home/>}/>
                        <Route path={ABOUT} element={<About/>}/>
                        <Route path={NEWS} element={<News/>}/>
                        <Route path={API_INFO} element={<ApiInfo/>}/>

                        <Route path={LOGIN} element={<Login/>}/>
                        <Route path={REGISTER} element={<Register/>}/>

                        <Route path={NEW_PROJECT} element={<NewProject/>}/>
                        <Route path={OPEN_PROJECT} element={<OpenProject/>}/>
                        <Route path={PROJECT} element={<Project/>}>
                            <Route path={LOAD_DATASET} element={<CreateDataset/>}/>
                            <Route path={UPLOAD_FILES} element={<UploadFiles/>}/>
                            <Route path={TYPING_DATA} element={<div>Typing data</div>}/>
                            <Route path={ISOLATE_DATA} element={<div>Isolate data</div>}/>
                            <Route path={DISTANCE_MATRIX} element={<div>Distance matrix</div>}/>
                            <Route path={TREE_VIEW} element={<div>Tree view</div>}/>
                        </Route>

                        <Route path="*" element={<NotFoundPage/>}/>
                    </Routes>
                </Dashboard>
            </div>
        </div>
    )
}
