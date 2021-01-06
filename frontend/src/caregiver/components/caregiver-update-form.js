import React from 'react';
import validate from "./validators/caregiver-validators";
import Button from "react-bootstrap/Button";
import * as API_USERS from "../api/caregiver-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import { FormGroup, Input, Label} from 'reactstrap';


class CaregiverUpdateForm extends React.Component {

    constructor(props) {
        super(props);
        this.toggle1 = this.props.toggle1;
        this.toggleForm = this.toggleForm.bind(this);
        this.caregiver = this.props.caregiver;
        this.reloadHandler = this.props.reloadHandler;

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,

            formControls: {
                address: {
                    value: '',
                    placeholder: 'Your address new is?...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
                birth_date: {
                    value: '',
                    placeholder: 'What is your new birth-date?...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
                gender: {
                    value: '',
                    placeholder: 'What is your new gender?...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
                name: {
                    value: '',
                    placeholder: 'What is your new name?...',
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

    updateCaregiver(caregiver) {
        return API_USERS.updateCaregiver(caregiver, (result, status, error) => {
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

    handleUpdate1(){
        this.toggle1();
    }

    handleUpdate() {
        let caregiver = {
            id: this.caregiver.id,
            name: this.state.formControls.name.value,
            address: this.state.formControls.address.value,
            birth_date: this.state.formControls.birth_date.value,
            gender: this.state.formControls.gender.value,
        };

        this.updateCaregiver(caregiver);
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

                <FormGroup id='address'>
                    <Label for='addressField'> Address: </Label>
                    <Input name='address' id='addressField' placeholder={this.state.formControls.address.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.address.value}
                           touched={this.state.formControls.address.touched? 1 : 0}
                           valid={this.state.formControls.address.valid}
                           required
                    />
                    {this.state.formControls.address.touched && !this.state.formControls.address.valid &&
                    <div className={"error-message"}> * Address must exist</div>}
                </FormGroup>

                <FormGroup id='birth_date'>
                    <Label for='birth_dateField'> Birth_Date: </Label>
                    <Input name='birth_date' id='birth_dateField' placeholder={this.state.formControls.birth_date.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.birth_date.value}
                           touched={this.state.formControls.birth_date.touched? 1 : 0}
                           valid={this.state.formControls.birth_date.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='gender'>
                    <Label for='genderField'> Gender: </Label>
                    <Input name='gender' id='genderField' placeholder={this.state.formControls.gender.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.gender.value}
                           touched={this.state.formControls.gender.touched? 1 : 0}
                           valid={this.state.formControls.gender.valid}
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

export default CaregiverUpdateForm;
