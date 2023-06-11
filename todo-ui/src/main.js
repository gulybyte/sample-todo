import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import PrimeVue from 'primevue/config';
import InputText from 'primevue/inputtext';
import Button from 'primevue/button';



const app = createApp(App)

app.use(router)

app.use(PrimeVue)
app.component('InputText', InputText)
app.component('Button', Button)


app.mount('#app')