import React from 'react';
import validate from "./validators/caregiver-validators";
import Button from "react-bootstrap/Button";
import * as API_USERS from "../api/caregiver-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import { FormGroup, Input, Label} from 'reactstrap';
import Table from "../../commons/tables/table";



class CaregiverPatientsForm extends React.Component {

    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.toggleForm4 = this.toggleForm4.bind(this);
        this.handleUpdate1 = this.handleUpdate1.bind(this);
        this.toggle2 = this.props.toggle2;

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,
            selected4 : false,
            tableData2 : [],
            isLoaded1 : false,

            formControls: {
                id: {
                    value: '',
                    placeholder: 'Insert the id',
                    valid: false,
                    touched: false,
                    validationRules: {
                        minLength: 3,
                        isRequired: true
                    }
                },
            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleView = this.handleView.bind(this);
    }


    columns = [
        {
            Header: 'Name',
            accessor: 'name',
        },
        {
            Header: 'Address',
            accessor: 'address',
        },
        {
            Header: 'Birth_date',
            accessor: 'birth_date',
        },
        {
            Header: 'Gender',
            accessor: 'gender',
        }
    ];

    filters = [
        {
            accessor: 'name',
        }
    ];
    h;

    toggleForm() {
        this.setState({collapseForm: !this.state.collapseForm});
    }

    toggleForm4() {
        this.setState({selected4: !this.state.selected4});
    }

    handleChange = event => {

        const name = event.target.name;
        const value = event.target.value;

        const updatedControls = this.state.formControls;

        const updatedFormElement = updatedControls[name];

        updatedFormElement.value = value;
        updatedFormElement.touched = true;
        updatedFormElement.valid = validate(value, updatedFormElement.validationRules);
        updatedControls[name] = updatedFormElement;

        let formIsValid = true;
        for (let updatedFormElementName in updatedControls) {
            formIsValid = updatedControls[updatedFormElementName].valid && formIsValid;
        }

        this.setState({
            formControls: updatedControls,
            formIsValid: formIsValid
        });

    };

    viewPatients(id) {
        return API_USERS.getCaregiversPatients(id, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                this.setState({
                    tableData2: result,
                    isLoaded1: true
                });
                console.log("pacientii caregiverului cu id: " + id + " sunt:" + this.state.tableData2);
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    handleView() {
        let id1 = this.state.formControls.id.value;
        this.viewPatients(id1);
    }

    handleUpdate1(){
        this.toggle2();
    }

    render() {
        return (
            <div>

                <FormGroup id='id'>
                    <Label for='idField'> Id: </Label>
                    <Input name='id' id='idField' placeholder={this.state.formControls.id.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.id.value}
                           touched={this.state.formControls.id.touched? 1 : 0}
                           valid={this.state.formControls.id.valid}
                           required
                    />
                </FormGroup>

                <Row>
                    <Col sm={{size: '4', offset: 8}}>
                        <Button type={"view"} disabled={!this.state.formIsValid} onClick={this.handleView}>  View </Button>{' '}
                        <Button variant = "danger" type={"close"} onClick={this.handleUpdate1}>  Close </Button>
                    </Col>

                </Row>

                {this.state.isLoaded1 &&
                <Table
                    data={this.state.tableData2}
                    columns={this.columns}
                    search={this.filters}
                    pageSize={5}
                />}

                {
                    this.state.errorStatus > 0 &&
                    <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>
                }
            </div>
        ) ;
    }
}

export default CaregiverPatientsForm;
