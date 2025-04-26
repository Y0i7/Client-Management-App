import axios from "axios";

const API_URL = process.env.REACT_APP_API_URL;
const CLIENT_GET_REST_API_URL = `${API_URL}/clients`;
const CLIENT_GETBYID_API_URL = `${API_URL}/client/`;
const CLIENT_GETBYNAME_API_URL = `${API_URL}/name/`;
const CLIENT_POST_REST_API_URL = `${API_URL}/add`;
const CLIENT_PUT_REST_API_URL = `${API_URL}/update/`;
const CLIENT_DELETE_REST_API_URL = `${API_URL}/delete/`;

class ClientService {
    getAllClients() {
        return axios.get(CLIENT_GET_REST_API_URL);
    }

    getClientById(clientId) {
        return axios.get(CLIENT_GETBYID_API_URL + clientId);
    }

    getClientsByName(name) {
        return axios.get(CLIENT_GETBYNAME_API_URL + name);
    }


    saveClient(client) {
        return axios.post(CLIENT_POST_REST_API_URL, client)
    }

    updateClient(clientId, client) {
        return axios.put(CLIENT_PUT_REST_API_URL + clientId, client)
    }

    deleteClient(clientId) {
        return axios.delete(CLIENT_DELETE_REST_API_URL + clientId)
    }


}


export default new ClientService();