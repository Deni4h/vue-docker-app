# Vue Docker App

## Deskripsi
Vue Docker App adalah proyek full-stack yang terdiri dari frontend (Vue.js) dan backend, yang dikemas menggunakan Docker dan didukung oleh pipeline otomatisasi deployment dengan Jenkins.

## Fitur
- **Frontend:** Dibangun dengan Vue.js
- **Backend:** API yang berfungsi sebagai layanan pendukung frontend, memakai java dengan framework springboot
- **Dockerized:** Dikemas menggunakan Docker untuk kemudahan deployment
- **CI/CD:** Otomatisasi deployment dengan Jenkins

## Prasyarat
Sebelum menjalankan proyek ini, pastikan telah menginstal:
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Jenkins](https://www.jenkins.io/download/)

## Cara Menjalankan
### 1. Clone Repository
```bash
git clone https://github.com/Deni4h/vue-docker-app.git
cd vue-docker-app
```

### 2. Build dan Jalankan dengan Docker Compose
jika belum punya image docker basic bisa pull dari docker hub

- docker pull denidkr24/frontend-vue:origin-main-20250304001420
- docker pull denidkr24/be-java-app:origin-main-20250304001420

```bash
docker-compose up --build -d
```


### 3. Akses Aplikasi
- **Frontend:** `http://localhost:8087`
- **Backend:** `http://localhost:8083/api/data`

## Deployment dengan Jenkins
Jenkins dikonfigurasi untuk otomatisasi build dan deployment aplikasi ini. Pastikan:

1. **Setup Jenkins:**
   - Instal Jenkins dengan plugin Docker dan Git.
   - Buat pipeline baru di Jenkins.
2. **integrasikan repositori ke jenkins job pipeline**
   - ketika membuat job jenkins, integrasikan job dengan repositori ini
3. **build now jenkins**
   - ketika semua sudah di konfigurasi dengan baik silakan di build now
2. **setelah build now selesai**
   - ganti docker image yang ada di docker-compose, dengan image yang baru dibuat oleh jenkins 
## Struktur Direktori
```
vue-docker-app/
├── frontend/    # Kode sumber frontend Vue.js
├── backend/     # Kode sumber backend
├── Jenkinsfile  # Konfigurasi pipeline Jenkins
├── docker-compose.yml # Konfigurasi Docker Compose
├── README.md    # Dokumentasi proyek
```
Dikembangkan oleh [Deni4h](https://github.com/Deni4h)

