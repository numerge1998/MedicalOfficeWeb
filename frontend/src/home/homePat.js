import React from 'react';

import BackgroundImg from '../commons/images/future-medicine.jpg';

import {Button, Container, Jumbotron, Modal, ModalBody, ModalHeader} from 'reactstrap';
import PacientMedsForm from "../patient/components/patients-med-plans-form";
import Redirect from "react-router-dom/Redirect";

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "100%",
    height: "1920px",
    backgroundImage: `url(${BackgroundImg})`
};

class HomePat extends React.Component {

    constructor(props) {
        super(props);
        this.toggleForm2 = this.toggleForm2.bind(this);
        this.state = {
            selected3 : false,
            collapseForm: false,
            tableData: [],
            isLoaded: false,
            errorStatus: 0,
            error: null
        };
    }

    logout(){
        //window.location.href = "https://alin-pop-frontend-1.herokuapp.com";
        localStorage.removeItem("role");
        window.location.href = "http://localhost:3000/";
    }

    toggleForm2() {
        this.setState({selected3: !this.state.selected3});
    }

    render() {
        if(localStorage.role === "patient") {
            return (
                <div>
                    <Jumbotron fluid style={backgroundStyle}>
                        <Container fluid>
                            <p className="lead">
                                <Button color="dark" onClick={this.toggleForm2}>View Med Plans </Button>{' '}
                                <Button color="danger" onClick={this.logout}>Logout </Button>
                            </p>
                        </Container>
                    </Jumbotron>

                    <Modal isOpen={this.state.selected3} toggle2={this.toggleForm2}
                           className={this.props.className} size="lg">
                        <ModalHeader toggle2={this.toggleForm2}> Medication Plans: </ModalHeader>
                        <ModalBody>
                            <PacientMedsForm toggle2={this.toggleForm2}/>
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

export default HomePat
