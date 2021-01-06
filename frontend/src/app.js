import React from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import Home from './home/home';
import PersonContainer from './person/person-container'
import PatientContainer from "./patient/patient-container";

import ErrorPage from './commons/errorhandling/error-page';
import styles from './commons/styles/project-style.css';
import CaregiverContainer from "./caregiver/caregiver-container";
import MedicationContainer from "./medication/medication-container";
import LoginContainer from "./login/login-container";
import HomePat from "./home/homePat";
import HomeCaregiver from "./home/homeCaregiver";

class App extends React.Component {


    render() {

        return (
            <div className={styles.back}>
            <Router>
                <div>
                    <Switch>

                        <Route
                            exact
                            path='/'
                            render={() => <LoginContainer/>}
                        />

                        <Route
                            exact
                            path='/homeDoctor'
                            render={() => <Home/>}
                        />

                        <Route
                            exact
                            path='/homePatient'
                            render={() => <HomePat/>}
                        />

                        <Route
                            exact
                            path='/homeCaregiver'
                            render={() => <HomeCaregiver/>}
                        />

                        <Route
                            exact
                            path='/person'
                            render={() => <PersonContainer/>}
                        />

                        <Route
                            exact
                            path='/patient'
                            render={() => <PatientContainer/>}
                        />

                        <Route
                            exact
                            path='/caregiver'
                            render={() => <CaregiverContainer/>}
                        />

                        <Route
                            exact
                            path='/medication'
                            render={() => <MedicationContainer/>}
                        />

                        {/*Error*/}
                        <Route
                            exact
                            path='/error'
                            render={() => <ErrorPage/>}
                        />

                        <Route render={() =><ErrorPage/>} />
                    </Switch>
                </div>
            </Router>
            </div>
        )
    };
}

export default App
