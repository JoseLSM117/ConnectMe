import { createBrowserRouter, Navigate } from "react-router-dom";
import { AuthLayout } from "../layouts/AuthLayout";
import { LoginPage } from "../pages/auth/login/LoginPage";
import { RegisterPage } from "../pages/auth/register/RegisterPage";

export const router = createBrowserRouter([
    {
        path:"/",
        element:<Navigate to={"/auth/login"}/>
    },
    {
        path: "auth",
        element: <AuthLayout />,
        children: [
            {
                path:"*",
                element:<Navigate to={"/auth/login"}/>
            },
            {
                path: "login",
                element: <LoginPage />
            },
            {
                path: "register",
                element: <RegisterPage />
            }
        ]
    }
]);