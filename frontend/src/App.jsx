import { GoogleMap, LoadScript, Marker } from '@react-google-maps/api';
import React, { useState } from 'react';
import { Link, Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './App.css';

// Componente para el formulario de inicio de sesión
function Login({ onLogin }) {
  const [role, setRole] = useState('user'); // Estado para almacenar el rol del usuario (admin o user)

  const handleLogin = (event) => {
    event.preventDefault();
    onLogin(role); // Llamamos la función onLogin para pasar el rol seleccionado
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

// Componente para mostrar el mapa con cargadores
function MapPage() {
  const mapContainerStyle = {
    width: '100%',
    height: '400px',
  };

  const center = {
    lat: 40.730610, // Coordenada central (puedes ajustarla según la ubicación del usuario)
    lng: -73.935242,
  };

  const markers = [
    { lat: 40.730610, lng: -73.935242 },
    { lat: 40.740610, lng: -73.925242 },
    // Agrega más cargadores aquí
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

// Componente para registrar tarjetas
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

// Componente principal
function App() {
  const [role, setRole] = useState(null);

  const handleLogin = (userRole) => {
    setRole(userRole); // Guardamos el rol después de que el usuario se haya logueado
  };

  return (
    <Router>
      <div className="App">
        <h1>EV Charger Manager</h1>
        {!role ? (
          <Login onLogin={handleLogin} /> // Si no hay rol, mostramos el formulario de inicio de sesión
        ) : (
          <div>
            <h2>Bienvenido {role === 'admin' ? 'Administrador' : 'Usuario Común'}</h2>
            <nav>
              <ul>
                <li>
                  <Link to="/map">Ver Cargadores</Link>
                </li>
                <li>
                  <Link to="/payment">Registrar Tarjeta</Link>
                </li>
              </ul>
            </nav>
            <Routes>
              <Route path="/map" element={<MapPage />} />
              <Route path="/payment" element={<PaymentCards />} />
            </Routes>
          </div>
        )}
      </div>
    </Router>
  );
}

export default App;
