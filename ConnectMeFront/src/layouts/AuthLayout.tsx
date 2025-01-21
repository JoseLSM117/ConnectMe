import { Outlet } from "react-router-dom"

export const AuthLayout = () => {
    return (
        <main className="flex justify-center items-center min-h-[100vh]">
            <Outlet/>
        </main>
    )
}
