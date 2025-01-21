import { Router } from "express"
import { UserControllers } from "./UserControllers"

export const userRoutes: Router = Router()
const userControllers = new UserControllers()
userRoutes.get("/", userControllers.initRoutes)
