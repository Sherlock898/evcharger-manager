import React from 'react';
import { Link } from 'react-router-dom';

type SidebarProps = {
  setView: React.Dispatch<React.SetStateAction<string>>;
};

const Sidebar: React.FC<SidebarProps> = ({ setView }) => {
  return (
    <div className="sidebar">
      <div className="sidebar-header">
        <h2>Gestión de Estaciones</h2>
      </div>
      <div className="sidebar-menu">
        <ul>
          <li onClick={() => setView('stations')}>
            <Link to="#">Estaciones</Link>
          </li>
          <li onClick={() => setView('mapa')}>
            <Link to="#">Mapa general de cargadores</Link>
          </li>
          <li onClick={() => setView('gastos')}>
            <Link to="#">Información de gastos</Link>
          </li>
          <li onClick={() => setView('usuarios')}>
            <Link to="#">Usuarios</Link>
          </li>
          <li onClick={() => setView('configuraciones')}>
            <Link to="#">Configuraciones</Link>
          </li>
          <li onClick={() => { /* Lógica para cerrar sesión */ }}>
            <Link to="#">Cerrar sesión</Link>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default Sidebar;