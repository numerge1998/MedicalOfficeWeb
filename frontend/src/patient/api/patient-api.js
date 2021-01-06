import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";


const endpoint = {
    patient: '/patient/'
};

function getPatients(callback) {
    let request = new Request(HOST.backend_api + endpoint.patient, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getPatientById(params, callback){
    let request = new Request(HOST.backend_api + endpoint.patient + params, {
        method: 'GET'
    });

    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getPatientsMeds(id, callback) {
    let request = new Request(HOST.backend_api + endpoint.patient + "find_med_plan/" + id, {
        method: 'GET',
    });
    console.log(request.url + " aici");
    RestApiClient.performRequest(request, callback);
}

function postPatient(user, id, callback){
    let request = new Request(HOST.backend_api + endpoint.patient + "my_insert/" + id , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

function deletePatient(id, callback){
    let request = new Request(HOST.backend_api + endpoint.patient + id , {
        method: 'DELETE',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }
    });

    console.log("URL: " + request.url);
    console.log(callback);

    RestApiClient.performRequest(request, callback);
}

function updatePatient(user, callback){
    let request = new Request(HOST.backend_api + endpoint.patient , {
        method: 'PUT',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

export {
    getPatients,
    getPatientById,
    postPatient,
    deletePatient,
    updatePatient,
    getPatientsMeds
};
