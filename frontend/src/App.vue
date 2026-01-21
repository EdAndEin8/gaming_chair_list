<script setup>
import { ref } from 'vue'
import axios from 'axios'
import GameCard from './components/GameCard.vue'

// Variable réactive pour stocker la liste des jeux
const games = ref([])
const loading = ref(false)
const error = ref(null)

// ⚠️ REMPLACE PAR TON STEAM ID ICI
const steamId = '76561197960434622'

const fetchGames = async () => {
  loading.value = true
  error.value = null

  try {
    // Appel vers TON backend Spring Boot (port 8080)
    const response = await axios.get(`http://localhost:8080/api/games/steam/${steamId}`)
    games.value = response.data
    console.log("Jeux reçus :", games.value) // Pour débugger dans la console du navigateur
  } catch (err) {
    console.error("Erreur API:", err)
    error.value = "Impossible de charger les jeux. Vérifie que le Backend tourne."
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="container">
    <header>
      <h1>🎮 Gaming Chair List</h1>
      <button @click="fetchGames" :disabled="loading" class="btn-import">
        {{ loading ? 'Chargement...' : 'Importer ma bibliothèque Steam' }}
      </button>
    </header>

    <div v-if="error" class="error">{{ error }}</div>

    <div class="grid">
      <GameCard
          v-for="game in games"
          :key="game.appid"
          :game="game"
      />
    </div>
  </div>
</template>

<style>
body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background-color: #f0f2f5;
  margin: 0;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

header {
  text-align: center;
  margin-bottom: 40px;
}

.btn-import {
  background-color: #2f3542;
  color: white;
  border: none;
  padding: 12px 24px;
  font-size: 16px;
  border-radius: 8px;
  cursor: pointer;
}

.btn-import:hover {
  background-color: #57606f;
}

.btn-import:disabled {
  opacity: 0.7;
  cursor: not-allowed;
}

.grid {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
}

.error {
  color: red;
  text-align: center;
  margin-bottom: 20px;
}
</style>