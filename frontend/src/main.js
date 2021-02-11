import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

createApp(App).use(router).mount('#app')
window.Kakao.init('8e2dc4d908bdabe00e13083f978890e1');
