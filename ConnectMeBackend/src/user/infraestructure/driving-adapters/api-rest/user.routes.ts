import { Router } from "express";
import { createUser } from "./controllers/CreateUserController";

export const userRoutes: Router = Router();
userRoutes.post("/", createUser);