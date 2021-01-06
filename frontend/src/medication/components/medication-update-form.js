import React from 'react';
import validate from "./validators/medication-validators";
import Button from "react-bootstrap/Button";
import * as API_USERS from "../api/medication-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import { FormGroup, Input, Label} from 'reactstrap';

class MedicationUpdateForm extends React.Component {

    constructor(props) {
        super(props);
        this.toggle2 = this.props.toggle2;
        this.toggleForm = this.toggleForm.bind(this);
        this.medication = this.props.medication;
        this.reloadHandler = this.props.reloadHandler;

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,

            formControls: {
                name: {
                    value: '',
                    placeholder: 'What is your new name?...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
                side_effects: {
                    value: '',
                    placeholder: 'Side effects',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
                dosage: {
                    value: '',
                    placeholder: 'Dosage',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleUpdate = this.handleUpdate.bind(this);
        this.handleUpdate1 = this.handleUpdate1.bind(this);
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

    updateMedication(medication) {
        return API_USERS.updateMedication1(medication, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully updated medication with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    handleUpdate1(){
        this.toggle2();
    }

    handleUpdate() {

        console.log(this.medication.id + "aici");
        let medication = {
            id: this.medication.id,
            name: this.state.formControls.name.value,
            side_effects: this.state.formControls.side_effects.value,
            dosage: this.state.formControls.dosage.value,
        };
        console.log(medication.id);
        console.log(medication.name);
        this.updateMedication(medication);
    }

    render() {
        return (
            <div>

                <FormGroup id='name'>
                    <Label for='nameField'> Name: </Label>
                    <Input name='name' id='nameField' placeholder={this.state.formControls.name.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.name.value}
                           touched={this.state.formControls.name.touched? 1 : 0}
                           valid={this.state.formControls.name.valid}
                           required
                    />
                    {this.state.formControls.name.touched && !this.state.formControls.name.valid &&
                    <div className={"error-message row"}> * Name must exist </div>}
                </FormGroup>

                <FormGroup id='side_effects'>
                    <Label for='side_effectsField'> Side Effects: </Label>
                    <Input name='side_effects' id='side_effectsField' placeholder={this.state.formControls.side_effects.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.side_effects.value}
                           touched={this.state.formControls.side_effects.touched? 1 : 0}
                           valid={this.state.formControls.side_effects.valid}
                           required
                    />
                    {this.state.formControls.side_effects.touched && !this.state.formControls.side_effects.valid &&
                    <div className={"error-message"}> * Address must exist</div>}
                </FormGroup>

                <FormGroup id='dosage'>
                    <Label for='dosageField'> Dosage: </Label>
                    <Input name='dosage' id='dosageField' placeholder={this.state.formControls.dosage.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.dosage.value}
                           touched={this.state.formControls.dosage.touched? 1 : 0}
                           valid={this.state.formControls.dosage.valid}
                           required
                    />
                </FormGroup>

                <Button variant = "dark" type={"update"} disabled={!this.state.formIsValid} onClick={this.handleUpdate}>  Update </Button>{' '}
                <Button variant = "danger" type={"close"} onClick={this.handleUpdate1}>  Close </Button>

                {
                    this.state.errorStatus > 0 &&
                    <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>
                }
            </div>
        ) ;
    }
}

export default MedicationUpdateForm;
