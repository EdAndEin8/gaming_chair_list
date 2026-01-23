import { createRouter, createWebHistory } from 'vue-router'
// Importe tes vues
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import HomeView from "../views/HomeView.vue";
// Crée un fichier HomeView.vue vide plus tard
// import HomeView from '../views/HomeView.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomeView
            //component: () => import('../views/HomeView.vue') // Temporaire : redirige vers Login pour tester
        },
        {
            path: '/login',
            name: 'login',
            component: LoginView
        },
        {
            path: '/register',
            name: 'register',
            component: RegisterView
        }
    ]
})

export default router