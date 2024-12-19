import axios from 'axios';
import React, { useState } from 'react';

const Register = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [password, setPassword] = useState('');
  const [roles, setRoles] = useState(['ROLE_USER']);

  const handleSubmit = async (event) => {
    event.preventDefault();

    const userData = {
      firstName,
      lastName,
      email,
      phone,
      password,
      roles,
    };

    try {
      const response = await axios.post('/api/users/register', userData);
      console.log('Usuario registrado con éxito:', response.data);
    } catch (error) {
      console.error('Error al registrar usuario:', error.response ? error.response.data : error.message);
    }
  };

  return (
    <div className="register-form">
      <h2>Registro de Usuario</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="firstName">Nombre:</label>
          <input
            type="text"
            id="firstName"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
            required
          />
        </div>

        <div>
          <label htmlFor="lastName">Apellido:</label>
          <input
            type="text"
            id="lastName"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
            required
          />
        </div>

        <div>
          <label htmlFor="email">Correo Electrónico:</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>

        <div>
          <label htmlFor="phone">Teléfono:</label>
          <input
            type="text"
            id="phone"
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
            required
          />
        </div>

        <div>
          <label htmlFor="password">Contraseña:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>

        <div>
          <label htmlFor="roles">Roles:</label>
          <select
            id="roles"
            value={roles}
            onChange={(e) => setRoles([e.target.value])}
          >
            <option value="ROLE_USER">Usuario</option>
            <option value="ROLE_ADMIN">Administrador</option>
            <option value="ROLE_SUB_ADMIN">Sub Administrador</option>
          </select>
        </div>

        <button type="submit">Registrar</button>
      </form>
    </div>
  );
};

export default Register;
