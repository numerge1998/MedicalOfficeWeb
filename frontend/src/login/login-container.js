import React from 'react';
import validate from "../person/components/validators/person-validators";
import Button from "react-bootstrap/Button";
import * as API_USERS from "../login/login-api";
import APIResponseErrorMessage from "../commons/errorhandling/api-response-error-message";
import {Col, Modal, ModalBody, Row} from "reactstrap";
import { FormGroup, Input, Label} from 'reactstrap';
import Form from "reactstrap/es/Form";




class LoginContainer extends React.Component {

    constructor(props) {
        super(props);
        this.login = {id:null, username:null, password:null, role:null};

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,
            role : '',

            formControls: {
                username: {
                    value: '',
                    placeholder: '',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
                password: {
                    value: '',
                    placeholder: '',
                    type:"password",
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
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

    getByUsername(id) {
        return API_USERS.getUserRole(id,(result, status, err) => {

            if (result !== null && status === 200) {
                this.login.id = result.id;
                this.login.username = result.username;
                this.login.password = result.password;
                this.login.role = result.role;
                console.log(this.login.role + " this");
                 if(this.login.role === "doctor") {
                     this.render();
                     //window.location.href = "https://alin-pop-frontend-1.herokuapp.com/homeDoctor"
                     window.location.href = "http://localhost:3000/homeDoctor"
                     localStorage.setItem("role", 'doctor');
                 }
                if(this.login.role === "caregiver") {
                    //window.location.href = "https://alin-pop-frontend-1.herokuapp.com/homeCaregiver"
                    window.location.href = "http://localhost:3000/homeCaregiver"
                    localStorage.setItem("role", 'caregiver');
                }
                else if(this.login.role === "patient") {
                    //window.location.href = "https://alin-pop-frontend-1.herokuapp.com/homePatient"
                    window.location.href = "http://localhost:3000/homePatient"
                    localStorage.setItem("role", 'patient');
                }
                else {
                    console.log("nu am introdus bine... " + this.login.role);
                }
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }
        });
    }


    handleSubmit() {
        localStorage.removeItem("role");
        let asta = this.state.formControls.username.value;
        console.log(asta);
        this.getByUsername(asta);
        this.render()
        // if(this.login.role === "doctor")
        //     window.location.href = "https://alin-pop-frontend-1.herokuapp.com/homeDoctor"
        // else if(this.login.role === "caregiver")
        //     window.location.href = "https://alin-pop-frontend-1.herokuapp.com/homeCaregiver"
        // else if(this.login.role === "patient")
        //     window.location.href = "https://alin-pop-frontend-1.herokuapp.com/homePatient"
        // else {
        //     console.log("nu am introdus bine... " + this.login.role);
        // }
    }

    render() {
        return (
            <Modal isOpen={true}
                   className={this.props.className} size="lg">
                <ModalBody>
                    <Form inline>
                        <FormGroup id='username'>
                            <Label for='usernameField'> Username: </Label>
                            <Input name='username' id='usernameField' placeholder={this.state.formControls.username.placeholder}
                                   onChange={this.handleChange}
                                   defaultValue={this.state.formControls.username.value}
                                   touched={this.state.formControls.username.touched? 1 : 0}
                                   valid={this.state.formControls.username.valid}
                                   required
                            />
                        </FormGroup>

                        <FormGroup id='password'>
                            <Label for='passwordField'> Password: </Label>
                            <Input name='password' type={"password"} id='passwordField' placeholder={this.state.formControls.password.placeholder}
                                   onChange={this.handleChange}
                                   defaultValue={this.state.formControls.password.value}
                                   touched={this.state.formControls.password.touched? 1 : 0}
                                   valid={this.state.formControls.password.valid}
                                   required
                            />
                        </FormGroup>

                        <Row>
                            <Col sm={{size: '4', offset: 8}}>
                                <Button disabled={!this.state.formIsValid} onClick={this.handleSubmit}>  Login </Button>
                            </Col>
                        </Row>

                        {
                            this.state.errorStatus > 0 &&
                            <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>
                        }
                    </Form>
                </ModalBody>
            </Modal>


        ) ;
    }
}

export default LoginContainer;
