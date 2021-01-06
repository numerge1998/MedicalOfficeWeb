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
import MedicationForm from "./components/medication-form";

import * as API_USERS from "./api/medication-api"
import MedicationTable from "./components/medication-table";
import MedicationUpdateForm from "./components/medication-update-form";
import NavigationBar from "../navigation-bar";
import Redirect from "react-router-dom/Redirect";



class MedicationContainer extends React.Component {

    constructor(props) {
        super(props);
        this.medication = {id:null, name:null, side_effects:null, dosage:null};
        this.id = null;
        this.toggleForm = this.toggleForm.bind(this);
        this.toggleForm2 = this.toggleForm2.bind(this);
        this.reload = this.reload.bind(this);
        this.reload1 = this.reload1.bind(this);
        this.reload_delete = this.reload_delete.bind(this);
        this.state = {
            selected: false,
            selected3 : false,
            collapseForm: false,
            tableData: [],
            isLoaded: false,
            errorStatus: 0,
            error: null
        };
    }

    componentDidMount() {
        this.fetchMedications();
    }

    fetchMedications() {
        return API_USERS.getMedications((result, status, err) => {

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

    toggleForm2() {
        this.setState({selected3: !this.state.selected3});
    }

    reload() {
        this.setState({
            isLoaded: false
        });
        this.toggleForm();
        this.fetchMedications();
    }

    reload_delete() {
        this.setState({
            isLoaded: false
        });
        this.fetchMedications();
    }

    reload1() {
        this.setState({
            isLoaded: false
        });
        this.toggleForm2();
        this.fetchMedications();
    }

    handleUpdate1 = (id) => {this.id = id; this.getById(this.id)};

    getById(id) {
        return API_USERS.getMedicationById(id,(result, status, err) => {

            if (result !== null && status === 200) {
                this.medication.id = result.id;
                this.medication.name = result.name;
                this.medication.side_effects = result.side_effects;
                this.medication.dosage = result.dosage;
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
                    <strong> Medication Management </strong>
                </CardHeader>
                <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="primary" onClick={this.toggleForm}>Add Medication </Button>
                        </Col>
                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoaded && <MedicationTable tableData = {this.state.tableData} reloadHandler = {this.reload_delete} id = {this.handleUpdate1} toggle2 = {this.toggleForm2}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />   }
                        </Col>
                    </Row>
                </Card>

                <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleForm}> Add Medication: </ModalHeader>
                    <ModalBody>
                        <MedicationForm reloadHandler={this.reload}/>
                    </ModalBody>
                </Modal>

                <Modal isOpen={this.state.selected3} toggle={this.toggleForm1}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleForm2}> Update Medication: </ModalHeader>
                    <ModalBody>
                        <MedicationUpdateForm reloadHandler = {this.reload1} medication = {this.medication} toggle2 = {this.toggleForm2}/>
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


export default MedicationContainer;
