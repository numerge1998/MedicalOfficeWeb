import React from 'react';

import BackgroundImg from '../commons/images/future-medicine.jpg';

import {Button, Container, Jumbotron, Modal, ModalBody, ModalHeader} from 'reactstrap';
import NavigationBar from "../navigation-bar";
import MedPlansForm from "./doctor-add-plans-form";
import Redirect from "react-router-dom/Redirect";

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "100%",
    height: "1920px",
    backgroundImage: `url(${BackgroundImg})`
};

class Home extends React.Component {

    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reload = this.reload.bind(this);
        this.state = {
            selected: false,
            collapseForm: false,
            tableData: [],
            isLoaded: false,
            errorStatus: 0,
            error: null
        };
    }

    toggleForm() {
        console.log(localStorage.role);
        this.setState({selected: !this.state.selected});
    }

    reload() {
        this.setState({
            isLoaded: false
        });
        this.toggleForm();
        //this.fetchCaregivers();
    }

    logout(){
        //window.location.href = "https://alin-pop-frontend-1.herokuapp.com";
        localStorage.removeItem("role");
        window.location.href = "http://localhost:3000/";
    }

    render() {
        if(localStorage.role === "doctor") {
        return (

            <div>
                <NavigationBar/>
                <Jumbotron fluid style={backgroundStyle}>
                    <Container fluid>
                        <p className="lead">
                            <Button color="danger" onClick={this.logout}>Logout </Button>{' '}
                            <Button color="primary" onClick={this.toggleForm}>Add medication plan </Button>
                        </p>
                    </Container>
                </Jumbotron>

                <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleForm}> Patients: </ModalHeader>
                    <ModalBody>
                        <MedPlansForm  toggle={ this.toggleForm} reloadHandler = {this.reload}/>
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

export default Home
