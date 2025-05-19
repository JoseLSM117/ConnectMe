import { Navigate, Route, Routes } from 'react-router';
import { AuthLayout } from '../layouts/authLayout/AuthLayout';
import { Register } from '../pages/auth/register/Register';
import { Login } from '../pages/auth/login/Login';

export const AuthRoutes = () => (
  <Routes>
    <Route path="/" element={<AuthLayout />}>
      <Route index element={<Navigate to="register" replace />} />
      <Route path='register' element={<Register />} />
      <Route path='login' element={<Login />} />
    </Route>
  </Routes>
);
