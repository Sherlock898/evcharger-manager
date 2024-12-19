import { GoogleMap, LoadScript, Marker } from '@react-google-maps/api';
import React, { useState } from 'react';
import { Link, Route, BrowserRouter as Router, Routes, useNavigate } from 'react-router-dom';
import './App.css';
import NearbyChargers from './components/NearbyChargers';

function SelectRole({ onSelectRole }) {
  const [role, setRole] = useState('user');
  const navigate = useNavigate();

  const handleSelectRole = (event) => {
    event.preventDefault();
    onSelectRole(role);
    navigate('/auth');
  };

  return (
    <div>
      <h2>Selecciona tu Rol</h2>
      <form onSubmit={handleSelectRole}>
        <label>
          Selecciona tu rol:
          <select value={role} onChange={(e) => setRole(e.target.value)}>
            <option value="user">Usuario Común</option>
            <option value="admin">Administrador</option>
          </select>
        </label>
        <button type="submit">Seleccionar Rol</button>
      </form>
    </div>
  );
}

function Auth({ onRegister, onLogin, role }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [phone, setPhone] = useState('');
  const [isLogin, setIsLogin] = useState(true);

  const navigate = useNavigate();

  const clearFields = () => {
    setEmail('');
    setPassword('');
    setFirstName('');
    setLastName('');
    setPhone('');
  };

  const handleRegister = (event) => {
    event.preventDefault();

    const userData = {
      firstName,
      lastName,
      email,
      phone,
      password,
      roles: [role === 'admin' ? 'ROLE_ADMIN' : 'ROLE_USER'],
    };

    onRegister(userData);
    clearFields();
    navigate('/map');
  };

  const handleLogin = (event) => {
    event.preventDefault();

    const userData = { email, password };
    const isLoggedIn = onLogin(userData);

    if (isLoggedIn) {
      clearFields();
      navigate('/map');
    } else {
      alert('Credenciales incorrectas');
    }
  };

  return (
    <div>
      <h2>{isLogin ? 'Iniciar sesión' : 'Registrar Usuario'}</h2>
      <form onSubmit={isLogin ? handleLogin : handleRegister}>
        {!isLogin && (
          <>
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
              type="text"
              placeholder="Teléfono"
              value={phone}
              onChange={(e) => setPhone(e.target.value)}
            />
          </>
        )}
        <input
          type="email"
          placeholder="Correo Electrónico"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          placeholder="Contraseña"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">{isLogin ? 'Iniciar sesión' : 'Registrar'}</button>
      </form>
      <p>
        {isLogin ? (
          <span>
            ¿No tienes cuenta?{' '}
            <button onClick={() => setIsLogin(false)}>Regístrate</button>
          </span>
        ) : (
          <span>
            ¿Ya tienes cuenta?{' '}
            <button onClick={() => setIsLogin(true)}>Inicia sesión</button>
          </span>
        )}
      </p>
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
  const [users, setUsers] = useState([]);
  const [role, setRole] = useState(null);
  const [user, setUser] = useState(null);

  const handleSelectRole = (role) => {
    setRole(role);
  };

  const handleRegister = (userData) => {
    setUsers([...users, userData]);
    setUser(userData);
  };

  const handleLogin = ({ email, password }) => {
    const foundUser = users.find(
      (user) => user.email === email && user.password === password
    );
    if (foundUser) {
      setUser(foundUser);
      return true;
    }
    return false;
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
                  <Link to="/select-role">Seleccionar Rol</Link>
                </li>
              </ul>
            </nav>
            <Routes>
              <Route path="/select-role" element={<SelectRole onSelectRole={handleSelectRole} />} />
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
              <Route path="/auth" element={<Auth onRegister={handleRegister} onLogin={handleLogin} role={role} />} />
            </Routes>
          </div>
        )}
      </div>
    </Router>
  );
}

export default App;
