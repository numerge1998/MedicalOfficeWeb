import React from 'react';

import BackgroundImg from '../commons/images/future-medicine.jpg';

import SockJsClient from 'react-stomp';
import {Button, Container, Jumbotron, Modal, ModalBody, ModalHeader} from 'reactstrap';
import CaregiverPatientsForm from "../caregiver/components/caregiver-patients-form";
import Redirect from "react-router-dom/Redirect";
import { Alert } from "react-bs-notifier";

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "100%",
    height: "1920px",
    backgroundImage: `url(${BackgroundImg})`
};



class HomeCaregiver extends React.Component {

    constructor(props) {
        super(props);
        this.i = 0;
        this.toggleForm2 = this.toggleForm2.bind(this);
        this.toggleForm3 = this.toggleForm3.bind(this);
        this.NotifierGenerator = this.NotifierGenerator.bind(this);
        this.state = {
            selected3: false,
            selected4: false,
            visible : false,
            collapseForm: false,
            tableData: [],
            isLoaded: false,
            errorStatus: 0,
            error: null
        };
    }

    NotifierGenerator() {
        if(this.state.selected4) {
            if (localStorage.activ === "Sleeping") {
                localStorage.removeItem("activ");
                if (this.state.visible){
                    return (
                        <Alert type="warning" headline="Sleeping">
                            Sleep period longer than 7 hours for patient with id 813db233-2d3a-49f2-b5c3-d1cab10d96d9
                        </Alert>
                    );
            }
                else {
                    this.toggleForm3();
                    return null;
                }
        }
            else if(localStorage.activ === "Leaving") {
                localStorage.removeItem("activ");
                if (this.state.visible) {
                    return (
                        <Alert type="warning" headline="Leaving">
                            The leaving activity (outdoor) is longer than 5 hours for patient with id 813db233-2d3a-49f2-b5c3-d1cab10d96d9
                        </Alert>
                    );
                }
                else {
                    this.toggleForm3();
                    return null;
                }
            }
            else if(localStorage.activ === "Grooming") {
                localStorage.removeItem("activ");
                if (this.state.visible) {
                    return (
                        <Alert type="warning" headline="Bathroom">
                            Period spent in bathroom is longer than 30 minutes for patient with id 813db233-2d3a-49f2-b5c3-d1cab10d96d9
                        </Alert>
                    );
                }
                else {
                    this.toggleForm3();
                    return null;
                }
            }
            else {
                this.toggleForm3();
                return null;
            }
        }
        else
            return null;
    }

    toggleForm2() {
        this.setState({selected3: !this.state.selected3});
    }

    toggleForm3() {
        this.setState({visible:true},()=>{
            window.setTimeout(()=>{
                this.setState({visible:false})
            },5000)
        });
        this.setState({selected4: !this.state.selected4});
    }


    logout(){
        //window.location.href = "https://alin-pop-frontend-1.herokuapp.com";
        localStorage.removeItem("role");
        window.location.href = "http://localhost:3000/";
    }

    render() {
        if(localStorage.role === "caregiver") {
        return (
            <div>
                <SockJsClient url='http://localhost:8080/websocket/tracker/'
                              topics={['/topic/tracker']}
                              onConnect={() => {
                                  console.log("connected");
                              }}
                              onDisconnect={() => {
                                  console.log("Disconnected");
                              }}
                              onMessage={(msg) => {
                                  localStorage.setItem("activ", msg.message);
                                  this.toggleForm3();
                                  console.log(msg.message);
                              }}
                              ref={(client) => {
                                  this.clientRef = client
                              }}/>
                <Jumbotron fluid style={backgroundStyle}>

                    <Container fluid>
                        <p className="lead">
                            <Button color = "dark" onClick={this.toggleForm2}>View Patients </Button>{' '}
                            <Button color="danger" onClick={this.logout}>Logout </Button>{' '}

                        </p>
                        <this.NotifierGenerator/>
                    </Container>
                </Jumbotron>


                <Modal isOpen={this.state.selected3} toggle2={this.toggleForm2}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle2={this.toggleForm2}> Patients: </ModalHeader>
                    <ModalBody>
                        <CaregiverPatientsForm  toggle2 ={ this.toggleForm2}/>
                    </ModalBody>
                </Modal>
            </div>
        )
        }
        else{
            localStorage.removeItem("role");
            return(<Redirect to={{
                pathname:'/'}
            }/>)
        }
    };
}

export default HomeCaregiver
