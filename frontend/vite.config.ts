import react from '@vitejs/plugin-react';
import { defineConfig } from 'vite';

// https://vitejs.dev/config/
export default defineConfig({
    base: 'http://localhost:2109/movements/',
    plugins: [
        react({ include: "**/*.tsx" })
    ],
    server: {
        host: true,
        port: 4400,
        watch: {
            usePolling: true
        }
    }
});
