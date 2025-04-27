import React, { useEffect, useState } from 'react';
import { useNavigate, Link, useParams } from 'react-router-dom';
import ClientService from '../Services/ClientService';

/*
 * @Author: Orlando Yepes Espitia
 * @Date: 2025/26/04
 * @Description: ClientFormComponent is a React component that allows users to add or update client information. It includes form fields for name, last name, and email, and handles form submission to save or update client data.
 * @Version: 2.0.0
 */

const ClientFormComponent = () => {

    const [errorMessage, setErrorMessage] = useState('');
    const [name, setName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const navigate = useNavigate();
    const { clientId } = useParams();

    const saveOrUpdateClient = (e) => {
        e.preventDefault();

        const client = {
            ID: clientId,
            NAME: name,
            LAST_NAME: lastName,
            EMAIL: email
        };

        if (!name || !email) {
            setErrorMessage("Name and email cannot be empty.");
            return;
        }

        if (clientId) {
            ClientService.updateClient(clientId, client).then((response) => {
                window.alert('Client successfully updated!');
                navigate('/clients');
            }).catch(error => {
                console.error(error);
                setErrorMessage("An unexpected error occurred.");
            });
        } else {
            ClientService.saveClient(client).then((response) => {
                window.alert('Client successfully saved!');
                navigate('/clients');
            }).catch(error => {
                if (error.response && error.response.status === 409) {
                    setErrorMessage("Client with this email already exists.");
                } else {
                    setErrorMessage("An unexpected error occurred.");
                }
            });
        }
    }

    useEffect(() => {
        if (clientId) {
            ClientService.getClientById(clientId).then(response => {
                const clientData = response.data.CLIENT;
                setName(clientData.NAME || '');
                setLastName(clientData.LAST_NAME || '');
                setEmail(clientData.EMAIL || '');
            }).catch(error => {
                console.log("Error fetching client data:", error);
            });
        }
    }, [clientId]);

    const title = () => {
        return <p className='text-center'>{clientId ? 'Update Client' : 'Add Client'}</p>;
    };

    return (
        <div id='form'>
            <div className='container'>
                <div className='row'>
                    <div className='card col-md-6 offset-md-3 offset-md-3'>
                        <h2 className='text-center'>
                            {title()}
                        </h2>
                        <div className='card-body'>
                            {/* Error Message Zone */}
                            {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
                            <form>
                                <div className='form-group mb-2'>
                                    <label className='form-label'>Name</label>
                                    <input
                                        type='text'
                                        placeholder='your name'
                                        name='name'
                                        className='form-control'
                                        value={name}
                                        onChange={(e) => setName(e.target.value)}
                                        required
                                    />
                                </div>
                                <div className='form-group mb-2'>
                                    <label className='form-label'>Last Name</label>
                                    <input
                                        type='text'
                                        placeholder='your last name'
                                        name='lastName'
                                        className='form-control'
                                        value={lastName}
                                        onChange={(e) => setLastName(e.target.value)}
                                    />
                                </div>
                                <div className='form-group mb-2'>
                                    <label className='form-label'>Email</label>
                                    <input
                                        type='email'
                                        placeholder='example@email.com'
                                        name='email'
                                        className='form-control'
                                        value={email}
                                        onChange={(e) => setEmail(e.target.value)}
                                        required
                                    />
                                </div>
                                <button className='btn btn-success' onClick={(e) => saveOrUpdateClient(e)}>Save</button>
                                &nbsp;&nbsp;
                                <Link to="/clients" className='btn btn-danger'>Cancel</Link>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ClientFormComponent;
