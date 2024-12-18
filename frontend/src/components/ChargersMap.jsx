import { GoogleMap, LoadScript, Marker } from '@react-google-maps/api';
import React from 'react';

// Componente para mostrar el mapa con la ubicación de la Universidad de Concepción
function MapPage() {
  const mapContainerStyle = {
    width: '100%',
    height: '400px',
  };

  const center = {
    lat: -36.8317, // Latitud de la Universidad de Concepción
    lng: -73.0507, // Longitud de la Universidad de Concepción
  };

  const markers = [
    { lat: -36.8317, lng: -73.0507 }, // Coordenadas de la Universidad de Concepción
    // Puedes agregar más coordenadas de otros cargadores aquí
  ];

  return (
    //TODO key api
    <LoadScript googleMapsApiKey="YOUR_GOOGLE_MAPS_API_KEY">
      <GoogleMap
        mapContainerStyle={mapContainerStyle}
        center={center}
        zoom={15} // Zoom adecuado para ver la universidad
      >
        {markers.map((marker, index) => (
          <Marker key={index} position={marker} />
        ))}
      </GoogleMap>
    </LoadScript>
  );
}

export default MapPage;
