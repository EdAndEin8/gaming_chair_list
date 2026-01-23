import axios from 'axios';

// 1. Création de l'instance Axios
const api = axios.create({
    baseURL: 'http://localhost:8080/api', // L'adresse du backend
    headers: {
        'Content-Type': 'application/json',
    },
});

// 2. Intercepteur : On ajoute le Token à chaque requête sortante
api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token'); // On récupère le token stocké
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

export default api;