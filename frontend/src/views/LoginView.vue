<script setup>
import { ref } from 'vue';
import { useAuthStore } from '../stores/auth';
import { useRouter } from 'vue-router';

const email = ref('');
const password = ref('');
const errorMessage = ref('');

const authStore = useAuthStore();
const router = useRouter();

const handleLogin = async () => {
  errorMessage.value = ''; // Reset erreur

  const result = await authStore.login(email.value, password.value);

  if (result.success) {
    // Si ça marche, on redirige vers l'accueil (ou dashboard)
    router.push('/');
  } else {
    // Sinon on affiche l'erreur
    errorMessage.value = typeof result.error === 'string' ? result.error : 'Échec de connexion';
  }
};
</script>

<template>
  <div class="login-container">
    <h1>Connexion</h1>
    <form @submit.prevent="handleLogin">
      <div class="form-group">
        <label>Email :</label>
        <input v-model="email" type="email" required placeholder="hacker@test.com" />
      </div>

      <div class="form-group">
        <label>Mot de passe :</label>
        <input v-model="password" type="password" required />
      </div>

      <p v-if="errorMessage" class="error">{{ errorMessage }}</p>

      <button type="submit">Se connecter</button>
    </form>
  </div>
</template>

<style scoped>
.login-container { max-width: 400px; margin: 0 auto; padding: 2rem; }
.form-group { margin-bottom: 1rem; display: flex; flex-direction: column; }
input { padding: 0.5rem; margin-top: 0.2rem; }
button { background-color: #42b883; color: white; border: none; padding: 0.7rem; cursor: pointer; }
.error { color: red; }
</style>