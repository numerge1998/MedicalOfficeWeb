import {HOST} from "../commons/hosts";
import RestApiClient from "../commons/api/rest-client";

const endpoint = {
    medication_plan: '/medication_plan/'
};


function postMedPlan(user, id, callback){
    let request = new Request(HOST.backend_api + endpoint.medication_plan + "my_insert/" + id , {
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

export {
    postMedPlan
};