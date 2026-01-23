<script setup>
import { ref } from 'vue';
import { useAuthStore } from '../stores/auth';
import { useRouter } from 'vue-router';

const username = ref('');
const email = ref('');
const password = ref('');
const errorMessage = ref('');

const authStore = useAuthStore();
const router = useRouter();

const handleRegister = async () => {
  errorMessage.value = '';

  // Appel au store pour s'inscrire
  const result = await authStore.register(username.value, email.value, password.value);

  if (result.success) {
    alert('Compte créé ! Tu peux maintenant te connecter.');
    router.push('/login'); // Redirection vers le login
  } else {
    errorMessage.value = typeof result.error === 'string' ? result.error : "Erreur lors de l'inscription";
  }
};
</script>

<template>
  <div class="register-container">
    <h1>Créer un compte</h1>
    <form @submit.prevent="handleRegister">
      <div class="form-group">
        <label>Pseudo :</label>
        <input v-model="username" type="text" required placeholder="GamerPro" />
      </div>

      <div class="form-group">
        <label>Email :</label>
        <input v-model="email" type="email" required placeholder="email@test.com" />
      </div>

      <div class="form-group">
        <label>Mot de passe :</label>
        <input v-model="password" type="password" required />
      </div>

      <p v-if="errorMessage" class="error">{{ errorMessage }}</p>

      <button type="submit">S'inscrire</button>
    </form>
  </div>
</template>

<style scoped>
.register-container { max-width: 400px; margin: 0 auto; padding: 2rem; }
.form-group { margin-bottom: 1rem; display: flex; flex-direction: column; }
input { padding: 0.5rem; margin-top: 0.2rem; }
button { background-color: #3498db; color: white; border: none; padding: 0.7rem; cursor: pointer; margin-top: 10px;}
button:hover { background-color: #2980b9; }
.error { color: red; }
</style>