<script setup>
import { RouterLink, RouterView, useRouter } from 'vue-router'
import { useAuthStore } from './stores/auth'

const authStore = useAuthStore()
const router = useRouter()

// Fonction pour se déconnecter
const handleLogout = () => {
  authStore.logout()
  router.push('/login') // On renvoie vers la page de login
}
</script>

<template>
  <header>
    <nav class="navbar">
      <div class="logo">
        <RouterLink to="/">🪑 GamingChairList</RouterLink>
      </div>

      <div class="menu">
        <RouterLink to="/" class="nav-item">Accueil</RouterLink>

        <template v-if="!authStore.token">
          <RouterLink to="/login" class="nav-item">Connexion</RouterLink>
          <RouterLink to="/register" class="nav-item">Inscription</RouterLink>
        </template>

        <template v-else>
          <span class="username">👤 {{ authStore.user }}</span>
          <button @click="handleLogout" class="logout-btn">Déconnexion</button>
        </template>
      </div>
    </nav>
  </header>

  <main>
    <RouterView />
  </main>
</template>

<style scoped>
/* Un peu de style pour que ça ressemble à un vrai site */
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  background-color: #2c3e50;
  color: white;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
}

.logo a {
  font-size: 1.5rem;
  font-weight: bold;
  color: white;
  text-decoration: none;
}

.menu {
  display: flex;
  align-items: center;
  gap: 1.5rem;
}

.nav-item {
  color: #ecf0f1;
  text-decoration: none;
  font-weight: 500;
  transition: color 0.3s;
}

.nav-item:hover {
  color: #42b883; /* Vert Vue.js */
}

/* Style spécial pour le lien actif (géré par Vue Router) */
.router-link-active {
  color: #42b883;
}

.username {
  font-weight: bold;
  color: #42b883;
}

.logout-btn {
  background-color: #e74c3c;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
}

.logout-btn:hover {
  background-color: #c0392b;
}

main {
  padding: 2rem;
  max-width: 1200px;
  margin: 0 auto;
}
</style>