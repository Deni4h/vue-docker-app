<template>
  <div id="app">
    <h1>deni devops + Docker App + jenkins</h1>
    <form @submit.prevent="addItem">
      <input
        v-model="newName"
        type="text"
        placeholder="Enter Name"
        required
      />
      <input
        v-model.number="newAge"
        type="number"
        placeholder="Enter Age"
        required
      />
      <button type="submit">Add</button>
    </form>
    <ul>
      <li v-for="(item, index) in items" :key="index">
        {{ item.name }} ({{ item.age }} years old)
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      newName: '', // Input untuk nama
      newAge: null, // Input untuk umur
      items: [] // Data yang akan ditampilkan dari backend
    };
  },
  methods: {
    // Menambahkan data baru ke backend
    addItem() {
      const newItem = {
        name: this.newName,
        age: this.newAge
      };
      fetch('http://192.168.122.100:8083/api/data', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newItem)
      })
        .then(response => {
          if (!response.ok) {
            throw new Error('Failed to add item');
          }
          return response.text();
        })
        .then(() => {
          // Reload data setelah item berhasil ditambahkan
          this.fetchItems();
          this.newName = '';
          this.newAge = null;
        })
        .catch(error => console.error(error));
    },
    // Mengambil semua data dari backend
    fetchItems() {
      fetch('http://192.168.122.100:8083/api/data')
        .then(response => response.json())
        .then(data => {
          this.items = data;
        })
        .catch(error => console.error(error));
    }
  },
  // Memuat data saat aplikasi pertama kali dijalankan
  mounted() {
    this.fetchItems();
  }
};
</script>

<style>
body {
  font-family: Arial, sans-serif;
  margin: 20px;
}

h1 {
  color: #42b983;
}

form {
  margin-bottom: 20px;
}

input {
  padding: 8px;
  font-size: 14px;
  margin-right: 10px;
}

button {
  padding: 8px 16px;
  font-size: 14px;
  background-color: #42b983;
  color: white;
  border: none;
  cursor: pointer;
}

ul {
  list-style: none;
  padding: 0;
}

li {
  padding: 8px;
  background: #f9f9f9;
  margin: 4px 0;
}
</style>
