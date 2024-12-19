import { GoogleMap, LoadScript, Marker } from '@react-google-maps/api';
import React, { useState } from 'react';
import { Link, Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './App.css';
import NearbyChargers from './components/NearbyChargers';

function Login({ onLogin }) {
  const [role, setRole] = useState('user');

  const handleLogin = (event) => {
    event.preventDefault();
    onLogin(role);
  };

  return (
    <div>
      <h2>Iniciar Sesión</h2>
      <form onSubmit={handleLogin}>
        <label>
          Selecciona tu rol:
          <select value={role} onChange={(e) => setRole(e.target.value)}>
            <option value="user">Usuario Común</option>
            <option value="admin">Administrador</option>
          </select>
        </label>
        <button type="submit">Iniciar Sesión</button>
      </form>
    </div>
  );
}

function Register({ onRegister }) {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();

    const userData = {
      firstName,
      lastName,
      email,
      phone,
      password,
      roles: ['ROLE_USER'],
    };

    console.log(userData);
    onRegister(userData);
  };

  return (
    <div>
      <h2>Registrar Usuario</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Nombre"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        />
        <input
          type="text"
          placeholder="Apellido"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
        />
        <input
          type="email"
          placeholder="Correo Electrónico"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="text"
          placeholder="Teléfono"
          value={phone}
          onChange={(e) => setPhone(e.target.value)}
        />
        <input
          type="password"
          placeholder="Contraseña"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Registrar</button>
      </form>
    </div>
  );
}

function MapPage() {
  const mapContainerStyle = {
    width: '100%',
    height: '400px',
  };

  const center = {
    lat: 40.730610,
    lng: -73.935242,
  };

  const markers = [
    { lat: 40.730610, lng: -73.935242 },
    { lat: 40.740610, lng: -73.925242 },
  ];

  return (
    <LoadScript googleMapsApiKey="YOUR_GOOGLE_MAPS_API_KEY">
      <GoogleMap
        mapContainerStyle={mapContainerStyle}
        center={center}
        zoom={12}
      >
        {markers.map((marker, index) => (
          <Marker key={index} position={marker} />
        ))}
      </GoogleMap>
    </LoadScript>
  );
}

function PaymentCards() {
  const [cards, setCards] = useState([]);
  const [cardNumber, setCardNumber] = useState('');

  const handleAddCard = () => {
    setCards([...cards, cardNumber]);
    setCardNumber('');
  };

  return (
    <div>
      <h2>Registrar Tarjeta de Pago</h2>
      <input
        type="text"
        placeholder="Número de tarjeta"
        value={cardNumber}
        onChange={(e) => setCardNumber(e.target.value)}
      />
      <button onClick={handleAddCard}>Registrar</button>
      <h3>Tarjetas Registradas:</h3>
      <ul>
        {cards.map((card, index) => (
          <li key={index}>{card}</li>
        ))}
      </ul>
    </div>
  );
}

function App() {
  const [role, setRole] = useState(null);
  const [user, setUser] = useState(null);

  const handleLogin = (userRole) => {
    setRole(userRole);
  };

  const handleRegister = (userData) => {
    setUser(userData);
    setRole('user');
  };

  return (
    <Router>
      <div className="App">
        <h1>EV Charger Manager</h1>
        {!role ? (
          <div>
            <h2>Bienvenido</h2>
            <nav>
              <ul>
                <li>
                  <Link to="/login">Iniciar Sesión</Link>
                </li>
                <li>
                  <Link to="/register">Registrar Usuario</Link>
                </li>
              </ul>
            </nav>
            <Routes>
              <Route path="/login" element={<Login onLogin={handleLogin} />} />
              <Route path="/register" element={<Register onRegister={handleRegister} />} />
            </Routes>
          </div>
        ) : (
          <div>
            <h2>Bienvenido {role === 'admin' ? 'Administrador' : 'Usuario Común'}</h2>
            <nav>
              <ul>
                <li>
                  <Link to="/map">Ver Cargadores en el Mapa</Link>
                </li>
                <li>
                  <Link to="/payment">Registrar Tarjeta</Link>
                </li>
                <li>
                  <Link to="/nearby">Cargadores Cercanos</Link>
                </li>
              </ul>
            </nav>
            <Routes>
              <Route path="/map" element={<MapPage />} />
              <Route path="/payment" element={<PaymentCards />} />
              <Route path="/nearby" element={<NearbyChargers />} />
            </Routes>
          </div>
        )}
      </div>
    </Router>
  );
}

export default App;
