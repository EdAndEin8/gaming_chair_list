<script setup>
// On définit ce que le composant reçoit comme données (un objet "game")
defineProps({
  game: Object
})
</script>

<template>
  <div class="card">
    <img
        v-if="game.img_icon_url"
        :src="`http://media.steampowered.com/steamcommunity/public/images/apps/${game.appid}/${game.img_icon_url}.jpg`"
        alt="Cover"
        class="game-cover"
    >
    <div class="card-content">
      <h3>{{ game.name }}</h3>
      <button class="btn-fav">♥ Favori</button>
    </div>
  </div>
</template>

<style scoped>
.card {
  background: white;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0,0,0,0.1);
  width: 200px;
  overflow: hidden;
  transition: transform 0.2s;
  display: flex;
  flex-direction: column;
}

.card:hover {
  transform: translateY(-5px);
}

.game-cover {
  width: 100%;
  height: 100px; /* Taille fixe pour l'uniformité */
  object-fit: cover;
}

.card-content {
  padding: 10px;
  text-align: center;
}

h3 {
  font-size: 14px;
  margin: 10px 0;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.btn-fav {
  background-color: #ff4757;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
}
</style>