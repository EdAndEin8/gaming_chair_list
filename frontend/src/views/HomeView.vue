<script setup>
import { ref, onMounted } from 'vue';
import api from '../services/api';      // Notre configuration Axios
import { useAuthStore } from '../stores/auth'; // Pour savoir si on est connecté

const authStore = useAuthStore();
const games = ref([]);        // Pour stocker la liste des jeux reçue
const loading = ref(false);   // Pour afficher "Chargement..."
const error = ref(null);      // Pour afficher les erreurs

// Fonction pour récupérer les jeux
const fetchGames = async () => {
  // Si pas connecté, on ne fait rien
  if (!authStore.token) return;

  loading.value = true;
  error.value = null;

  try {
    // L'appel API (GET http://localhost:8080/api/games)
    // Le token est ajouté automatiquement par api.js
    const response = await api.get('/games');

    // On met les données reçues dans notre variable
    games.value = response.data;
    console.log("Jeux reçus :", games.value);

  } catch (err) {
    console.error("Erreur API :", err);
    error.value = "Impossible de charger les jeux (Vérifie que le backend tourne !)";
  } finally {
    loading.value = false;
  }
};

// Se lance automatiquement quand la page s'affiche
onMounted(() => {
  fetchGames();
});
</script>

<template>
  <div class="home-container">

    <div v-if="!authStore.token" class="welcome-box">
      <h1>Bienvenue sur Gaming Chair List 🪑</h1>
      <p>Connecte-toi pour voir ta collection.</p>
      <router-link to="/login" class="btn">Se connecter</router-link>
    </div>

    <div v-else>
      <h1>Ma Collection 🎮</h1>

      <p v-if="loading">Chargement des jeux en cours...</p>

      <p v-if="error" class="error-msg">{{ error }}</p>

      <div v-if="!loading && !error" class="games-grid">

        <p v-if="games.length === 0">Aucun jeu trouvé. Ajoutes-en un via CURL pour tester !</p>

        <div v-for="game in games" :key="game.id" class="game-card">
          <h3>{{ game.title }}</h3>
          <span class="status" :class="game.status">{{ game.status }}</span>
        </div>

      </div>

      <button @click="fetchGames" style="margin-top: 20px;">🔄 Actualiser</button>
    </div>

  </div>
</template>

<style scoped>
.home-container { max-width: 800px; margin: 0 auto; padding: 20px; text-align: center; }
.welcome-box { background: #f8f9fa; padding: 40px; border-radius: 10px; }
.btn { display: inline-block; background: #42b883; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-top: 10px; }
.error-msg { color: red; background: #ffe6e6; padding: 10px; border-radius: 5px; }

/* Grille des cartes */
.games-grid { display: flex; flex-wrap: wrap; gap: 20px; justify-content: center; margin-top: 20px; }

/* Carte Jeu */
.game-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 15px;
  width: 200px;
  background: white;
  box-shadow: 0 2px 5px rgba(0,0,0,0.05);
  transition: transform 0.2s;
}
.game-card:hover { transform: translateY(-3px); }

/* Badges de statut */
.status { display: inline-block; padding: 4px 8px; border-radius: 12px; font-size: 0.8rem; font-weight: bold; margin-top: 10px; background: #eee; }
.status.TERMINÉ { background: #d4edda; color: #155724; }
.status.EN_COURS { background: #fff3cd; color: #856404; }
.status.A_FAIRE { background: #f8d7da; color: #721c24; }
</style>