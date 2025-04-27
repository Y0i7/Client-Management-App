import React, { useEffect, useState } from 'react';
import ClientService from '../Services/ClientService';
import { Link } from 'react-router-dom';

/*
 * @Author: Orlando Yepes Espitia
 * @Date: 2025/26/04
 * @Description: This component is responsible for listing all clients and providing options to search, update, and delete them.
 * @Version: 2.0.0
 */

function ListClientComponent() {

    const [clients, setClients] = useState([]);
    const [searchName, setSearchName] = useState('');
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        listClients();
    }, []);

    const listClients = async () => {
        setLoading(true);
        try {
            const response = await ClientService.getAllClients();
            setClients(response.data.CLIENTS);
        } catch (error) {
            console.error(error);
            window.alert('An error occurred while fetching clients.');
        } finally {
            setLoading(false);
        }
    };

    const deleteClient = async (clientId) => {
        try {
            await ClientService.deleteClient(clientId);
            window.alert('Client: ' + clientId + ' has been deleted');
            listClients();
        } catch (error) {
            console.error(error);
            window.alert('An error occurred while deleting the client.');
        }
    };

    const searchClientsByName = async () => {
        if (!searchName.trim()) {
            listClients();
            return;
        }

        if (/[^a-zA-Z0-9 ]/g.test(searchName)) {
            window.alert('Invalid characters in search input.');
            return;
        }

        setLoading(true);
        try {
            const response = await ClientService.getClientsByName(searchName);
            if (response.data.CLIENTS) {
                setClients(response.data.CLIENTS);
            } else {
                window.alert('Client not Found');
                listClients()
            }

        } catch (error) {
            console.error(error);
            window.alert('An error occurred while searching for clients.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className='container'>
            <h2 className='text-center' id='tittlelist'>Clients</h2>

            {/* Contenedor para el input de búsqueda y el botón "Add Client" */}
            <div className='d-flex justify-content-between align-items-center mb-3'>
                <Link to='/add-client' className='btn btn-primary'>Add Client</Link>

                <div className='d-flex align-items-center' style={{ gap: '10px' }}>
                    <input
                        type='text'
                        placeholder='Search by name'
                        className='form-control'
                        value={searchName}
                        onChange={(e) => setSearchName(e.target.value)}
                        style={{ width: '300px' }}
                    />
                    <button
                        className='btn btn-warning'
                        onClick={searchClientsByName}
                    >
                        Search
                    </button>
                </div>
            </div>

            {loading ? (
                <div className='text-center'>Loading...</div>
            ) : (
                <table className='table table-bordered table-striped'>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>NAME</th>
                            <th>LAST NAME</th>
                            <th>EMAIL</th>
                            <th>UPDATED_AT</th>
                            <th>OPTION</th>
                        </tr>
                    </thead>
                    <tbody>
                        {clients.length > 0 ? (
                            clients.map(client => (
                                <tr key={client.ID}>
                                    <td id='id'>{client.ID}</td>
                                    <td>{client.NAME}</td>
                                    <td>{client.LAST_NAME || 'N/A'}</td>
                                    <td>{client.EMAIL}</td>
                                    <td>{client.DATE}</td>
                                    <td id='option'>
                                        <Link className='btn btn-info' to={`/edit-client/${client.ID}`}>Update</Link>
                                        <button style={{ marginLeft: "10px" }} className='btn btn-danger' onClick={() => deleteClient(client.ID)}>Delete</button>
                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="6" className="text-center">No clients found.</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default ListClientComponent;
