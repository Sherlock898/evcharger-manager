import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:5000/api", // URL del backend
});

export const registrarUsuario = (datos) => api.post("/register", datos);
export const obtenerCargadores = () => api.get("/chargers");
export const registrarTarjeta = (tarjeta) => api.post("/cards", tarjeta);
