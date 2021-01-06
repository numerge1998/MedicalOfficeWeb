import React from 'react';
import APIResponseErrorMessage from "../commons/errorhandling/api-response-error-message";
import {
    Button,
    Card,
    CardHeader,
    Col,
    Modal,
    ModalBody,
    ModalHeader,
    Row
} from 'reactstrap';
import PatientForm from "./components/patient-form";

import * as API_USERS from "./api/patient-api"
import PatientTable from "./components/patient-table";
import PatientUpdateForm from "./components/patient-update-form";
import NavigationBar from "../navigation-bar";
import Redirect from "react-router-dom/Redirect";



class PatientContainer extends React.Component {

    constructor(props) {
        super(props);
        this.patient = {id:null, address:null, birth_date:null, gender:null, name:null};
        this.id = null;
        this.toggleForm = this.toggleForm.bind(this);
        this.toggleForm1 = this.toggleForm1.bind(this);
        this.toggleForm2 = this.toggleForm2.bind(this);
        this.reload = this.reload.bind(this);
        this.reload1 = this.reload1.bind(this);
        this.reload_delete = this.reload_delete.bind(this);
        this.state = {
            selected: false,
            selected2: false,
            selected3 : false,
            collapseForm: false,
            tableData: [],
            isLoaded: false,
            errorStatus: 0,
            error: null
        };
    }

    componentDidMount() {
        this.fetchPatients();
    }

    fetchPatients() {
        return API_USERS.getPatients((result, status, err) => {

            if (result !== null && status === 200) {
                this.setState({
                    tableData: result,
                    isLoaded: true
                });
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }
        });
    }

    toggleForm() {
        this.setState({selected: !this.state.selected});
    }

    toggleForm1() {
        this.setState({selected2: !this.state.selected2});
    }

    toggleForm2() {
        this.setState({selected3: !this.state.selected3});
    }

    reload() {
        this.setState({
            isLoaded: false
        });
        this.toggleForm();
        this.fetchPatients();
    }

    reload1() {
        this.setState({
            isLoaded: false
        });
        this.toggleForm1();
        this.fetchPatients();
    }

    reload_delete(){
        this.setState({
            isLoaded: false
        });
        this.fetchPatients();
    }

    handleUpdate1 = (id) => {this.id = id; console.log(this.id); this.getById(this.id)};

    getById(id) {
        return API_USERS.getPatientById(id,(result, status, err) => {

            if (result !== null && status === 200) {
                this.patient.id = result.id;
                this.patient.address = result.address;
                this.patient.birth_date = result.birth_date;
                this.patient.gender = result.gender;
                this.patient.name = result.name;
                console.log(this.patient)
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }
        });
    }

    render() {
        if(localStorage.role === "doctor") {
        return (
            <div>
                <NavigationBar/>
                <CardHeader>
                    <strong> Patient Management </strong>
                </CardHeader>
                <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                             <Button color="primary" onClick={this.toggleForm}>Add Patient </Button>{' '}
                        </Col>
                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoaded && <PatientTable tableData = {this.state.tableData} reloadHandler = {this.reload_delete} id = {this.handleUpdate1} toggle1 = {this.toggleForm1}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />   }
                        </Col>
                    </Row>
                </Card>

                <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleForm}> Add Patient: </ModalHeader>
                    <ModalBody>
                        <PatientForm reloadHandler={this.reload}/>
                    </ModalBody>
                </Modal>

                <Modal isOpen={this.state.selected2} toggle={this.toggleForm1}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleForm1}> Update Patient: </ModalHeader>
                    <ModalBody>
                        <PatientUpdateForm reloadHandler = {this.reload1} patient = {this.patient} toggle1 = {this.toggleForm1}/>
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
    }
}


export default PatientContainer;
