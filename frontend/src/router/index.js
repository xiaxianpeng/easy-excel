// router/index.js
import {createRouter, createWebHistory} from 'vue-router';

// 导入组件
import ReadExcel from '@/pages/ReadExcel.vue';

export const routes = [, {
    path: '/readexcel',
    name: 'ReadExcel',
    component: ReadExcel,
},
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
