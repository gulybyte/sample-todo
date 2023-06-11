import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ReportView from '../views/ReportView.vue'
import TableReportView from '../views/TableReportView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/report',
      name: 'report',
      component: ReportView
    },
    {
      path: '/report/table',
      name: 'table',
      component: TableReportView
    }
  ]
})

export default router
