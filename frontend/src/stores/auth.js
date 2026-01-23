import { defineStore } from 'pinia';
import api from '../services/api'; // On importe notre configuration Axios
import { ref } from 'vue';

export const useAuthStore = defineStore('auth', () => {
    const token = ref(localStorage.getItem('token') || null);
    const user = ref(localStorage.getItem('username') || null);

    // Action : Se connecter
    async function login(email, password) {
        try {
            const response = await api.post('/auth/login', { email, password });


            token.value = response.data.token;
            user.value = response.data.username;


            localStorage.setItem('token', token.value);
            localStorage.setItem('username', user.value);

            return { success: true };
        } catch (error) {
            console.error("Erreur de login", error);
            return { success: false, error: error.response?.data || "Erreur de connexion" };
        }
    }

    async function register(username, email, password) {
        try {
            await api.post('/auth/register', { username, email, password });
            return { success: true };
        } catch (error) {
            return { success: false, error: error.response?.data || "Erreur d'inscription" };
        }
    }


    function logout() {
        token.value = null;
        user.value = null;
        localStorage.removeItem('token');
        localStorage.removeItem('username');
    }

    return { token, user, login, register, logout };
});