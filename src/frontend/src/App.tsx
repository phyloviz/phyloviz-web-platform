import * as React from 'react'
import './App.css'
import {Route, Routes} from 'react-router-dom'
import About from "./Components/About/About"
import {Uris} from "./Utils/navigation/Uris"
import Dashboard from "./Components/Dashboard/Dashboard";
import HOME = Uris.HOME;
import ABOUT = Uris.ABOUT;

/**
 * App component.
 */
export default function App() {

    return (
        <div className="App">
            <div className="App-content">
                <Routes>
                    <Route path={HOME} element={<Dashboard/>}/>
                    <Route path={ABOUT} element={<About/>}/>
                </Routes>
            </div>
        </div>
    )
}
