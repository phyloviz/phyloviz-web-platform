import * as React from 'react'
import './App.css'
import {Route, Routes} from 'react-router-dom'
import About from "./Pages/About/About"
import {Uris} from "./Utils/navigation/Uris"
import Dashboard from "./Layouts/Dashboard/Dashboard";
import {Home} from "./Pages/Home/Home";
import Register from "./Pages/Register/Register";
import News from "./Pages/News/News";
import ApiInfo from "./Pages/ApiInfo/ApiInfo";
import Login from "./Pages/Login/Login";
import LoadDataset from "./Pages/LoadDataset/LoadDataset";
import NewProject from "./Pages/NewProject/NewProject";
import OpenProject from "./Pages/OpenProject/OpenProject";
import Project from "./Pages/Project/Project";
import HOME = Uris.HOME;
import ABOUT = Uris.ABOUT;
import LOGIN = Uris.LOGIN;
import REGISTER = Uris.REGISTER;
import NEWS = Uris.NEWS;
import API_INFO = Uris.API_INFO;
import LOAD_DATASET = Uris.LOAD_DATASET;
import NEW_PROJECT = Uris.NEW_PROJECT;
import OPEN_PROJECT = Uris.OPEN_PROJECT;
import PROJECT = Uris.PROJECT;

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
                        <Route path={LOGIN} element={<Login/>}/>
                        <Route path={REGISTER} element={<Register/>}/>
                        <Route path={NEWS} element={<News/>}/>
                        <Route path={API_INFO} element={<ApiInfo/>}/>
                        <Route path={LOAD_DATASET} element={<LoadDataset/>}/>
                        <Route path={NEW_PROJECT} element={<NewProject/>}/>
                        <Route path={OPEN_PROJECT} element={<OpenProject/>}/>
                        <Route path={PROJECT} element={<Project/>}/>
                    </Routes>
                </Dashboard>
            </div>
        </div>
    )
}
