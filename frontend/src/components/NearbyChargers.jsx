import React, { useEffect, useState } from 'react';
import api from '../api/apiClient';

function NearbyChargers() {
    const [chargers, setChargers] = useState([]);

    useEffect(() => {
        async function fetchNearbyChargers() {
            try {
                const response = await api.get('/chargers/nearby');
                setChargers(response.data);
            } catch (error) {
                console.error('Error fetching nearby chargers:', error);
            }
        }

        fetchNearbyChargers();
    }, []);

    return (
        <div>
            <h1>Cargadores Cercanos</h1>
            <ul>
                {chargers.map((charger) => (
                    <li key={charger.id}>{charger.name}</li>
                ))}
            </ul>
        </div>
    );
}

export default NearbyChargers;
