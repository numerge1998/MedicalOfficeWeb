import React from 'react';
import validate from "../patient/components/validators/patient-validators";
import Button from "react-bootstrap/Button";
import * as API_USERS from "./doctor-add-plan-api";
import APIResponseErrorMessage from "../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import { FormGroup, Input, Label} from 'reactstrap';

class PatientForm extends React.Component {

    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadHandler = this.props.reloadHandler;

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,

            formControls: {
                intervals: {
                    value: '',
                    placeholder: 'intervals',
                    valid: false,
                    touched: false,
                    validationRules: {
                        minLength: 3,
                        isRequired: true
                    }
                },
                period: {
                    value: '',
                    placeholder: 'period',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
                patient_id_patient: {
                    value: '',
                    placeholder: 'patient id',
                    valid: false,
                    touched: false,
                },
            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    toggleForm() {
        this.setState({collapseForm: !this.state.collapseForm});
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

    registerMedPlan(medPlan, id) {
        return API_USERS.postMedPlan(medPlan, id, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully inserted patient with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    handleSubmit() {
        let medicationPlan = {
            intervals: this.state.formControls.intervals.value,
            period: this.state.formControls.period.value,
        };

        let id = this.state.formControls.patient_id_patient.value;

        this.registerMedPlan(medicationPlan, id);
    }

    render() {
        return (
            <div>

                <FormGroup id='intervals'>
                    <Label for='intervalsField'> Intervals: </Label>
                    <Input name='intervals' id='intervalsField' placeholder={this.state.formControls.intervals.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.intervals.value}
                           touched={this.state.formControls.intervals.touched? 1 : 0}
                           valid={this.state.formControls.intervals.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='period'>
                    <Label for='periodField'> Period: </Label>
                    <Input name='period' id='periodField' placeholder={this.state.formControls.period.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.period.value}
                           touched={this.state.formControls.period.touched? 1 : 0}
                           valid={this.state.formControls.period.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='patient_id_patient'>
                    <Label for='patient_id_patientField'> Patient id: </Label>
                    <Input name='patient_id_patient' id='patient_id_patientField' placeholder={this.state.formControls.patient_id_patient.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.patient_id_patient.value}
                           touched={this.state.formControls.patient_id_patient.touched? 1 : 0}
                           valid={this.state.formControls.patient_id_patient.valid}
                           required
                    />
                </FormGroup>

                <Row>
                    <Col sm={{size: '4', offset: 8}}>
                        <Button type={"submit"} disabled={!this.state.formIsValid} onClick={this.handleSubmit}>  Add Medication Plan </Button>
                    </Col>
                </Row>

                {
                    this.state.errorStatus > 0 &&
                    <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>
                }
            </div>
        ) ;
    }
}

export default PatientForm;
