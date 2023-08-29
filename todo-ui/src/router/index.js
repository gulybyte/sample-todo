import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import InsightView from '../views/InsightView.vue'
import TableInsightView from '../views/TableInsightView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/insight',
      name: 'insight',
      component: InsightView
    },
    {
      path: '/insight/table',
      name: 'table',
      component: TableInsightView
    }
  ]
})

export default router
