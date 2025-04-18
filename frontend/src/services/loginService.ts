import axios from 'axios'
const baseUrl = 'http://localhost:8080/api/v1/auth/login'

const login = async (credentials : {email: string, pin: string}) => {
    console.log('loginService', credentials)
    const response = await axios.post(baseUrl, credentials)
    console.log(response)
    return response.data
}

export default { login }