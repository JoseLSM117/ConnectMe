/* eslint-disable comma-dangle */
import { AuthRoutes } from './routes/AuthRoutes';
import { BrowserRouter, Navigate, Route, Routes } from 'react-router';
import { createRoot } from 'react-dom/client';
import { PrimeReactProvider } from 'primereact/api';
import { StrictMode } from 'react';
import { QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';

import './assets/themes/main.css';
import './index.css';
import { queryClient } from './api/react-query/queryClient';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <QueryClientProvider client={queryClient}>
      <PrimeReactProvider>
        <BrowserRouter>
          <Routes>
            <Route path='/auth/*' element={<AuthRoutes />} />
            <Route path='*' element={<Navigate to='/auth' />} />
          </Routes>
        </BrowserRouter>
      </PrimeReactProvider>
      <ReactQueryDevtools initialIsOpen={false} />
    </QueryClientProvider>
  </StrictMode>
);
