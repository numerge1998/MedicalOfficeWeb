import {HOST} from '../commons/hosts';
import RestApiClient from "../commons/api/rest-client";


const endpoint = {
    login: '/login/'
};
function getUserRole(username, callback){
    let request = new Request(HOST.backend_api + endpoint.login + "username/" + username, {
        method: 'GET'
    });

    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

export {
    getUserRole
};
