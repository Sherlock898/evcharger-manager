import axios from 'axios';

async function login(username, password) {
    try {
        const response = await axios.post('http://localhost:8080/api/v1/auth/login', { username, password });
        return response.data.token;
    } catch (error) {
        console.error('Error during login:', error);
        throw error;
    }
}
